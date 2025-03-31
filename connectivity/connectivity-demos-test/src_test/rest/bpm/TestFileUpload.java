package rest.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.Data;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest(enableWebServer = true)
class TestFileUpload {
  @BeforeAll
  static void setUp(AppFixture fixture) {
    fixture.config("RestClients.jsonPlaceholder.Url", "http://jsonplaceholder:3000/");
    fixture.config("RestClients.openApiService.Url", "http://test-webservices.ivyteam.io:8090/api/v3");
  }

  @Test
  void fileUpload_octetStream(BpmClient bpmClient) {
    ExecutionResult result = bpmClient.start().process("rest/fileUpload/upAndDown.ivp").execute();
    assertThat(result).isNotNull();

    Data data = result.data().last();
    assertThat(data.getResult()).endsWith(".png");
  }

  @Test
  void fileUpload_multiPart(BpmClient bpmClient) {
    ExecutionResult result = bpmClient.start().process("rest/fileUpload/fileMultipart.ivp").execute();
    assertThat(result).isNotNull();

    Data data = result.data().last();
    assertThat(data.getResult()).startsWith("File was uploaded succesfully");
  }
}
