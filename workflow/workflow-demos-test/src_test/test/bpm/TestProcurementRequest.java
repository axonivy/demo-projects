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
 * Tests the ProcurementRequest using the {@link BpmClient} testing API and mocking elements:
 * <code>workflow-demos/processes/Humantask/ProcurementRequestParallel.mod</code>
 *
 * @author rew
 * @author rwei
 * @since Mar 3, 2020
 */
@IvyProcessTest
class TestProcurementRequest {
  /** Processes under test */
  private static final BpmProcess PROCUREMENT_PROCESS = BpmProcess.name("ProcurementRequestParallel");

  /** HTML Dialogs under test */
  private interface HtmlDialog {
    BpmElement ENTER_REQUEST = PROCUREMENT_PROCESS.elementName("Enter Request");
    BpmElement VERIFY_REQUEST_TEAM_LEADER = PROCUREMENT_PROCESS.element().name().contains("by Team Leader");
    BpmElement VERIFY_REQUEST_MANAGER = PROCUREMENT_PROCESS.element().name().contains("by Manager");
    BpmElement ACCEPT_REQUEST = PROCUREMENT_PROCESS.elementName("Accept Request");
  }

  private interface Role {
    String MANAGER = "Manager";
    String TEAMLEADER = "Teamleader";
    String EXECUTIVE_MANAGER = "Executive Manager";
  }

  /**
   * Manager and Team Leader do not verify
   */
  @Test
  void approvalTask_notVerified_byBoth(BpmClient bpmClient) {
    ExecutionResult result = createProcurrementRequest(bpmClient);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_TEAM_LEADER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkTeamLeader(false));
    verifyRequest(bpmClient, result, Role.TEAMLEADER);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_MANAGER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkManager(false));
    ExecutionResult verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    executeSystemTask(bpmClient, verifyResult);
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  /**
   * Manager does not and Team Leader does verify
   */
  @Test
  void approvalTask_notVerified_byManager(BpmClient bpmClient) {
    ExecutionResult result = createProcurrementRequest(bpmClient);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_TEAM_LEADER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkTeamLeader(true));
    verifyRequest(bpmClient, result, Role.TEAMLEADER);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_MANAGER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkManager(false));
    ExecutionResult verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    executeSystemTask(bpmClient, verifyResult);
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  /**
   * Manager does and Team Leader does not verify
   */
  @Test
  void approvalTask_notVerified_byTeamLeader(BpmClient bpmClient) {
    ExecutionResult result = createProcurrementRequest(bpmClient);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_TEAM_LEADER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkTeamLeader(false));
    verifyRequest(bpmClient, result, Role.TEAMLEADER);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_MANAGER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkManager(true));
    ExecutionResult verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    executeSystemTask(bpmClient, verifyResult);
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  /**
   * Manager and Team Leader do verify but Executive Manager does not accept
   */
  @Test
  void approvalTask_verified_notAccepted(BpmClient bpmClient) {
    ExecutionResult result = createProcurrementRequest(bpmClient);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_TEAM_LEADER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkTeamLeader(true));
    verifyRequest(bpmClient, result, Role.TEAMLEADER);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_MANAGER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkManager(true));
    ExecutionResult verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    executeSystemTask(bpmClient, verifyResult);

    bpmClient.mock().element(HtmlDialog.ACCEPT_REQUEST)
        .with(ProcurementRequest.class, (in, out) -> out.setAccepted(false));
    ProcurementRequest request = acceptRequest(bpmClient, verifyResult);

    assertThat(request.getAccepted()).isFalse();
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  /**
   * Manager and Team Leader do verify and Executive Manager does accept
   */
  @Test
  void approvalTask_verified_accepted(BpmClient bpmClient) {
    ExecutionResult result = createProcurrementRequest(bpmClient);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_TEAM_LEADER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkTeamLeader(true));
    verifyRequest(bpmClient, result, Role.TEAMLEADER);

    bpmClient.mock().element(HtmlDialog.VERIFY_REQUEST_MANAGER)
        .with(ProcurementRequest.class, (in, out) -> out.setDataOkManager(true));
    ExecutionResult verifyResult = verifyRequest(bpmClient, result, Role.MANAGER);

    executeSystemTask(bpmClient, verifyResult);

    bpmClient.mock().element(HtmlDialog.ACCEPT_REQUEST).with(ProcurementRequest.class, (in, out) -> out.setAccepted(true));
    ProcurementRequest request = acceptRequest(bpmClient, verifyResult);

    assertThat(request.getAccepted()).isTrue();
    assertThat(verifyResult.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }

  private static ExecutionResult createProcurrementRequest(BpmClient bpmClient) {
    bpmClient.mock().element(HtmlDialog.ENTER_REQUEST).with(in -> newComputer());
    ExecutionResult result = bpmClient
        .start().process(PROCUREMENT_PROCESS)
        .as().user("ldv")
        .execute();

    assertThat(result).isNotNull();
    assertProcurementInitTasks(result);
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

  private static void assertProcurementInitTasks(ExecutionResult result) {
    TaskSelector tasks = result.workflow().activeTask();
    assertThat(result.workflow().activeTasks()).hasSize(2);
    assertThat(tasks.responsibleRole(Role.TEAMLEADER).get().getName()).startsWith("Verify Request:");
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

  private static void executeSystemTask(BpmClient client, ExecutionResult previousResult) {
    client.start().task(previousResult.workflow().activeTask().system())
        .as().systemUser().execute();
  }
}
