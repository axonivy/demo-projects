package db.demos.persistence.webtest;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.primeui.widget.Table;

@IvyWebTest(headless = true)
public class PlayerRepoWebTestIT {

  @Test
  public void create_edit_delete() {
    open(EngineUrl.createProcessUrl("db-demos/197F321EE10A76E6/start.ivp"));

    var playersTable = new Table(By.id("form:playersTable"));
    var firstRow = playersTable.row(0);
    firstRow.shouldHave(cssClass("ui-datatable-empty-message"));

    $(By.id("form:playersTable:create")).click();

    firstRow.shouldNotHave(cssClass("ui-datatable-empty-message"));
    playersTable.column(1).shouldHave(size(1));

    var rowEditor = $(By.id("form:playersTable:0:rowEditor"));
    rowEditor.$("a").click();
    var firstName = $(By.id("form:playersTable:0:inputFirstName"));
    firstName.clear();
    firstName.sendKeys("Reto");
    var lastName = $(By.id("form:playersTable:0:inputLastName"));
    lastName.clear();
    lastName.sendKeys("Weiss");
    rowEditor.$$("a").get(1).click();

    playersTable.valueAtShouldBe(0, 1, text("Reto"));
    playersTable.valueAtShouldBe(0, 2, text("Weiss"));

    $(By.id("form:playersTable:0:delete")).click();

    firstRow.shouldHave(cssClass("ui-datatable-empty-message"));
  }

}
