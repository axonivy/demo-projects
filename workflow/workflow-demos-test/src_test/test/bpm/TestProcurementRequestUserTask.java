package test.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.TaskSelector;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.TaskState;
import workflow.humantask.ProcurementRequest;

/**
 * Tests the ProcurementRequest using the {@link BpmClient} testing API and mocking ui:
 * <code>workflow-demos/processes/Humantask/ProcurementRequestUserTask.mod</code>
 *
 * @author rew
 * @author rwei
 * @since Mar 3, 2020
 */
@IvyProcessTest
class TestProcurementRequestUserTask {
  /** Processes under test */
  private static final BpmProcess PROCUREMENT_PROCESS = BpmProcess.name("ProcurementRequestUserTask");

  /** HTML Dialogs under test */
  private interface HtmlDialog {
    BpmElement ENTER_REQUEST = PROCUREMENT_PROCESS.elementName("Enter Request");
    BpmElement VERIFY_REQUEST = PROCUREMENT_PROCESS.elementName("Verify Request");
    BpmElement ACCEPT_REQUEST = PROCUREMENT_PROCESS.elementName("Accept Request");
  }

  private interface Role {
    String MANAGER = "Manager";
    String EXECUTIVE_MANAGER = "Executive Manager";
  }

  /**
   * Request not verify
   */
  @Test
  void approvalTask_notVerified(BpmClient bpmClient) {
    var result = createProcurrementRequest(bpmClient);

    bpmClient.mock().uiOf(HtmlDialog.VERIFY_REQUEST)
        .with((_, results) -> results.set("dataOk", false));
    var verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  /**
   * Request verified but not accepted
   */
  @Test
  void approvalTask_verified_notAccepted(BpmClient bpmClient) {
    var result = createProcurrementRequest(bpmClient);

    bpmClient.mock().uiOf(HtmlDialog.VERIFY_REQUEST)
        .with((_, results) -> results.set("dataOk", true));
    var verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    bpmClient.mock().uiOf(HtmlDialog.ACCEPT_REQUEST)
        .with((_, results) -> results.set("accepted", false));
    ProcurementRequest request = acceptRequest(bpmClient, verifyResult);

    assertThat(request.getAccepted()).isFalse();
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  /**
   * Verified and accepted
   */
  @Test
  void approvalTask_verified_accepted(BpmClient bpmClient) {
    var result = createProcurrementRequest(bpmClient);

    bpmClient.mock().uiOf(HtmlDialog.VERIFY_REQUEST)
        .with((_, results) -> results.set("dataOk", true));
    var verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    bpmClient.mock().uiOf(HtmlDialog.ACCEPT_REQUEST)
        .with((_, results) -> results.set("accepted", true));
    ProcurementRequest request = acceptRequest(bpmClient, verifyResult);

    assertThat(request.getAccepted()).isTrue();
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  private static ExecutionResult createProcurrementRequest(BpmClient bpmClient) {
    bpmClient.mock().uiOf(HtmlDialog.ENTER_REQUEST).with((_, results) -> results.set("procurementRequestData", newComputer()));
    ExecutionResult result = bpmClient
        .start().process(PROCUREMENT_PROCESS)
        .as().user("ldv")
        .execute();

    assertThat(result).isNotNull();
    assertProcurementInitTask(result);
    return result;
  }

  private static ProcurementRequest newComputer() {
    var testData = new ProcurementRequest();
    testData.setDescription("PC");
    testData.setAmount(3);
    testData.setPricePerUnit(1222.95);
    testData.setTotalPrice(3668.85);
    return testData;
  }

  private static void assertProcurementInitTask(ExecutionResult result) {
    TaskSelector tasks = result.workflow().activeTask();
    assertThat(result.workflow().activeTasks()).hasSize(1);
    assertThat(tasks.responsibleRole(Role.MANAGER).get().getName()).startsWith("Verify Request:");
  }

  private static ExecutionResult verifyRequest(BpmClient bpmClient, ExecutionResult previousResult, String role) {
    ExecutionResult result = bpmClient.start()
        .task(previousResult.workflow().activeTask().responsibleRole(role))
        .as().role(role).execute();
    assertThat(result.workflow().executedTask().getState()).isIn(TaskState.DONE, TaskState.READY_FOR_JOIN);
    return result;
  }

  private static ProcurementRequest acceptRequest(BpmClient bpmClient, ExecutionResult previousResult) {
    ExecutionResult result = bpmClient.start()
        .task(previousResult.workflow().activeTask().name().contains("Accept Request:"))
        .as().role(Role.EXECUTIVE_MANAGER).execute();
    assertThat(result.workflow().executedTask().getState()).isIn(TaskState.DONE);
    return result.data().last();
  }
}
