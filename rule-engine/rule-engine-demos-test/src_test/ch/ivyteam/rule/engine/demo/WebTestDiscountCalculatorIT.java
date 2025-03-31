package ch.ivyteam.rule.engine.demo;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.primeui.PrimeUi;
import com.codeborne.selenide.Selenide;

@IvyWebTest
public class WebTestDiscountCalculatorIT {

  private static final String MEMBER_PURCHASE_AMOUNT_MESSAGE = "form:memberPurchaseAmountMessage";
  private static final String MEMBER_PURCHASE_AMOUNT = "form:memberPurchaseAmount";
  private static final String MEMBER_DISCOUNT = "form:memberDiscount";
  private static final String MEMBER_TYPE = "form:memberType";
  private static final String APPLY_DECISION_TABLE = "form:applyDecisionTable";
  private static final String APPLY_DRL = "form:applyDRL";
  private static final String APPLY_DMN = "form:applyDMN";

  @BeforeEach
  public void openRuleDemo() {
    open(EngineUrl.createProcessUrl("rule-engine-demos/153EAAA7649F85DA/start1.ivp"));
  }

  @Test
  public void noAmount() {
    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(empty);
    $(By.id(APPLY_DRL)).shouldBe(visible).click();
    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(text("Please enter Purchase Amount"));
    Selenide.refresh();

    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(empty);
    $(By.id(APPLY_DECISION_TABLE)).shouldBe(visible).click();
    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(text("Please enter Purchase Amount"));
    Selenide.refresh();

    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(empty);
    $(By.id(APPLY_DMN)).shouldBe(visible).click();
    $(By.id(MEMBER_PURCHASE_AMOUNT_MESSAGE)).shouldBe(text("Please enter Purchase Amount"));
  }

  @ParameterizedTest
  @ValueSource(strings = {APPLY_DRL, APPLY_DECISION_TABLE, APPLY_DMN})
  public void applyNone(String calcBtn) {
    assertDiscountForAmount(calcBtn, 200, 0);
    assertDiscountForAmount(calcBtn, 2500, 0);
  }

  @ParameterizedTest
  @ValueSource(strings = {APPLY_DRL, APPLY_DECISION_TABLE, APPLY_DMN})
  public void applyGold(String calcBtn) {
    PrimeUi.selectOneRadio(By.id(MEMBER_TYPE)).selectItemByValue("Gold");
    assertDiscountForAmount(calcBtn, 200, 2);
    assertDiscountForAmount(calcBtn, 750, 8);
    assertDiscountForAmount(calcBtn, 2100, 10);
  }

  @ParameterizedTest
  @ValueSource(strings = {APPLY_DRL, APPLY_DECISION_TABLE, APPLY_DMN})
  public void applySilver(String calcBtn) {
    PrimeUi.selectOneRadio(By.id(MEMBER_TYPE)).selectItemByValue("Silver");
    assertDiscountForAmount(calcBtn, 200, 0);
    assertDiscountForAmount(calcBtn, 750, 3);
    assertDiscountForAmount(calcBtn, 2100, 5);
  }

  private void assertDiscountForAmount(String calcBtn, int amount, int discount) {
    setPurchaseAmount(amount);
    $(By.id(calcBtn)).shouldBe(visible).click();
    $(By.id(MEMBER_DISCOUNT)).shouldBe(exactText(String.valueOf(discount)));
  }

  private void setPurchaseAmount(int value) {
    $(By.id(MEMBER_PURCHASE_AMOUNT)).shouldBe(visible).clear();
    $(By.id(MEMBER_PURCHASE_AMOUNT)).sendKeys(String.valueOf(value));
  }
}
