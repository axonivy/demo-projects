package rest.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.test.CreateApprovalData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest
public class TestApprovalCallSub {
  @Test
  public void callSub(BpmClient bpmClient) {
    ExecutionResult result = bpmClient.start()
        .process("rest/testApprovalCreation/test.ivp")
        .as().user("restUser")
        .execute();
    CreateApprovalData data = result.data().last();
    assertThat(data.getCreatedTask().getName())
        .isEqualTo("Approve request from restUser");
  }
}
