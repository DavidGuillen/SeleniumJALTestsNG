package selenium.ng.tests.jal.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AlpiPageObject {

	public static void selectPassengerTitle(int i, String title, WebDriver driver) {
		Select s = new Select(driver.findElement(By.id(i + "-title")));
		s.selectByVisibleText(title);
	}
	
	public static void setPassengerName(String name, WebDriver driver) {
		driver.findElement(By.id(" ")).sendKeys();
	}

}
