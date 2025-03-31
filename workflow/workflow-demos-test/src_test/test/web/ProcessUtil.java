package test.web;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrlContaining;

import com.axonivy.ivy.webtest.engine.EngineUrl;

public class ProcessUtil {

  public static void startProcess(String pathToIvp) {
    open(EngineUrl.createProcessUrl("/workflow-demos/" + pathToIvp));
  }

  public static void startTestProcess(String pathToIvp) {
    open(EngineUrl.createProcessUrl("/workflow-demos-test/" + pathToIvp));
  }

  public static void checkEndPage() {
    webdriver().shouldHave(currentFrameUrlContaining("endedTaskId="));
  }

}
