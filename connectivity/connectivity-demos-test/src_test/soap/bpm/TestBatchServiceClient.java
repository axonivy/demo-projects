package soap.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.soap.BatchServiceClientData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
public class TestBatchServiceClient {
  @Test
  void batchClient(BpmClient bpmClient) {
    var result = bpmClient.start()
        .process("soap/client/batchServiceClient/callLongRunningBatchOperation.ivp")
        .withParam("blockSeconds", "1") // reduce actual blocking time
        .execute();
    BatchServiceClientData data = result.data().last();
    assertThat(data.getMessage())
        .as("remote service exposes blocking time")
        .startsWith("batch processed in ");
  }
}
