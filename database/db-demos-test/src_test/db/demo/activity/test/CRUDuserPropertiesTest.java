package db.demo.activity.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.History;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import db.demos.activity.Player;

@IvyProcessTest(enableWebServer = true)
public class CRUDuserPropertiesTest {

  private static final BpmProcess testee = BpmProcess.path("activity/CRUDuserProperties");

  @Test
  void callProcess(BpmClient bpmClient) {
    BpmElement startable = testee.elementName("Create.ivp");
    ExecutionResult result = bpmClient.start().process(startable).as().user("Alvin").execute();

    History history = result.history();
    assertThat(history.elementNames()).containsExactly("Create.ivp", "Create", "Read", "Update", "Delete", "");

    Player orderData = result.data().last();
    assertThat(orderData.getFavoritecolor()).isEqualTo("");
  }

}
