package ch.ivyteam.htmldialog.server.test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.Selenide;

public class ProcessUtil {

  public static void startProcess(String pathToIvp) {
    Selenide.open(EngineUrl.createProcessUrl("/html-dialog-demos/" + pathToIvp));
    $(By.id("menuform")).shouldBe(visible);
  }

  public static void startOfflineProcess() {
    Selenide.open(
        EngineUrl.createProcessUrl("/html-dialog-demos/150425B095B4FB54/ClientSideValidationDemo.ivp"));
  }
}
