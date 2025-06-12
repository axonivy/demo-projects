package ch.ivyteam.htmldialog.server.test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.Selenide;

public class ProcessUtil {

  public static void startProcess(String pathToIvp) {
    open(EngineUrl.createProcessUrl("/html-dialog-demos/" + pathToIvp));
    $(By.id("menuform")).shouldBe(visible);
  }

  public static void startOfflineProcess() {
    open(EngineUrl.createProcessUrl("/html-dialog-demos/150425B095B4FB54/ClientSideValidationDemo.ivp"));
  }

  public static void open() {
    open(EngineUrl.base());
  }

  public static void open(String url) {
    open(url, 1);
  }

  private static void open(String url, int retry) {
    if (retry >= 3) {
      throw new RuntimeException("Could not start browser instance.");
    }
    try {
      Selenide.open(url);
    } catch (TimeoutException ex) {
      System.out.println("Browser didn't respond in time, retry: " + retry);
      Selenide.closeWebDriver();
      open(url, retry++);
    }
  }
}
