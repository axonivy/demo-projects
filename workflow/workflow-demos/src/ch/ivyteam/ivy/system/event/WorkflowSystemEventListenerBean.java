package ch.ivyteam.ivy.system.event;

import java.util.EnumSet;

import ch.ivyteam.ivy.event.ISystemEventListener;
import ch.ivyteam.ivy.event.SystemEvent;
import ch.ivyteam.ivy.event.SystemEventCategory;
import ch.ivyteam.ivy.process.eventstart.AbstractProcessStartEventBean;
import ch.ivyteam.ivy.process.eventstart.IProcessStartEventBeanRuntime;
import ch.ivyteam.ivy.process.extension.ProgramConfig;
import ch.ivyteam.ivy.process.extension.ui.ExtensionUiBuilder;
import ch.ivyteam.ivy.process.extension.ui.UiEditorExtension;
import ch.ivyteam.ivy.service.ServiceException;
import ch.ivyteam.ivy.workflow.WorkflowSystemEvent;

public class WorkflowSystemEventListenerBean extends AbstractProcessStartEventBean {

  private static final String WORKFLOW_SYSTEM_EVENT_KEY = "workflowSystemEventKey";

  private String workflowSystemEvent;

  private ISystemEventListener listener;

  public WorkflowSystemEventListenerBean() {
    super(WorkflowSystemEventListenerBean.class.getSimpleName(), "Starts a process when a workflow system event is detected");
  }

  @Override
  public void initialize(IProcessStartEventBeanRuntime eventRuntime, ProgramConfig programConfig) {
    super.initialize(eventRuntime, programConfig);
    this.workflowSystemEvent = programConfig.get(WORKFLOW_SYSTEM_EVENT_KEY);
    this.listener = new WorkflowSystemEventListener();
    eventRuntime.poll().disable();
  }

  @Override
  public void start() throws ServiceException {
    super.start();
    getEventBeanRuntime().getProcessModelVersion().getApplication()
        .addSystemEventListener(EnumSet.of(SystemEventCategory.WORKFLOW), this.listener);
  }

  @Override
  public void stop() throws ServiceException {
    getEventBeanRuntime().getProcessModelVersion().getApplication()
        .removeSystemEventListener(EnumSet.of(SystemEventCategory.WORKFLOW), this.listener);
    super.stop();
  }

  private class WorkflowSystemEventListener implements ISystemEventListener {

    @Override
    public void handleSystemEvent(SystemEvent<?> event) {
      if (!event.getName().equals(workflowSystemEvent)) {
        return;
      }
      getEventBeanRuntime().processStarter()
          .withReason("System event '" + workflowSystemEvent + "' detected")
          .withParameter("eventParameters", event.getParameter())
          .startAsync();
    }
  }

  public static class Editor extends UiEditorExtension {

    @Override
    public void initUiFields(ExtensionUiBuilder ui) {
      ui.label("System event to listen for").create();
      ui.textField(WORKFLOW_SYSTEM_EVENT_KEY).create();
      ui.label("Must be one of"
          + "\n- " + WorkflowSystemEvent.TASK_CREATED
          + "\n- " + WorkflowSystemEvent.TASK_CHANGED
          + "\n- " + WorkflowSystemEvent.CASE_CREATED
          + "\n- " + WorkflowSystemEvent.CASE_CHANGED)
          .multiline().create();
    }
  }
}
