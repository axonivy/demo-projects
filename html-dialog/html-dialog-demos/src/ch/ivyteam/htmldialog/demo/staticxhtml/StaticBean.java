package ch.ivyteam.htmldialog.demo.staticxhtml;

import ch.ivyteam.ivy.application.app.Application;
import ch.ivyteam.ivy.environment.Ivy;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class StaticBean {

  String text = "I was sent from the ManagedBean.";

  public String getText() {
    return text;
  }

  public void buttonAction() {
    text = Ivy.session().getSessionUserName() + " clicked the button in application '"
        + Application.current().name() + "'.";
  }
}
