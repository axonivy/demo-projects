package ch.ivyteam.ivy.wfdemo.maintenance;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.workflow.CaseState;

public class CaseChore {

  private static final long ONE_YEAR = 365;

  public static void cleanup() {
    Instant outdated = Instant.now().minus(retentionDays(), ChronoUnit.DAYS);

    Ivy.wf().getCaseQueryExecutor().createCaseQuery()
        .where().state().isIn(CaseState.DONE, CaseState.DESTROYED)
        .and().endTimestamp().isLowerThan(new Date(outdated.toEpochMilli()))
        .executor()
        .resultsPaged()
        .forEach(caze -> {
          Ivy.log().debug("cleaning completed case " + caze);
          Ivy.wf().deleteCompletedCase(caze);
        });
  }

  private static long retentionDays() {
    long days = ONE_YEAR;
    String daysRaw = Ivy.var().get("maintenance.caze.cleanup.days");
    if (daysRaw != "") {
      days = Long.parseLong(daysRaw);
    }
    return days;
  }

}
