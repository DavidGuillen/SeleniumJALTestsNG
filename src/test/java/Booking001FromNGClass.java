import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import selenium.ng.tests.jal.pageObjects.AvaiPageObject;
import selenium.ng.tests.jal.pageObjects.HomePageObject;

public class Booking001FromNGClass {

	private static final String PATH_CHROME_DRIVER = "C:\\Users\\formation\\Documents\\ChromeDrivers\\chromedriver.exe";
	private static final String HOME_PAGE = "> HOME_PAGE > Checks > ";
	private static final String EXPECTED_PAGE_TITLE = "JAPAN AIRLINES (JAL) - France Region - Airfare to Japan (Tokyo)";
	private static final String WRONG_EXPECTED_PAGE_TITLE = "JAPAN AIRLINES (JL) - France Region - Airfare to Japan (Tokyo)";
	private static final String JAL_HOME_PAGE = "http://www.fr.jal.co.jp/frl/en/?utm_source=google&utm_medium=search&utm_campaign=07_fr_eu_acquisition_general_435169997_27673035317_263326966681_201712&utm_term=e_japan%20airlines&sl=11_0030&gclid=Cj0KCQiA14TjBRD_ARIsAOCmO9ag8AeRyWlLKpqNLdMQHJMx2eNtKJCW_MfSh6oWWzDY6aD6wbCQlGIaAhBXEALw_wcB";

	public WebDriver driver;
	private static SoftAssert softAssert = new SoftAssert();

	
	@BeforeTest(alwaysRun = true)
	public void setUpDriver() {
		System.out.println("Launching tests from : " + System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test(priority=0, alwaysRun = true)
	public void normalFlow() {
		driver.get(JAL_HOME_PAGE);
		//Close the modal window
		HomePageObject.closeModal(driver);
		// Perform Home Page checks
		defaultHomePageChecks();
		// Perform Home Page actions
		defaultHomePageActions();
		
		AvaiPageObject.waitForPage(driver);
	}
	
	private void defaultHomePageChecks() {
		softAssert.assertEquals(driver.getTitle(), EXPECTED_PAGE_TITLE, HOME_PAGE + "Wrong title");
		softAssert.assertEquals(HomePageObject.getDepartureArea(driver), "France", HOME_PAGE + "The departure area must be France if you connect from France");
		softAssert.assertAll();
	}

	private void defaultHomePageActions() {
		// Select Nice as departure city
		HomePageObject.selectDepartureCity("NCE", driver);
		// Set a departure date in 2 months
		LocalDate departureDate = LocalDate.now().plusMonths(2);
		HomePageObject.setDepartureDate(departureDate, driver);
		// Set a return date in 3 months
		HomePageObject.setReturnDate(departureDate.plusMonths(1), driver);	
		//Continue to the following page
		HomePageObject.continueToNextPage(driver);		
	}
	
	
	@AfterTest(alwaysRun = true)
	public void closeDriver() {
		pausa(5);
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
