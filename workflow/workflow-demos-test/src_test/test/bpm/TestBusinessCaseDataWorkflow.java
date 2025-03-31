package test.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmElement;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class TestBusinessCaseDataWorkflow {
  private static final BpmProcess PROCESS = BpmProcess.name("Workflow");
  private static final BpmElement CREATE_START = PROCESS.elementName("create.ivp");
  private static final BpmElement HD_INTERVIEW_1 = PROCESS.element().fieldId("f3");
  private static final BpmElement HD_INTERVIEW_2 = PROCESS.element().fieldId("f7");
  private static final BpmElement TASK_SWITCH = PROCESS.element().fieldId("f5");
  private static final BpmElement END_TASK = PROCESS.element().fieldId("f1");

  @Test
  void create_anonymous(BpmClient bpmClient) {
    bpmClient.mock().element(HD_INTERVIEW_1).withNoAction();
    bpmClient.mock().element(HD_INTERVIEW_2).withNoAction();
    ExecutionResult result = bpmClient.start().process(CREATE_START).executeAndIgnoreBpmError();
    assertThat(result.bpmError()).isNotNull();
    assertThat(result.bpmError().getErrorCode()).isEqualTo("ivy:security:forbidden");
  }

  @Test
  void create_checkElements(BpmClient bpmClient) {
    bpmClient.mock().element(HD_INTERVIEW_1).withNoAction();
    bpmClient.mock().element(HD_INTERVIEW_2).withNoAction();
    ExecutionResult result = bpmClient.start().process(CREATE_START).as().everybody().execute();
    assertThat(result.history().elements()).containsExactly(CREATE_START, HD_INTERVIEW_1, TASK_SWITCH);
    assertThat(result.workflow().anyActiveTask()).isPresent();
    result = bpmClient.start().anyActiveTask(result).execute();
    assertThat(result.history().elements()).containsExactly(TASK_SWITCH, HD_INTERVIEW_2, END_TASK);
    assertThat(result.workflow().anyActiveTask()).isEmpty();
  }

}
