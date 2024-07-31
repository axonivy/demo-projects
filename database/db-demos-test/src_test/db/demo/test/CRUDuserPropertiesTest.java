package db.demo.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.*;
import ch.ivyteam.ivy.bpm.engine.client.element.*;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import db.demos.Player;

@IvyProcessTest(enableWebServer = true)
public class CRUDuserPropertiesTest {

	private static final BpmProcess testee = BpmProcess.path("CRUDuserProperties");

	@Test
	public void callProcess(BpmClient bpmClient) {
		BpmElement startable = testee.elementName("Create.ivp");
		ExecutionResult result = bpmClient.start().process(startable).as().user("Alvin").execute();

		History history = result.history();
		assertThat(history.elementNames()).containsExactly("Create.ivp", "Create", "Read", "Update", "Delete", "");

		Player orderData = result.data().last();
		assertThat(orderData.getFavoritecolor()).isEqualTo("");
	}

}