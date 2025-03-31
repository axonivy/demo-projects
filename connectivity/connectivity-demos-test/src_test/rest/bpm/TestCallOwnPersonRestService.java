package rest.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.Data;
import com.axonivy.connectivity.Person;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.ExecutionResult;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
public class TestCallOwnPersonRestService {

  @Test
  void listPersons(BpmClient bpmClient) {
    ExecutionResult result = bpmClient.start().process("rest/callOwnPersonRestService/listPersons.ivp").execute();
    assertThat(result).isNotNull();

    Data data = result.data().last();
    List<Person> persons = data.getPersons();
    assertThat(persons).hasSize(4);
    assertThat(persons).extracting("firstname").contains("Renato", "Bruno", "Reguel", "Reto");
  }

  @Test
  void listServiceOptions(BpmClient bpmClient) {
    ExecutionResult result = bpmClient.start().process("rest/callOwnPersonRestService/listServiceOptions.ivp").execute();
    assertThat(result).isNotNull();

    Data data = result.data().last();
    String response = data.getResult();
    assertThat(response).isNotBlank().contains("<resource path=\"persons\">");
  }

}
