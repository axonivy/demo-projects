package db.demos.webtest;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.axonivy.ivy.webtest.primeui.PrimeUi;
import com.axonivy.ivy.webtest.primeui.widget.SelectOneMenu;

@IvyWebTest(headless = true)
public class PlayerDemoWebTestIT {

	@Test
	public void fillDialogForm() {
		open(EngineUrl.createProcessUrl("db-demos/184BD5B00AD6A83D/start.ivp"));

		$(By.id("Teams:teamInputForm:foundationDay_input")).sendKeys("11.11.2010");
		$(By.id("Teams:teamInputForm:Teamname")).sendKeys("FCL");

		$(By.xpath("//*[@id=\"Teams:teamListForm:team_data\"]/tr[1]/td[1]")).shouldBe(visible,
				match("is a primary key", el -> isNumber(el)));
		$(By.id("Teams:teamInputForm:saveTeam")).shouldBe(enabled).click();
		$(By.id("Teams:teamListForm:team:1:delete")).should(enabled).click();
		refresh();
		$(By.id("Players:playerInputForm:name")).sendKeys("Tom");
		$(By.id("Players:playerInputForm:Birthday_input")).sendKeys("11.11.2010");
		$(By.id("Players:playerInputForm:Birthday_input")).sendKeys(Keys.TAB);
		$(By.id("Players:playerInputForm:FavoriteColor")).sendKeys("Yellow");
		SelectOneMenu menu = PrimeUi.selectOne(By.id("Players:playerInputForm:team"));
		menu.selectItemByLabel("FCB");
		
		$(By.xpath("//*[@id=\"Players:playerListForm:player_data\"]/tr[1]/td[4]")).shouldBe(visible,
				match("is a primary key", el -> isNumber(el)));
		$(By.id("Players:playerListForm:player:0:delete")).should(enabled).click();
		$(By.id("Players:playerInputForm:savePlayer")).shouldBe(enabled).click();

	}

	private boolean isNumber(WebElement element) {
		String text = element.getText();
		int z = Integer.parseInt(text);
		return z >= 0;
	}

}