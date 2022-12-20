package db.demo.webtest;

import static com.codeborne.selenide.Condition.*;

import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;


@IvyWebTest(headless = false) 
public class PlayerDemoWebTestIT {

	@Test
	public void fillDialogForm() {
		open(EngineUrl.createProcessUrl("db-demo/184BD5B00AD6A83D/start2.ivp"));

		$(By.id("playerInputForm:name")).sendKeys("Tom");
		$(By.id("playerInputForm:Birthday")).sendKeys("11.11.2010");
		$(By.id("playerInputForm:FavoriteColor")).sendKeys("Yellow");
		  
		$(By.xpath("//tbody[@id = 'playerListForm:player_data']/tr[1]/td[4]"))
		  .shouldBe(visible, match("is a primary key", el -> isNumber(el)));
		$(By.id("playerInputForm:save")).shouldBe(enabled).click();
		$(By.id("playerListForm:player:0:delete")).should(enabled).click();
	}
	
	private boolean isNumber(WebElement element) {
		String text = element.getText();
		int z = Integer.parseInt(text);
		return z >= 0;
	}
	
}