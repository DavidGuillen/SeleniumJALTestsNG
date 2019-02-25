package selenium.ng.tests.jal.testsCases;

import java.awt.AWTException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WindowsTabsTest {

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

	@AfterTest(alwaysRun = true)
	public void closeDriver() {
		pausa(8);
		driver.quit();
	}

	private void pausa(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	@Test
	public void testTheWindowsHandlers() {
		driver.get("http://demo.guru99.com/popup.php");

		String mainWindow = driver.getWindowHandle();
		System.out.println("This is the ID for the Main Window: " + mainWindow);

		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();
		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();
		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

//driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.chord(Keys.CONTROL, "t"));
//driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.chord(Keys.CONTROL, "T"));
//driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.CONTROL, "t");
		mainWindow = driver.getWindowHandle();
		System.out.println("This is the ID for the Main Window: " + mainWindow);
		System.out.println("These are all the Windows' IDs: " + mainWindow);
		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		int j = 0;
		while (i1.hasNext()) {
			System.out.println("Vuelta : " + j);
			String childWindow = i1.next();
			if (!mainWindow.equalsIgnoreCase(childWindow)) {

				// Switching to Child window
				driver.switchTo().window(childWindow);
				pausa(3);
				System.out.println("This is a Child: " + driver.getWindowHandle());
				driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");

				driver.findElement(By.name("btnLogin")).click();
				pausa(3);
				// Closing the Child Window.
				driver.close();
			}
			j++;
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(mainWindow);
	}

	@Test
	public void moreWindowsAndTabsTest() throws AWTException {

		driver.get("http://google.com");

		String mainWindow = driver.getWindowHandle();
		System.out.println("This is the ID for the Main Window: " + mainWindow);
		WebElement element = driver.findElement(By.name("q"));

		// Enter something to search for
		element.sendKeys("David Guillen Jimenez");
		element.submit();

		pausa(3);

		System.out.println("Page title is: " + driver.getTitle());
		driver.findElement(By.partialLinkText("https://fr.linkedin.com"))
				.sendKeys(Keys.chord(Keys.CONTROL, Keys.SHIFT, Keys.ENTER));

		final String ID_FIRST_NAME = "join-firstname";
		
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		pausa(4);
		((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");
		Set<String> sNew = driver.getWindowHandles();
		final String NEW_TAB_ID = getTheDifferentOne(s1, sNew);
		
		int j = 0;
		while (i1.hasNext()) {
			System.out.println("Vuelta : " + j);
			String childWindowHandleStr = i1.next();
			if (!mainWindow.equalsIgnoreCase(childWindowHandleStr)) {
				driver.switchTo().window(childWindowHandleStr);
				driver.findElement(By.id(ID_FIRST_NAME)).sendKeys("David");
			}
		}
		
		System.out.println("Is this the child window?? >> " + NEW_TAB_ID);

		/*
		 * OPEN A NEW TAB
		 */
//		((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

		// This will not work if you change the Window, or if you run parallel tests.
		// The call is made for the system
//		Robot robotAWT = new Robot();
//		robotAWT.keyPress(KeyEvent.VK_CONTROL);
//		robotAWT.keyPress(KeyEvent.VK_T);
//		robotAWT.keyRelease(KeyEvent.VK_T);
//		robotAWT.keyRelease(KeyEvent.VK_CONTROL);

		// None of this works at all to open a new tab
//		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.chord(Keys.CONTROL, "t"));
//		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.chord(Keys.CONTROL, "T"));
//		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.CONTROL, "t");
//		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.CONTROL + "t");
//		Actions action = new Actions(driver);
//		action.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL).build().perform();

		/*
		 * OPEN A NEW WINDOW
		 */
//		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).sendKeys(Keys.chord(Keys.CONTROL, "n"));

//		((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", "");

		/*
		 * See all the created handlers
		 */
		Set<String> handlesAll = driver.getWindowHandles();
		System.out.println("These are all the handles: " + handlesAll);
		System.out.println("Before finishing you were in : " + driver.getWindowHandle());

	}

	private String getTheDifferentOne(Set<String> s1, Set<String> sNew) {
		String str = sNew.toString();
		String str2 = str.substring(1, str.length()-1);
		String test;
		try {
			test = str.substring(1, str.length());
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("***************VOILAL l'erreur" );
		}
		
		String[] s = str2.split(", ");
		String theNewOne = "";
		
		Iterator<String> i1 = s1.iterator();
		
		for (int i = s.length-1; i != 0; i--) {
			boolean didWeFindIt = false;
			while (i1.hasNext()) {
				String childWindowHandleStr = i1.next();
				if (childWindowHandleStr.equals(s[i].trim())) {
					didWeFindIt = true;
				}
			}
			if(!didWeFindIt) {
				theNewOne = s[i].trim();
				break;
			}
		}
		return theNewOne;
	}

	private String getTheDifferentOne2(Set<String> s1, Set<String> sNew) {
		return "";
	}
}
