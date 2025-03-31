package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.codeborne.selenide.Selenide;

@IvyWebTest
class WebTestDynamicDialog {
  public WebDriver driver = Selenide.webdriver().driver().getWebDriver();
  private static final int firstDialog = 1;
  private static final int secondDialog = 2;

  @BeforeEach
  void setup() {
    startProcess("145D180807C60B4B/DynamicDialog.ivp");
  }

  @Test
  void openDialog() {
    $(By.id("form:open")).shouldBe(visible).click();
    switchToDialogs(firstDialog);
    $(By.id("dialogForm:openDialog")).shouldBe(visible).click();
    $(By.id("dialogForm:openFurtherDialog")).shouldBe(visible).click();
    switchToDialogs(secondDialog);
    $(By.id("finalDialogForm:closeDialog")).shouldBe(visible).click();
    switchToDialogs(firstDialog);
    $(By.id("dialogForm:closeDialog")).shouldBe(visible).click();
  }

  @Test
  void logicAndData() {
    $(By.id("form:open")).shouldBe(visible).click();
    switchToDialogs(firstDialog);
    $(By.id("dialogForm:text")).shouldHave(text("Counter:"));
    $(By.id("dialogForm:useLogic")).shouldBe(visible).click();
    $(By.id("dialogForm:text")).shouldHave(text("Counter: 1"));
    switchToDialogs(firstDialog);
    $(By.id("dialogForm:closeDialog")).shouldBe(visible).click();
  }

  @Test
  void parameter() {
    $(By.id("form:openConfigurated")).shouldBe(visible).click();
    switchToDialogs(firstDialog);
    $(By.id("ConfiguratedDialogForm")).shouldBe(visible).shouldHave(text("Film: Harry Potter"));
    $(By.id("dialogForm:closeDialog")).shouldBe(visible).click();
    switchToForm();
    $(By.id("form:proceed")).shouldBe(visible).click();
  }

  @Test
  void configurationOptions() {
    $(By.id("form:openConfigurated")).shouldBe(visible).click();
    switchToDialogs(firstDialog);
    var widht = $(By.id("ConfiguratedDialogForm")).getSize().getWidth();
    assertThat(widht).isEqualTo(713).as("The Widht of the dialog wasn't the 713px as defined.");
    $(By.id("dialogForm:closeDialog")).shouldBe(visible).click();
    switchToForm();
    $(By.id("form:proceed")).shouldBe(visible).click();
  }

  private void switchToDialogs(int index) {
    switchToForm();
    driver.switchTo().frame(index);
  }

  private void switchToForm() {
    driver.switchTo().defaultContent();
  }
}
