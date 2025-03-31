package soap.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.axonivy.connectivity.soap.PersonClientData;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;

@IvyProcessTest(enableWebServer = true)
public class TestPersonClient {
  @Test
  void read_add_delete(BpmClient bpmClient) {
    var result = bpmClient.start().process("soap/client/personClient/read.ivp").execute();
    assertThat(result).isNotNull();

    var data = (PersonClientData) result.data().last();
    var number = data.getPersons().size();
    assertThat(data.getPersons())
        .anySatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Reguel"))
        .anySatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Bruno"))
        .anySatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Reto"))
        .anySatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Markus"))
        .anySatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Renato"));

    bpmClient.start().process("soap/client/personClient/add.ivp").execute();
    result = bpmClient.start().process("soap/client/personClient/read.ivp").execute();
    data = (PersonClientData) result.data().last();
    assertThat(data.getPersons())
        .hasSize(number + 1)
        .anySatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Joshua"));

    result = bpmClient.start().process("soap/client/personClient/delete.ivp").execute();
    data = (PersonClientData) result.data().last();
    assertThat(data.getPersons())
        .hasSize(number)
        .noneSatisfy(person -> assertThat(person.getFirstname()).isEqualTo("Joshua"));
  }
}
