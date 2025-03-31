package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.primeui.PrimeUi;

@IvyWebTest
class WebTestInputIT {

  @Test
  void form() {
    startProcess("145D18298A3E81CF/FormDemo.ivp");
    $(By.id("Form:Name")).shouldBe(visible).sendKeys("team");
    $(By.id("Form:Birthday_input")).sendKeys("14.07.2016");
    $(By.id("Form:Birthday_input")).sendKeys(Keys.TAB);
    $(By.id("Form:Mail")).sendKeys("support@axonivy.com");
    $(By.id("Form:PhoneNumber")).sendKeys("41586663455");
    $(By.id("Form:Address")).sendKeys("Baarerstrasse 12");
    $(By.id("Form:ZipCode")).sendKeys("6300");
    $(By.id("Form:City")).sendKeys("Zug");
    $(By.id("Form:Country_input")).sendKeys("Switzerland");
    $(By.id("Form:Country_input")).sendKeys(Keys.ENTER);
    $(By.id("Form:SendButton")).click();
    $(By.id("Form:msgs")).shouldHave(text("Value is required"), text("First Name"));
    $(By.id("Form:Firstname")).sendKeys("ivy");
    $(By.id("Form:SendButton")).click();
    $(By.id("Form:msgs")).shouldNotHave(text("Value is required"));
    $(By.id("outputData")).shouldHave(text("name=team, firstname=ivy"));
  }

  @Test
  void form_customProjectValidator() {
    startProcess("145D18298A3E81CF/FormDemo.ivp");
    $(By.id("Form:Mail")).shouldBe(visible).sendKeys("notValidMail[at]test.ch");
    $(By.id("Form:Mail")).shouldBe(visible, enabled).submit();
    $$(".ui-messages-error span").find(text("E-mail validation failed: invalid mail address"))
        .shouldBe(visible);
  }

  @Test
  void multiView_invoice() {
    startProcess("145D18298A3E81CF/MultiViewDemo.ivp");
    $(By.id("myForm:Name")).shouldBe(visible).sendKeys("testName");
    $(By.id("myForm:FirstName")).sendKeys("testFirstName");
    $(By.id("myForm:nextButton")).click();
    $(By.id("myForm:panel")).shouldHave(text("Payment - Invoice"));
    $(By.id("myForm:Address")).sendKeys("Baarerstrasse 13");
    $(By.id("myForm:finishButton")).click();
    waitForSummary("testName", "testFirstName");
  }

  @Test
  void multiView_creditCard() throws Exception {
    startProcess("145D18298A3E81CF/MultiViewDemo.ivp");
    $(By.id("myForm:Name")).sendKeys("team");
    $(By.id("myForm:FirstName")).sendKeys("ivy");
    PrimeUi.selectOneRadio(By.id("myForm:options")).selectItemByValue("CreditCard");
    $(By.id("myForm:nextButton")).click();
    $(By.id("myForm:panel")).shouldHave(text("Payment - Credit Card"));
    $(By.id("myForm:CreditCardNumber")).sendKeys("1234567891234567");
    $(By.id("myForm:CreditCardNumber")).sendKeys(Keys.ENTER);
    $(By.id("myForm:panel")).shouldHave(text("Payment - Credit Card Processing"));
    $(By.id("myForm:confirmVerification")).shouldHave(text("Credit card verified!"));
    $(By.id("myForm:finishButton")).click();
    waitForSummary("team", "ivy");
  }

  @Test
  void textEditor() {
    startProcess("145D18298A3E81CF/TextEditorDemo.ivp");
    $(By.id("form:update")).click();
  }

  @Test
  void enterMultipleRows() {
    startProcess("145D18298A3E81CF/EnterMultipleRowsDemo.ivp");
    $(By.id("form:playerName")).shouldBe(visible).sendKeys("Hugo");
    $(By.id("form:submit")).click();
    $$(".ui-messages-error span").find(text("At least one score is required")).shouldBe(visible);
  }

  private void waitForSummary(String name, String firstName) {
    $(By.id("myForm:panel")).shouldHave(text("Payment - Summary"));
    $(By.id("myForm:outputSummary")).shouldHave(text(firstName + " " + name));
  }
}
