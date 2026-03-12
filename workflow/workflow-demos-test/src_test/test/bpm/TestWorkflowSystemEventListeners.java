package test.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.junit.jupiter.api.Test;

import WorkflowSystemEvent.WorkflowSystemEventListenersData;
import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.security.IUser;

@IvyProcessTest
public class TestWorkflowSystemEventListeners {

  private static final BpmProcess SYSTEM_EVENT_LISTENERS_PROCESS = BpmProcess.name("WorkflowSystemEventListeners");
  private static final BpmElement NEW_TASK_ASSIGNMENTS = SYSTEM_EVENT_LISTENERS_PROCESS.elementName("Execute for new task assignments");
  private static final BpmElement TASK_DELEGATIONS = SYSTEM_EVENT_LISTENERS_PROCESS.elementName("Execute for task delegations");

  private static final BpmProcess EXPIRY_PROCESS = BpmProcess.name("Expiry");
  private static final BpmElement EXPIRY_OTHER_ROLE = EXPIRY_PROCESS.elementName("createExpiryTaskOtherRole.ivp");

  @Test
  void executions(BpmClient bpmClient, @Named("jb") IUser jamesBond) {
    List<WorkflowSystemEventListenersData> newTaskAssignmentsExecutions = new ArrayList<>();
    bpmClient.mock()
        .element(NEW_TASK_ASSIGNMENTS)
        .with(WorkflowSystemEventListenersData.class, (params, result) -> newTaskAssignmentsExecutions.add(params));

    List<WorkflowSystemEventListenersData> taskDelegationsExecutions = new ArrayList<>();
    bpmClient.mock()
        .element(TASK_DELEGATIONS)
        .with(WorkflowSystemEventListenersData.class, (params, result) -> taskDelegationsExecutions.add(params));

    var result = bpmClient.start()
        .process(EXPIRY_OTHER_ROLE)
        .as().everybody()
        .execute();
    var task = result.workflow().anyActiveTask().get();
    var taskId = task.getId();

    assertThat(newTaskAssignmentsExecutions).hasSize(1);
    assertThat(newTaskAssignmentsExecutions.get(0).getTaskIds()).containsExactly(taskId);
    assertThat(taskDelegationsExecutions).isEmpty();

    task.setActivator(jamesBond);

    assertThat(newTaskAssignmentsExecutions).hasSize(1);
    assertThat(taskDelegationsExecutions).hasSize(1);
    assertThat(taskDelegationsExecutions.get(0).getTaskIds()).containsExactly(taskId);

    task.setExpiryTimestamp(new Date());

    assertThat(newTaskAssignmentsExecutions).hasSize(1);
    assertThat(taskDelegationsExecutions).hasSize(2);
    assertThat(taskDelegationsExecutions.get(1).getTaskIds()).containsExactly(taskId);
  }
}
