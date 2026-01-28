package ch.ivyteam.htmldialog.demo.other;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.engine.WebAppFixture;

@IvyWebTest
public class WebTestErrorHandlingIT {

  @Test
  public void ajax_error_dialog() {
    startProcess("145D1862CF17F2C9/ErrorHandlingDemo.ivp");
    $(By.id("form:ajax")).shouldBe(visible).click();
    $(By.id("ajaxExceptionDialog_title")).shouldHave(text("Error"));
    $(By.id("ajaxExceptionDialog_content")).shouldHave(
        text("ivy:error:script"),
        text("Error id"),
        text("Process"),
        text("ErrorHandlingProcess"),
        text("Element"),
        text("throw exception <15E519280889B152-f9>"),
        text("Technical cause"),
        text("IvyScriptRuntimeException: IvyScript Runtime Exception in"),
        text("Instruction:"),
        text("(0 / 0)"),
        text("Request Uri"),
        text("/" + EngineUrl.applicationName() + "/1/faces/instances/html-dialog-demos/"),
        text("/ch.ivyteam.htmldialog.demo.other.ErrorHandling/ErrorHandling.xhtml"),
        text("Servlet"),
        text("Faces Servlet"),
        text("Application"),
        text(EngineUrl.applicationName()),
        text("Thread local values"),
        text("application"),
        text("session"),
        text("requestId"),
        text("executionContext"),
        text("SYSTEM"),
        text("pmv"),
        text("client"),
        text("hd"),
        text("ch.ivyteam.htmldialog.demo.other.ErrorHandling"),
        text("processElement"),
        text("15E519280889B152-f9"),
        text("Stack-Trace"));
  }

  @Test
  public void non_ajax_error_dialog() {
    startProcess("145D1862CF17F2C9/ErrorHandlingDemo.ivp");
    $(By.id("form:nonAjax")).shouldBe(visible).click();
    $("h1").shouldHave(text("ivy:error:script"));
    $(".exception-detail-link > a").click();
    $(".ui-dialog").shouldHave(text("ivy:error:script"),
        text("Error id"),
        text("Process"),
        text("ErrorHandlingProcess"),
        text("Element"),
        text("throw exception <15E519280889B152-f9>"),
        text("Technical cause"),
        text("IvyScriptRuntimeException: IvyScript Runtime Exception in"),
        text("Instruction:"),
        text("(0 / 0)"),
        text("Request Uri"),
        text("/" + EngineUrl.applicationName() + "/1/faces/instances/html-dialog-demos/"),
        text("/ch.ivyteam.htmldialog.demo.other.ErrorHandling/ErrorHandling.xhtml"),
        text("Servlet"),
        text("Faces Servlet"),
        text("Application"),
        text(EngineUrl.applicationName()),
        text("Request context"),
        text("application"),
        text("session"),
        text("requestId"),
        text("executionContext"),
        text("SYSTEM"),
        text("pmv"),
        text("client"),
        text("hd"),
        text("ch.ivyteam.htmldialog.demo.other.ErrorHandling"),
        text("processElement"),
        text("15E519280889B152-f9"),
        text("Stack Trace"));
  }

  @Test
  public void session_expired_withUser(WebAppFixture fixture) {
    fixture.login("demoUser1", "demoUser1");
    startProcess("145D1862CF17F2C9/ErrorHandlingDemo.ivp");
    $(By.id("form:expireSession")).shouldBe(visible).click();
    $(By.id("form:ajaxWithExpiredSession")).shouldBe(visible).click();
    $(By.id("viewExpiredExceptionDialog_title")).shouldHave(text("View or Session Expired"));
    $(By.id("viewExpiredExceptionDialog_content")).shouldHave(
        text("Custom Exception Handling for ViewExpiredException"),
        text("The view or session has expired"),
        // We are still logged in because we only invalidate the http session and not the ivy session
        text("Please restart"));
  }

  @Test
  public void session_expired_unknownSessionUser(WebAppFixture fixture) {
    fixture.login("demoUser1", "demoUser1");
    fixture.logout();
    startProcess("145D1862CF17F2C9/ErrorHandlingDemo.ivp");
    $(By.id("form:expireSession")).shouldBe(visible).click();
    $(By.id("form:ajaxWithExpiredSession")).shouldBe(visible).click();
    $(By.id("viewExpiredExceptionDialog_title")).shouldHave(text("View or Session Expired"));
    $(By.id("viewExpiredExceptionDialog_content")).shouldHave(
        text("Custom Exception Handling for ViewExpiredException"),
        text("The view or session has expired"),
        text("Please login again"));
  }
}
