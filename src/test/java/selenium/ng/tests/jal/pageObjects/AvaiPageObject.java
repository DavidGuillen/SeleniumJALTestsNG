package selenium.ng.tests.jal.pageObjects;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AvaiPageObject {

	// CHECKs

	public static void waitForPage(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(AvaiPageIDs.LOGIN_RADIO_BUTTON_ID)));
	}

	public static String getDepartureTime(int bound, int segment, WebDriver driver) {
		String selectorTime = "#sidebar-segment-" + bound + "-" + segment
				+ " > div.flight-segment > div.itinerary-bar-step.ui-collapsible-content > time:nth-child(2)";

		String s = driver.findElement(By.cssSelector(selectorTime)).getText();
		return s.split("\n")[0];
//		return driver.findElement(By.cssSelector(selectorTime)).getText();
	}

	public static String getDepartureDate(int bound, int segment, WebDriver driver) {
		final String idPrefix = "segmentOriginDate-";
		String s = driver.findElement(By.id(idPrefix + bound + "-" + segment)).getText();
		return s.split("\n")[1];
//		return driver.findElement(By.id(idPrefix + bound + "-" + segment)).getText();
	}

	/**
	 * 
	 * @param bound   0 for the go. 1 for the return. More for complex combinations
	 * @param segment Each of the intermediate stops of the bound
	 * @param driver  your WebDriver
	 * @return
	 */
	public static String getDepartureDateAndTime(int bound, int segment, WebDriver driver) {
//		String dateStr = getDepartureDate(bound, segment, driver);
//		LocalDate ld = LocalDate.parse(dateStr);
//		String timeStr = getDepartureTime(bound, segment, driver);
//		ld.atTime(Integer.valueOf(timeStr.split(":")[0]), Integer.valueOf(timeStr.split(":")[1]));
		return getDepartureDate(bound, segment, driver) + " " + getDepartureTime(bound, segment, driver);
	}

	public static String getDepartureCity(WebDriver driver) {
		return driver.findElement(By.cssSelector(AvaiPageIDs.DEPARTURE_CITY_CSS)).getText();
	}

	public static String getTotalPrice(WebDriver driver) {
		return driver.findElement(By.id(AvaiPageIDs.TOTAL_PRICE_ID)).getText();
	}

	public static void clickContinueButton(WebDriver driver) {
		driver.findElement(By.id(AvaiPageIDs.CONTINUE_BUTTON_ID)).click();

	}
}
