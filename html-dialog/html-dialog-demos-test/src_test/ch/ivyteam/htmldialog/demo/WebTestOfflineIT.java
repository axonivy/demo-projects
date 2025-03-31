package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startOfflineProcess;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

@IvyWebTest
public class WebTestOfflineIT {

  @Test
  public void testClientSideValidationExists() {
    startOfflineProcess();
    $(By.id("form:firstName")).shouldBe(visible).sendKeys("Someone");
    $(By.id("form:personName")).sendKeys("Else");
    $(By.id("form:personDateOfBirth")).sendKeys("1990-01-01");
    $(By.id("form:addressZip")).sendKeys("8000");
    $(By.id("form:addressCity")).sendKeys("Zürich");
    $(By.id("form:addressCountry")).sendKeys("CH");
    $(By.id("form:email")).sendKeys("someone.else@admin.ch");
    $(By.id("form:timeOfArrival")).sendKeys("13:15");
    $(By.id("form:proceed")).click();
    $(By.id("welcomeText")).shouldHave(text("Welcome to Axon Ivy Html Dialog Demos"));
  }

  @Test
  public void testClientSideValidationWorks() {
    startOfflineProcess();
    $(By.id("form:firstName")).shouldBe(visible).sendKeys("Someone");
    $(By.id("form:personName")).sendKeys("El");
    $(By.id("form:personDateOfBirth")).sendKeys("1990-01-01");
    $(By.id("form:addressZip")).sendKeys("999");
    $(By.id("form:addressCity")).sendKeys("Zürich");
    $(By.id("form:addressCountry")).sendKeys("CH");
    $(By.id("form:email")).sendKeys("someone.else@admin.ch");
    $(By.id("form:timeOfArrival")).sendKeys("13:15");
    Selenide.$$(".ui-message-error").find(Condition.attribute("data-target", "form:personName"))
        .shouldHave(text("Length is less than allowable minimum of '3'"));
    Selenide.$$(".ui-message-error").find(Condition.attribute("data-target", "form:addressZip"))
        .shouldHave(text("Specified attribute is not between the expected values of 1000 and 9999."));
  }
}
