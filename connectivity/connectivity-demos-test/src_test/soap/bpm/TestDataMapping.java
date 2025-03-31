package soap.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.soap.DataMappingData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;

@IvyProcessTest(enableWebServer = true)
public class TestDataMapping {

  public interface Smartbear {
    String ENDPOINT_URI_KEY = "WebServiceClients.smartbearTests.Endpoints.SampleWebServiceSoap12";
    String MOCK_SERVICE = "http://test-webservices.ivyteam.io:7086/mockSampleWebServiceSoap12";
  }

  @Test
  void resolveToCache(BpmClient bpmClient, AppFixture fixture) {
    fixture.config(Smartbear.ENDPOINT_URI_KEY,
        Smartbear.MOCK_SERVICE);

    var result = bpmClient.start().process("soap/client/dataMapping/resolveToCache.ivp").execute();
    assertThat(result).isNotNull();

    DataMappingData data = result.data().last();
    assertThat(data.getTime().toJavaDate()).isBetween(
        Instant.now().minus(1, ChronoUnit.DAYS), Instant.now().plus(1, ChronoUnit.DAYS));
  }

  @Test
  void mapComplexData(BpmClient bpmClient) {
    var result = bpmClient.start().process("soap/client/dataMapping/mapComplexData.ivp").execute();
    assertThat(result).isNotNull();

    DataMappingData data = result.data().last();
    assertThat(data.getPerson().getId()).hasSize(36);
  }
}
