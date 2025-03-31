package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.codeborne.selenide.Selenide;

@IvyWebTest
public class WebTestComponentIT {

  @Test
  public void testComponentDemo() {
    startProcess("145D1849FACF0EAA/ComponentDemo.ivp");
    $(By.id("demoForm:personComponent:firstname")).shouldBe(visible).sendKeys("testFirstName");
    $(By.id("demoForm:personComponent:lastname")).sendKeys("testName" + Keys.ENTER);
    $(By.id("demoForm:result")).shouldHave(text("testFirstName"), text("testName"));
    fillAddressComponent("delivery");
    fillAddressComponent("billing");
  }

  private void fillAddressComponent(String prefix) {
    $(By.id("demoForm:personComponent:" + prefix + "AddressComponent:street")).sendKeys(
        prefix + "Street");
    $(By.id("demoForm:personComponent:" + prefix + "AddressComponent:country")).sendKeys(
        prefix + "Country" + Keys.ENTER);
    $(By.id("demoForm:result")).shouldHave(text(prefix + "Street"), text(prefix + "Country"));
  }

  @Test
  public void testCallbackDemo() {
    startProcess("145D1849FACF0EAA/ComponentEventListenerDemo.ivp");
    $(By.id("componentForm:detailListComponent:personsTable")).shouldBe(visible);
    Selenide.executeJavaScript("PF('personsTable').selectRow(1, false);");
    $(By.id("componentForm:selectionResult_container")).shouldHave(text("Person selected"), text("Peter"));
    Selenide.executeJavaScript("PF('personsTable').selectRow(3, false);");
    $(By.id("componentForm:selectionResult_container")).shouldHave(text("Person selected"), text("Bruno"));
    $(By.id("componentForm:detailListComponent:fireEventButton")).shouldBe(visible, enabled).click();
    $(By.id("componentForm:selectionResult_container")).shouldHave(text("Fire event!"));
  }

  @Test
  public void testPartialUpdate() {
    startProcess("145D1849FACF0EAA/PartialUpdateDemo.ivp");
    increaseCounter("A", 2);
    assertPanelValue(2, 0);
    increaseCounter("B", 1);
    assertPanelValue(2, 3);
    $(By.id("Form:increaseAllButton")).click();
    assertPanelValue(4, 4);
  }

  private void increaseCounter(String panel, int increaseClicks) {
    for (int i = 1; i <= increaseClicks; i++) {
      $(By.id("Form:increaseP" + panel + "Button"))
          .shouldBe(visible, enabled).click();
    }
  }

  private void assertPanelValue(Integer expectedValueA, Integer expectedValueB) {
    $(By.id("Form:counterPanel_1_content")).shouldHave(text(expectedValueA.toString()));
    $(By.id("Form:counterPanel_2_content")).shouldHave(text(expectedValueB.toString()));
  }
}
