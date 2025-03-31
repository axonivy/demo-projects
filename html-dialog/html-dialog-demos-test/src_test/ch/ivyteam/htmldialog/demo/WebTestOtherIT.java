package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

@IvyWebTest
public class WebTestOtherIT {

  @Test
  public void testHtmlJsfDemo() {
    startProcess("145D1862CF17F2C9/Html5Demo.ivp");
    $(By.id("passthrough:email")).shouldHave(attribute("placeholder", "Enter your email"));
    $(By.id("passthrough:name")).shouldHave(attribute("placeholder", "Enter your name"));
    $(By.id("paintHtml:name")).shouldBe(visible).sendKeys("paintHtml");
    $(By.id("paintHtml:buttonShow")).shouldBe(visible, enabled).click();
    $(By.id("paintHtml:growl_container")).shouldHave(text("Hello paintHtml"));
  }

  @Test
  public void testHtmlBootstrapDemo() {
    startProcess("145D1862CF17F2C9/Html5BootstrapDemo.ivp");
    $(By.id("Form:Name")).shouldBe(visible).sendKeys("name");
    $(By.id("Form:Email")).sendKeys("email@ivyteam.ch");
    ((JavascriptExecutor) WebDriverRunner.getWebDriver())
        .executeScript("document.getElementById('Form:Birthdate').setAttribute('value', '2016-01-01')");
    $(By.id("Form:Captcha")).sendKeys("21");
    $(By.id("Submit")).click();
    $(By.id("captchaError")).shouldHave(text("Error!"));
    $(By.id("Form:Captcha")).shouldBe(visible).clear();
    $(By.id("Form:Captcha")).sendKeys("42");
    $(By.id("Submit")).click();
    $(By.id("messageSent")).shouldHave(text("Hey name! Your message has been sent."));
  }

  @Test
  public void testAjaxMethodCall() {
    startProcess("145D1862CF17F2C9/MethodCallWithAjaxDemo.ivp");
    $(By.id("hello")).shouldBe(visible).click();
    $(By.id("result")).shouldHave(text("Welcome World"));
    $(By.id("closeDialog")).click();
    $(By.id("welcomeText")).shouldHave(text("Welcome to Axon Ivy Html Dialog Demos"));
  }

  @Test
  public void testManagedBean() {
    startProcess("145D1862CF17F2C9/ManagedBeanDemo.ivp");
    String managedBeanPoperty = "this is immortal!";
    $(By.id("beanForm:descriptionProperty")).shouldBe(visible).clear();
    $(By.id("beanForm:descriptionProperty")).sendKeys(managedBeanPoperty);
    $(By.id("beanForm:buttonSend")).click();
    openAndValidate(managedBeanPoperty);
    Selenide.refresh();
    openAndValidate(managedBeanPoperty);
  }

  private void openAndValidate(String managedBeanPoperty) {
    startProcess("145D1862CF17F2C9/ManagedBeanDemo.ivp");
    $(By.id("beanForm:descriptionProperty")).shouldBe(exactValue(managedBeanPoperty));
  }
}
