package test.bpm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.ICase;

@IvyProcessTest
public class TestCaseCleanup {

  private static final BpmProcess PROCUREMENT_PROCESS = BpmProcess.name("HouseKeeper");

  @Test
  void deleteOldCases(BpmClient bpmClient, AppFixture app) {
    createCleanableCases(bpmClient);

    app.var("maintenance.caze.cleanup.days", "0");
    bpmClient.start().process(PROCUREMENT_PROCESS.elementName("simulate"))
        .execute();

    assertThat(cleanable())
        .as("cases were removed forever")
        .isEmpty();
  }

  private void createCleanableCases(BpmClient bpmClient) {
    bpmClient.start()
        .process(PROCUREMENT_PROCESS.elementName("createOldCases"))
        .execute();
    var doneCases = cleanable();
    assertThat(doneCases)
        .as("created system cases (for removal)")
        .hasSize(10);
    doneCases.forEach(caze -> {
      bpmClient.start()
          .task(caze.getFirstTask())
          .as().systemUser()
          .execute(); // pickup -> done
    });
  }

  private List<ICase> cleanable() {
    return Ivy.wf().getCaseQueryExecutor().createCaseQuery()
        .where().name().isLike("cleanable case%")
        .executor()
        .results();
  }

}
