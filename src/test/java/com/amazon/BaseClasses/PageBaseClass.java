package com.amazon.BaseClasses;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.Zip;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.ExtentUtility.ExtentListeners;
import com.amazon.Utilities.DateUtil;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class PageBaseClass {

	public static WebDriver driver;
	public Logger log = Logger.getLogger(getClass());
	boolean result;
	public Properties prop;
	public static ExtentTest test;
	
	public void invokeBrowser(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("Chrome")) {
				//WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				
				options.addArguments("--disable-blink-features=AutomationControlled");
		        options.addArguments("user-data-dir=C:/Users/abing/AppData/Local/Google/Chrome/User Data/Default");
		        options.addArguments("profile-directory=Default");
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/driver/chromedriver.exe");
				driver = new ChromeDriver(options);
			}else if(browserName.equalsIgnoreCase("Mozila")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}else if(browserName.equalsIgnoreCase("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void openApplication(String url) {
		try {
		driver.get(url);
		ExtentListeners.info("Opened URL is : "+url);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void maximaizeWindow() {
		try {
			driver.manage().window().maximize();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void zipFolder() {
		try {
			String zip = Zip.zip(new File(System.getProperty("user.dir")+"/reports"));
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("./reports.zip"));
			byte[] decode = Base64.getDecoder().decode(zip);
			stream.write(decode);
			stream.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] getByteScreenshot() throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		byte[] fileContent = FileUtils.readFileToByteArray(src);
		return fileContent;
	}
	
	
	
	public WebElement getElement(String locatorKey,String locatorValue) {
		WebElement element = null;
		
		try {
			if(locatorKey.equalsIgnoreCase("Id")) {
				element=driver.findElement(By.xpath(locatorValue));
			}else if(locatorKey.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(locatorValue));
			}else if(locatorKey.equalsIgnoreCase("ClassName")) {
				element = driver.findElement(By.xpath(locatorValue));
			}else if(locatorKey.equalsIgnoreCase("CSS")) {
				element = driver.findElement(By.xpath(locatorValue));
			}else if(locatorKey.equalsIgnoreCase("LinkText")) {
				element = driver.findElement(By.xpath(locatorValue));
			}else if(locatorKey.equalsIgnoreCase("PartialLinkText")) {
				element = driver.findElement(By.xpath(locatorValue));
			}else if(locatorKey.equalsIgnoreCase("name")) {
				element = driver.findElement(By.xpath(locatorValue));
			}else {
				log.info("Failing The TestCase, Invalid Locator "+ locatorValue);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return element;
	}
	
	//boolean
	public void matchTitle(String expectedTitle) {
		try {
			String actualTitle = driver.getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			ExtentListeners.info(actualTitle + "Equals to : "+ expectedTitle);
		}catch(Exception e) {
			e.printStackTrace();
		}
	//	return result;
	}
	
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertMessage = driver.switchTo().alert().getText();
			
			System.out.println(alertMessage);
			
			alert.accept();
			log.info("Page Alert Accepted");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cancelAlert() {
	try {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		log.info("Page Alert Rejected");
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public static boolean isClickable(WebDriver driver,WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	
	public void takeScreenShot() {
		TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+ "/ScreenShots/" + DateUtil.getTimeStamp() + ".png");
		
		try {
			FileUtils.copyFile(sourceFile, destFile);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean selectDropDownValueByVisibleText(WebElement webelement,String Value) {
		try {
			if(isClickable(driver,webelement)) {
				Select select = new Select(webelement);
				select.selectByVisibleText(Value);
				log.info("Selected the Value : "+ Value);
				takeScreenShot();
				result = true;
			}else {
				result =  false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean selectDropDownValueByValue(WebElement webelement,String value) {
		try {
			Select select = new Select(webelement);
			select.selectByValue(value);
			log.info("Selected the Value : "+ value);
			takeScreenShot();
			result = true;
		}catch(Exception e) {
			result =false;
		}
		return result;
	}
	
	public void verifyElementIsDisplayed(WebElement webelement) {
		try {
			if(webelement.isDisplayed()) {
				log.info(webelement + " is Displayed");
			}else {
				log.info(webelement + " is not appeared");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List getAllElementsOfDropdown(WebElement webelement) {
		List<WebElement> allElements = null;
		
		try {
			Select select = new Select(webelement);
			allElements = select.getOptions();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return allElements;
	}
	
	public boolean rightClick(WebElement ele) {
		try {
			Actions action = new Actions(driver);
			action.contextClick(ele).perform();
			result = true;
		}catch(Exception e) {
			result = false;
		}
		return result;
	}
	
	public void switchtoFrame(WebDriver driver, String identifier) {
		driver.switchTo().frame(driver.findElement(By.xpath(identifier)));
	}
	
	public void switchtoMainContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public boolean enterText(String key,String value) {
		try {
			getElement(key, value).sendKeys(value);
			Thread.sleep(2000);
			log.info("Entered "+ value);
			result = true;
		}catch(Exception e) {
			result = false;
			log.info("Unable to enter "+ value);
		}
		return result;
	}
	
	public void enterText(WebElement element, String value) {
		try {
			Thread.sleep(2000);
			element.sendKeys(value);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public WebElement fluientWaitForElement(WebElement element,int timeoutSec,int pollingSec) throws InterruptedException {
		Thread.sleep(5000);
		FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeoutSec)).pollingEvery(Duration.ofMillis(pollingSec)).ignoring(NoSuchElementException.class, TimeoutException.class).ignoring(StaleElementReferenceException.class);
		
		for(int i = 0; i<2; i++) {
			try {
				fWait.until(ExpectedConditions.visibilityOf(element));
				fWait.until(ExpectedConditions.elementToBeClickable(element));
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Element No Found trying again - "+ element.toString().substring(70));
			}
		}
		return element;
	}
	
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)","");
	}
	
	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-350)", "");
	}
	
	public String getText(WebElement element) {
		String value=null;
		try {
			value = element.getText();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	
	
}
