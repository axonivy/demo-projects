package workflow.businessdata;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static test.web.ProcessUtil.checkEndPage;
import static test.web.ProcessUtil.startProcess;
import static test.web.ProcessUtil.startTestProcess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.Selenide;

@IvyWebTest
class WebTestBusinessCaseDataWorkflowIT {

  @BeforeEach
  void cleanElasticSearch() {
    startTestProcess("1537FF3C3382D47F/clearDemoDossiers.ivp?embedInFrame");
    checkEndPage();
  }

  @Test
  void interview() {
    login();

    startProcess("16EF567002B146F2/create.ivp");
    $(By.id("form:interviewFirstName")).shouldBe(visible).sendKeys("Hans");
    $(By.id("form:interviewLastName")).sendKeys("Muster");
    $(By.id("form:saveAndPark")).click();

    $(By.id("tasksForm:tasks:0:taskName")).shouldHave(text("6.1: Save BusinessCaseData When Park Task")).click();
    $(By.id("taskName")).shouldHave(text("6.1: Save BusinessCaseData When Park Task"));
    $(By.id("actionMenuForm:taskStartBtn")).shouldBe(visible).click();
    Selenide.switchTo().frame("iFrame");
    $(By.id("form:interviewFirstName")).shouldBe(visible, exactValue("Hans"));
    $(By.id("form:interviewLastName")).shouldBe(exactValue("Muster")).sendKeys("mann");
    $(By.id("form:interviewConversation")).sendKeys("This is an interview");
    $(By.id("form:interviewDate_input")).sendKeys("13.12.2025");

    $(By.id("form:proceed")).click();

    startProcess("16EF567002B146F2/browse.ivp");
    sleep(1000); // wait for elastic search
    $$("button").find(text("Search")).shouldBe(visible, enabled).click();
    $$("#interviewTable tbody tr").first().shouldHave(text("Hans"),
        text("Mustermann"),
        text("This is an interview"),
        text("13.12.2025"));
  }

  private void login() {
    if (!EngineUrl.isDesigner()) {
      open(EngineUrl.create().path("login").toUrl());
      $(By.id("loginForm:userName")).shouldBe(visible).sendKeys("hb");
      $(By.id("loginForm:password")).sendKeys("hb");
      $(By.id("loginForm:login")).click();
      $(By.id("sessionUserName")).shouldBe(text("hb"));
    }
  }
}
