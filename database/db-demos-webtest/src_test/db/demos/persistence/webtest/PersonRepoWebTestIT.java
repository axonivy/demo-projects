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
public class PersonRepoWebTestIT {

  @Test
  public void create_edit_delete() {
    open(EngineUrl.createProcessUrl("db-demos/197F321EE10A76E6/start.ivp"));

    var personsTable = new Table(By.id("form:personsTable"));
    var firstRow = personsTable.row(0);
    firstRow.shouldHave(cssClass("ui-datatable-empty-message"));

    $(By.id("form:personsTable:create")).click();

    firstRow.shouldNotHave(cssClass("ui-datatable-empty-message"));
    personsTable.column(1).shouldHave(size(1));

    var rowEditor = $(By.id("form:personsTable:0:rowEditor"));
    rowEditor.$("a").click();
    var firstName = $(By.id("form:personsTable:0:inputFirstName"));
    firstName.clear();
    firstName.sendKeys("Reto");
    var lastName = $(By.id("form:personsTable:0:inputLastName"));
    lastName.clear();
    lastName.sendKeys("Weiss");
    rowEditor.$$("a").get(1).click();

    personsTable.valueAtShouldBe(0, 1, text("Reto"));
    personsTable.valueAtShouldBe(0, 2, text("Weiss"));

    $(By.id("form:personsTable:0:delete")).click();

    firstRow.shouldHave(cssClass("ui-datatable-empty-message"));
  }

}
