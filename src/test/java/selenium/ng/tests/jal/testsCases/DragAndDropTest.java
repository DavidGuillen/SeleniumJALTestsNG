package selenium.ng.tests.jal.testsCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DragAndDropTest {

	private static final String PATH_CHROME_DRIVER = "C:\\Users\\formation\\Documents\\ChromeDrivers\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest(alwaysRun = true)
	public void setUpDriver() {
		System.out.println("Launching tests from : " + System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void DragnDrop() {
		driver.get("http://demo.guru99.com/test/drag_drop.html");

		// Element which needs to drag.
		WebElement From = driver.findElement(By.xpath("//*[@id='credit2']/a"));
		WebElement From2 = driver.findElement(By.id("fourth"));
		System.out.println("Texto del fourth: " + From2.getText());

		// Element on which need to drop.
		WebElement To = driver.findElement(By.xpath("//*[@id='bank']/li"));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);

		// Dragged and dropped.
		act.dragAndDrop(From, To).build().perform();
//		act.dragAndDropBy(, xOffset, yOffset).build().perform()
		pausa(2);
		int x0 = 520;
		int y0 = 376;
		int xf = 930 + 200;
		int yf = 628;
		act.dragAndDropBy(From2, xf - x0, yf - y0).build().perform();
		String bank = "";
		To = driver.findElement(By.xpath("//*[@id='bank']/li"));
		try {
			bank = To.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String blanco = "";
		try {
			blanco = driver.findElement(By.id("amt8")).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Text in Destination 1 is: " + bank);
		System.out.println("Text in Destination 2 is: " + blanco);

	}

	@Test
	public void DragnDropVerified() {
		driver.get("http://demo.guru99.com/test/drag_drop.html");

		// Element(BANK) which need to drag.
		WebElement From1 = driver.findElement(By.xpath("//*[@id='credit2']/a"));

		// Element(DEBIT SIDE) on which need to drop.
		WebElement To1 = driver.findElement(By.xpath("//*[@id='bank']/li"));

		// Element(SALES) which need to drag.
		WebElement From2 = driver.findElement(By.xpath("//*[@id='credit1']/a"));

		// Element(CREDIT SIDE) on which need to drop.
		WebElement To2 = driver.findElement(By.xpath("//*[@id='loan']/li"));

		// Element(500) which need to drag.
		WebElement From3 = driver.findElement(By.xpath("//*[@id='fourth']/a"));

		// Element(DEBIT SIDE) on which need to drop.
		WebElement To3 = driver.findElement(By.xpath("//*[@id='amt7']/li"));

		// Element(500) which need to drag.
		WebElement From4 = driver.findElement(By.xpath("//*[@id='fourth']/a"));

		// Element(CREDIT SIDE) on which need to drop.
		WebElement To4 = driver.findElement(By.xpath("//*[@id='amt8']/li"));

		// Using Action class for drag and drop.
		Actions act = new Actions(driver);

		// BANK drag and drop.
		act.dragAndDrop(From1, To1).build().perform();

		// SALES drag and drop.
		act.dragAndDrop(From2, To2).build().perform();

		// 500 drag and drop debit side.
		act.dragAndDrop(From3, To3).build().perform();

		// 500 drag and drop credit side.
		act.dragAndDrop(From4, To4).build().perform();

		// Verifying the Perfect! message.
		boolean isPerfectDisplayed;
		if (driver.findElement(By.xpath("//a[contains(text(),'Perfect')]")).isDisplayed()) {
			System.out.println("Perfect Displayed !!!");
			isPerfectDisplayed = true;
		} else {
			System.out.println("Perfect not Displayed !!!");
			isPerfectDisplayed = false;
		}

		SoftAssert asse = new SoftAssert();
		asse.assertEquals(isPerfectDisplayed, true, "Check the perfect displayed");
		To1 = driver.findElement(By.xpath("//*[@id='bank']/li"));
		To2 = driver.findElement(By.xpath("//*[@id='loan']/li"));
		To3 = driver.findElement(By.xpath("//*[@id='amt7']/li"));
		To4 = driver.findElement(By.xpath("//*[@id='amt8']/li"));
		asse.assertEquals(To1.getText(), "BANK", "Check the panel Debit side Account");
		asse.assertEquals(To2.getText(), "SALES", "Check the panel Debit side Amount");
		asse.assertEquals(To3.getText(), "5000", "Check the panel Credit side Account");
		asse.assertEquals(To4.getText(), "5000", "Check the panel Credit side Amount");

		asse.assertAll();

	}

	/**
	 * Execute at the end of a complete test to close the web driver.<br>
	 * You can override to use drive.quit() that will close all windows<br>
	 * <table>
	 * <tr>
	 * <th>Firstname</th>
	 * <th>Lastname</th>
	 * <th>Age</th>
	 * </tr>
	 * <tr>
	 * <td>Jill</td>
	 * <td>Smith</td>
	 * <td>50</td>
	 * </tr>
	 * <tr>
	 * <td>Eve</td>
	 * <td>Jackson</td>
	 * <td>94</td>
	 * </tr>
	 * </table>
	 */
	
	
	@AfterTest(alwaysRun = true)
	public void closeDriver() {
		pausa(8);
		driver.close();
	}

	private void pausa(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
