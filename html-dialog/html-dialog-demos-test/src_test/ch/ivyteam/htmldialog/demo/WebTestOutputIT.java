package ch.ivyteam.htmldialog.demo;

import static ch.ivyteam.htmldialog.server.test.ProcessUtil.startProcess;
import static com.codeborne.selenide.CollectionCondition.anyMatch;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.io.File;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.primeui.PrimeUi;
import com.axonivy.ivy.webtest.primeui.widget.SelectManyCheckbox;
import com.axonivy.ivy.webtest.primeui.widget.SelectOneMenu;
import com.axonivy.ivy.webtest.primeui.widget.Table;

import ch.ivyteam.htmldialog.server.test.ProcessUtil;

@IvyWebTest
class WebTestOutputIT {

  @Test
  void dataTable() throws Exception {
    startProcess("145D180807C60B4B/DataTableDemo.ivp");
    Table table = PrimeUi.table(By.id("form:theTable"));
    table.searchGlobal("Dais");
    table.valueAtShouldBe(0, 2, exactText("Daisy"));
    clearInput(By.id("form:theTable:globalFilter"));
    SelectOneMenu menu = PrimeUi.selectOne(By.id("form:theTable:nameFilter"));
    menu.selectItemByLabel("Achmed");
    table.valueAtShouldBe(0, 2, exactText("Achmed"));
    menu.selectItemByLabel("All");
    $(By.id("form:theTable:scorePointFilter:filter")).sendKeys("6");
    table.valueAtShouldBe(0, 2, exactText("Birgit"));
    clearInput(By.id("form:theTable:scorePointFilter:filter"));
    $(By.id("form:theTable:scorePointFilter:filter")).sendKeys(" ");
    $(By.id("form:theTable:6:colName")).shouldBe(visible);
    $(By.id("form:theTable_data")).find(".ui-row-toggler").click();
    clearInput(By.id("form:theTable:0:name"));
    $(By.id("form:theTable:0:name")).sendKeys("testPerson");
    clearInput(By.id("form:theTable:0:points"));
    $(By.id("form:theTable:0:points")).sendKeys("9999");
    $(By.id("form:theTable:0:saveButton")).click();
    table.contains("testPerson");
    table.containsNot("Beni");
    table.contains("9999");
    table.containsNot("2563");
    $(By.id("form:theTable:6:dialogEditButton")).click();
    clearInput(By.id("detailForm:name"));
    $(By.id("detailForm:name")).sendKeys("demoUser");
    clearInput(By.id("detailForm:points"));
    $(By.id("detailForm:points")).sendKeys("2300");
    $(By.id("detailForm:dialogSaveButton")).click();
    table.contains("demoUser");
    table.containsNot("Yvonne");
    table.contains("2300");
    table.containsNot("1324");
    $(By.id("form:theTable:scoreId")).scrollIntoView("{block: \"center\"}").click();
    table.valueAtShouldBe(0, 2, exactText("testPerson"));
    $(By.id("form:theTable:scoreId")).click();
    table.valueAtShouldBe(0, 2, exactText("Tim"));
  }

  @Test
  void editableTable() throws Exception {
    startProcess("145D180807C60B4B/EditableTableDemo.ivp");
    Table table = PrimeUi.table(By.id("form:personTable"));
    editTable(1, 0, "firstName", "lastName");
    editableTableContains(table);
    editTable(2, 0, "Reto", "Weiss");
    editableTableContains(table);
    $(By.id("form:personTable:4:deleteButton")).click();
    table.containsNot("Michael");
    $(By.id("form:personTable:addButton")).click();
    editTable(1, 5, "testfirstName", "testLastName");
    table.contains("testfirstName");
    table.contains("testLastName");
  }

  private static void editableTableContains(Table table) {
    table.contains("lastName");
    table.containsNot("Weiss");
    table.contains("firstName");
    table.containsNot("Reto");
  }

  private void editTable(int confirm, int rowPosition, String firstName, String lastName) {
    $(By.id("form:personTable:" + rowPosition + ":rowEditor")).find("a", 0).click();
    clearInput(By.id("form:personTable:" + rowPosition + ":inputName"));
    $(By.id("form:personTable:" + rowPosition + ":inputName")).sendKeys(lastName);
    clearInput(By.id("form:personTable:" + rowPosition + ":inputFirstName"));
    $(By.id("form:personTable:" + rowPosition + ":inputFirstName")).sendKeys(firstName);
    $(By.id("form:personTable:" + rowPosition + ":rowEditor")).find("a", confirm).click();
  }

  @Test
  void pickList() throws Exception {
    startProcess("145D180807C60B4B/PickListDemo.ivp");
    $(By.id("personListForm:pickList")).find(".ui-picklist-source").find("li", 0).shouldBe(visible).click();
    $(".ui-picklist-button-add").shouldBe(visible, enabled).click();
    $(By.id("personListForm:pickList")).find(".ui-picklist-source").shouldNotHave(text("Renato"));
    $(By.id("personListForm:pickList")).find(".ui-picklist-target").shouldHave(text("Renato"));
    $(By.id("personListForm:pickList_target")).findAll("option").shouldHave(size(1));
    $(By.id("personListForm:sendButton")).shouldBe(visible).click();
    $(By.id("personListForm:resultPanel")).find("tr", 0).shouldNotHave(text("Renato"));
    $(By.id("personListForm:resultPanel")).find("tr", 1).shouldHave(text("Renato"));
  }

  @Test
  void orderList() {
    startProcess("145D180807C60B4B/OrderListDemo.ivp");
    editList(1, 3, "Bruno");
    editList(5, 0, "Bruno");
  }

  private void editList(int elementPosition, int buttonPosition, String name) {
    $(By.id("personListForm:personsList")).find(".ui-orderlist-item", elementPosition)
        .shouldBe(text(name)).click();
    $(By.id("personListForm:personsList")).find(".ui-orderlist-controls").find("button", buttonPosition)
        .shouldBe(visible, enabled).click();
    $(By.id("personListForm:personsList")).find(".ui-orderlist-item", elementPosition)
        .shouldNotBe(text(name));
  }

  @Test
  void selectOneMenu() throws Exception {
    startProcess("145D180807C60B4B/SelectOneMenuDemo.ivp");
    SelectOneMenu menu = PrimeUi.selectOne(By.id("demoForm:basic"));
    menu.selectItemByLabel("Reguel Wermelinger");
    $(By.id("demoForm:advance_label")).click();
    $(By.id("demoForm:advance_filter")).shouldBe(visible).sendKeys("Flav");
    $(By.id("demoForm:advance_9")).shouldBe(visible, enabled).click();
    $(By.id("demoForm:sendButton")).click();
    $(By.id("demoForm:outputBasicSelects")).shouldHave(text("Reguel"));
    $(By.id("demoForm:outputAdvancedSelects")).shouldHave(text("Flavio"));
  }

  @Test
  void autoComplete() throws Exception {
    startProcess("145D180807C60B4B/AutoCompleteDemo.ivp");
    searchAndExpect("xzyt", "xzyt69", "xzyz99");
    startProcess("145D180807C60B4B/AutoCompleteDemo.ivp");
    searchAndExpect("xzyz", "xzyz99", "xzyt69");
    startProcess("145D180807C60B4B/AutoCompleteDemo.ivp");
    searchAndExpect("yt69", "xzyt69", "xzyz98");
  }

  private void searchAndExpect(String searchText, String expectedText, String notExpectedText) {
    $(By.id("Form:event_input")).shouldBe(visible).sendKeys(searchText);
    $(By.id("Form:event_panel")).shouldBe(visible, text(expectedText), not(text(notExpectedText)));
    $(By.id("Form:event_input")).clear();
  }

  @Test
  void selectCheckboxes() throws Exception {
    startProcess("145D180807C60B4B/SelectManyCheckboxDemo.ivp");
    SelectManyCheckbox checkbox = PrimeUi.selectManyCheckbox(By.id("demoForm:manyCheckboxes"));
    checkbox.setCheckboxes(Arrays.asList("Weiss", "Kis"));
    $(By.id("demoForm:sendButton")).click();
    $(By.id("demoForm:outputSelectedPersons")).shouldHave(text("Weiss"), text("Kis"));
  }

  @Test
  void chart() {
    startProcess("145D180807C60B4B/ChartDemo.ivp");
    $(By.id("form:comboChart")).shouldBe(visible);
    $(By.id("form:pieChart")).shouldBe(visible);
  }

  @Test
  void prettyTime() {
    startProcess("145D180807C60B4B/PrettyTimeDemo.ivp");
    $(By.id("form:yesterday")).shouldHave(text("1 day ago"));
    $(By.id("form:twoHoursBefore")).shouldHave(text("2 hours ago"));
    $(By.id("form:threeWeeksAgo")).shouldHave(text("3 weeks ago"));
    $(By.id("form:threeYearsAgo")).shouldHave(text("3 years ago"));
  }

  @Test
  void documentViewer() throws Exception {
    startProcess("145D180807C60B4B/DocumentViewerDemo.ivp");
    File tempFile = File.createTempFile("tempDocFile", ".pdf");
    tempFile.deleteOnExit();
    $(By.id("form:choose_input")).shouldBe(exist).sendKeys(tempFile.getAbsolutePath());
    $(By.id("form:upload")).click();
    $(By.id("form:doclink")).shouldHave(text(tempFile.getName()));
    $(By.id("form:viewer")).shouldBe(visible);
  }

  @Test
  void barcode() {
    startProcess("145D180807C60B4B/BarcodeDemo.ivp");
    var element = $(By.id("qr")).shouldBe(visible);
    ProcessUtil.open(element.getAttribute("src"));
    $(By.tagName("svg")).shouldBe(visible);
    $(By.tagName("path")).shouldBe(visible);
  }

  @Test
  void jsfAgnosticForm() {
    startProcess("145D1862CF17F2C9/JsfAgnosticFormDemo.ivp");
    $$("span").shouldHave(anyMatch("build-form is rendered",
        span -> span.getText().contains("the FormEditor")));
  }

  private void clearInput(By inputLocator) {
    $(inputLocator).shouldBe(visible).clear();
    $(inputLocator).sendKeys(Keys.TAB);
  }
}
