package soap.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.soap.client.CustomLogFeature;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.security.ISession;

@IvyProcessTest(enableWebServer = true)
public class TestClientedInterceptedPersonService {

  @Test
  void customLogInterceptor(BpmClient bpmClient, ISession session) {
    assertThat((String) session.getAttribute("lastExchange"))
        .isNull();

    bpmClient.start()
        .process("soap/client/interceptedService/clientIntercept.ivp")
        .as().session(session)
        .execute();

    assertThat((String) session.getAttribute("lastExchange"))
        .as("session attrs set by " + CustomLogFeature.class)
        .isNotBlank();
  }
}
