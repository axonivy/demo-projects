package test.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.NotificationAccess;
import ch.ivyteam.ivy.scripting.objects.Tuple;
import ch.ivyteam.ivy.workflow.CaseState;
import workflow.order.Order;
import workflow.order.Person;

@IvyProcessTest
public class TestOrder {
  private static final BpmProcess ORDER_PROCESS = BpmProcess.name("OrderProcess");

  private static final BpmElement START_ORDER = ORDER_PROCESS.elementName("startOrder");
  private static final BpmElement INPUT_ORDER = ORDER_PROCESS.elementName("Input Order");
  private static final BpmElement PROCESS_ORDER = ORDER_PROCESS.elementName("Process Order");
  private static final BpmElement DELIVER_ORDER = ORDER_PROCESS.elementName("Deliver Order");
  private static final BpmElement SEND_INVOICE = ORDER_PROCESS.elementName("Send Invoice");
  private static final BpmElement BOOK_PAYMENT = ORDER_PROCESS.elementName("Book Payment");

  private static final Person CUSTOMER = new Person();
  private static final Order ORDER = new Order();

  static {
    CUSTOMER.setName("Doe");
    CUSTOMER.setSurname("John");
    ORDER.setProduct("Ferrari Testarossa");
  }

  @Test
  void runProcess(BpmClient bpmClient, NotificationAccess notifications) {
    bpmClient.mock().uiOf(INPUT_ORDER).with(this::inputOrder);
    bpmClient.mock().uiOf(PROCESS_ORDER).withNoAction();
    bpmClient.mock().uiOf(DELIVER_ORDER).withNoAction();
    bpmClient.mock().uiOf(DELIVER_ORDER).withNoAction();
    bpmClient.mock().uiOf(SEND_INVOICE).withNoAction();
    bpmClient.mock().uiOf(BOOK_PAYMENT).withNoAction();

    var notificationsCount = notifications.count();

    var result = bpmClient.start().process(START_ORDER)
        .as().everybody().execute();
    bpmClient.start().anyActiveTask(result).as().role("Processor").execute();
    bpmClient.start().anyActiveTask(result).as().role("Deliverer").execute();
    bpmClient.start().anyActiveTask(result).as().role("Finance").execute();
    bpmClient.start().anyActiveTask(result).as().role("Finance").execute();

    assertThat(result.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
    assertThat(notifications.count()).isEqualTo(notificationsCount + 9);
  }

  private void inputOrder(Tuple parameters, Tuple results) {
    try {
      results.set("customer", CUSTOMER);
      results.set("order", ORDER);
    } catch (NoSuchFieldException ex) {
      throw new RuntimeException(ex);
    }
  }
}
