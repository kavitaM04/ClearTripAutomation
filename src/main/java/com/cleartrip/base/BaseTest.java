package com.cleartrip.base;

import org.testng.annotations.BeforeTest;

import com.cleartrip.utils.TestUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {

	protected static WebDriver driver;
	protected static Properties prop;
	protected static Actions action;
	protected static HashMap<String, String> strings = new HashMap<String, String>();
	TestUtils utils;

	InputStream stringsis;
	InputStream inputStream;

	public BaseTest() {
		PageFactory.initElements(driver, this);
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("./src/main/java/com/cleartrip/config/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// @Parameters({ "platformName", "platformVersion", "deviceName", "udid" })
	@BeforeTest
	public void beforeTest() throws Exception {
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox"))

		{
			System.setProperty("webdriver.gecko.driver", "./exefiles/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.navigate().to(prop.getProperty("url"));

	}public void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clickElement(WebElement e) {
		waitForVisibility(e);
		e.click();
	}
	
	
	public void scrollTillElement(WebElement e) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", e);
		}

	public void scrollVertically() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

	}
	public void sendKeys(WebElement e, String text) {
		waitForVisibility(e);
		e.clear();
		e.sendKeys(text);
	}

	public String getAttribute(WebElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

	public String getTxt(WebElement e) {
		waitForVisibility(e);
		return e.getText();
	}

	public String CurrentURL() {
		String current_URL = driver.getCurrentUrl();
		return current_URL;
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
