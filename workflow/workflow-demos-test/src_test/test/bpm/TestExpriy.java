package test.bpm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.workflow.CaseState;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.WorkflowPriority;
import ch.ivyteam.ivy.workflow.task.responsible.Responsible;

@IvyProcessTest
public class TestExpriy {
  private static final Date YESTERDAY = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));

  private static final BpmProcess PROCUREMENT_PROCESS = BpmProcess.name("Expiry");

  private static final BpmElement EXPIRY_HIGHER_PRIO = PROCUREMENT_PROCESS.elementName("createExpriyTaskHigherPrio.ivp");
  private static final BpmElement EXPIRY_OTHER_ROLE = PROCUREMENT_PROCESS.elementName("createExpiryTaskOtherRole.ivp");
  private static final BpmElement EXPIRY_ERROR_TASK = PROCUREMENT_PROCESS.elementName("createExpriyErrorTask.ivp");

  @Test
  void runProcessWithoutExpire(BpmClient bpmClient) {
    ExecutionResult result = startProcess(bpmClient, EXPIRY_HIGHER_PRIO);
    bpmClient.start().anyActiveTask(result).execute();
    assertCaseIsDone(result);
  }

  @Test
  void expriyWithHigherPrio(BpmClient bpmClient) {
    ExecutionResult result = startProcess(bpmClient, EXPIRY_HIGHER_PRIO);

    ITask nextTask = getNextTask(result);
    assertThat(nextTask.getPriority()).isEqualTo(WorkflowPriority.NORMAL);
    nextTask.setExpiryTimestamp(YESTERDAY);
    assertThat(nextTask.getPriority()).isEqualTo(WorkflowPriority.HIGH);

    bpmClient.start().task(nextTask).as().everybody().execute();
    assertCaseIsDone(result);
  }

  @Test
  void expriyWithOtherRole(BpmClient bpmClient) {
    ExecutionResult result = startProcess(bpmClient, EXPIRY_OTHER_ROLE);

    var nextTask = getNextTask(result);
    assertThat(nextTask.responsibles().all())
        .extracting(Responsible::name)
        .containsOnlyOnce("Everybody");

    nextTask.setExpiryTimestamp(YESTERDAY);
    assertThat(nextTask.responsibles().all())
        .extracting(Responsible::name)
        .containsOnlyOnce("Manager");

    assertThatThrownBy(() -> bpmClient.start().task(nextTask).as().everybody().execute())
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("is not permitted");

    bpmClient.start().task(nextTask).as().role("Manager").execute();
    assertCaseIsDone(result);
  }

  @Test
  void expriyWithNewErrorTask(BpmClient bpmClient) {
    ExecutionResult result = startProcess(bpmClient, EXPIRY_ERROR_TASK);

    ITask nextTask = getNextTask(result);
    nextTask.setExpiryTimestamp(YESTERDAY);
    assertThat(nextTask.getState()).isEqualTo(TaskState.DESTROYED);

    ITask errorTask = getNextTask(result);
    assertThatThrownBy(() -> bpmClient.start().task(errorTask).as().everybody().execute())
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("is not permitted");

    bpmClient.start().task(errorTask).as().systemUser().execute();
    assertCaseIsDone(result);
  }

  private ExecutionResult startProcess(BpmClient bpmClient, BpmElement startElement) {
    return bpmClient.start().process(startElement)
        .as().everybody().execute();
  }

  private ITask getNextTask(ExecutionResult result) {
    ITask nextTask = result.workflow().anyActiveTask().get();
    assertThat(nextTask.getPriority()).isEqualTo(WorkflowPriority.NORMAL);
    return nextTask;
  }

  private void assertCaseIsDone(ExecutionResult result) {
    assertThat(result.workflow().activeCase().getState()).isEqualTo(CaseState.DONE);
  }
}
