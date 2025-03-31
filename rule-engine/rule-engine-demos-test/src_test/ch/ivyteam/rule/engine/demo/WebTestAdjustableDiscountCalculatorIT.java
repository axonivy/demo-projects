package ch.ivyteam.rule.engine.demo;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.primeui.PrimeUi;

@IvyWebTest
public class WebTestAdjustableDiscountCalculatorIT {
  private static final String MAIN_MESSAGE = "form:mainMessage";
  private static final String MEMBER_PURCHASE_AMOUNT_MESSAGE = "form:memberPurchaseAmountMessage";
  private static final String MEMBER_PURCHASE_AMOUNT = "form:memberPurchaseAmount";
  private static final String MEMBER_DISCOUNT = "form:memberDiscount";
  private static final String MEMBER_TYPE = "form:memberType";
  private static final String APPLY_DRL = "form:applyDRL";
  private static final String RULETABLE_SAVE_BUTTON = "tableform:save";
  private static final String RULETABLE_SAVED_MSG = "tableform:saved_msg";

  @BeforeEach
  public void openRuleDemo() {
    open(EngineUrl.createProcessUrl("rule-engine-demos/153EAAA7649F85DA/start2.ivp"));
  }

  @Test
  public void noAmount() {
    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(empty);
    $(By.id(APPLY_DRL)).shouldBe(visible).click();
    $(By.id(MAIN_MESSAGE)).shouldBe(text("Value is required"));
  }

  @Test
  public void changedRule() {
    setMemberType("Silver");
    assertDiscountForAmount(900, 5);

    var tableRow2 = $(By.id("tableform:table_data")).find(".ui-widget-content", 2);

    tableRow2.find(".ui-row-editor").shouldBe(visible).click();
    var discountInput = tableRow2.find(".ui-editable-column", 3).find(".ui-inputfield").shouldBe(visible);
    discountInput.sendKeys(Keys.BACK_SPACE);
    discountInput.sendKeys("7");
    tableRow2.find(".ui-row-editor-check").shouldBe(visible).click();
    $(By.id(RULETABLE_SAVE_BUTTON)).shouldBe(visible).click();
    $(By.id(RULETABLE_SAVED_MSG)).shouldBe(visible);

    assertDiscountForAmount(900, 7);
  }

  @Test
  public void amountTooBig() {
    setMemberType("Silver");
    setPurchaseAmount(10000);
    $(By.id(APPLY_DRL)).shouldBe(visible).click();
    $(By.id(MAIN_MESSAGE)).shouldBe(text("Discount NOT defined"));

    setMemberType("Gold");
    $(By.id(APPLY_DRL)).shouldBe(visible).click();
    $(By.id(MAIN_MESSAGE)).shouldBe(text("Discount NOT defined"));
  }

  private void assertDiscountForAmount(int amount, int discount) {
    setPurchaseAmount(amount);
    $(By.id(APPLY_DRL)).shouldBe(visible).click();
    $(By.id(MEMBER_DISCOUNT)).shouldBe(exactText(String.valueOf(discount)));
  }

  private void setPurchaseAmount(int value) {
    $(By.id(MEMBER_PURCHASE_AMOUNT)).shouldBe(visible).clear();
    $(By.id(MEMBER_PURCHASE_AMOUNT)).sendKeys(String.valueOf(value));
  }

  private void setMemberType(String type) {
    PrimeUi.selectOneRadio(By.id(MEMBER_TYPE)).selectItemByValue(type);
  }
}
