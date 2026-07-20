package workflow.trigger;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;

@IvyWebTest
class WebTestCreateNewEmployeeIT {

  @Test
  void createNewEmployee() {
    open(EngineUrl.createProcessUrl("/workflow-demos-test/1537FF3C3382D47F/start.ivp"));

    open(EngineUrl.create().path("login").toUrl());
    $(By.id("loginForm:userName")).shouldBe(visible).sendKeys("hb");
    $(By.id("loginForm:password")).sendKeys("hb");
    $(By.id("loginForm:login")).click();
    $(By.id("sessionUserName")).shouldBe(text("hb"));
    
    open(EngineUrl.createProcessUrl("/workflow-demos/15254CF1CE56AE72/start.ivp"));
    $("#form\\:employeeName").sendKeys("Charlie");
    $("#form\\:email").sendKeys("charlie@xyz.com");
    $("#form\\:employeeDateOfBirth_input").sendKeys("26.12.1985");
    $("#ui-datepicker-div").shouldBe(visible);
    $("#form\\:employeeName").click();
    $("#ui-datepicker-div").shouldNotBe(visible);
    $("#form\\:needsParkingLot").click();
  }
}
