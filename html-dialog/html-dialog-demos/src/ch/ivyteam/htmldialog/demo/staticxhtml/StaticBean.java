package ch.ivyteam.htmldialog.demo.staticxhtml;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.environment.Ivy;

@Named
@RequestScoped
public class StaticBean {

  String text = "I was sent from the ManagedBean.";

  public String getText() {
    return text;
  }

  public void buttonAction() {
    text = Ivy.session().getSessionUserName() + " clicked the button in application '"
        + IApplication.current().getName() + "'.";
  }
}
