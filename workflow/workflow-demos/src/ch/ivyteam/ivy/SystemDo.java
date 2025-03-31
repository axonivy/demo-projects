package ch.ivyteam.ivy;

import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;

public class SystemDo {
  public static void setCustomVarCharField1(final ITask task, final String val)
      throws Exception {
    Sudo.run(() -> task.customFields().stringField("CustomVarCharField1").set(val));
  }

  public static void setCaseName(final ICase icase, final String val)
      throws Exception {
    Sudo.run(() -> icase.setName(val));
  }

  public static void setCaseDescription(final ICase icase, final String val)
      throws Exception {
    Sudo.run(() -> icase.setDescription(val));
  }

  public static void setProcess(final ICase icase, final String code, final String val)
      throws Exception {
    Sudo.run(() -> {
      icase.customFields().stringField("ProcessCode").set(code);
      icase.customFields().stringField("ProcessName").set(val);
    });
  }
}
