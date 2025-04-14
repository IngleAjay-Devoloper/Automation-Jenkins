package com.amazon.PageClasses;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.amazon.ExtentUtility.ExtentListeners;

public class LoginPage {

	//public String User = "abingle27@gmail.com";
	//public String pwd = "Ingle@1997";
	
	@FindBy(xpath = "//span[contains(text(),\"Hello, sign in\")]")
	public WebElement signIn;
	
	@FindBy(xpath = "//input[@id='ap_email']")
	public WebElement emailinputbox;
	
	@FindBy(xpath = "//input[@id='continue']")
	public WebElement continueButtonafterEmailinput;
	
	@FindBy(xpath = "//input[@id='ap_password']")
	public WebElement passworInputBox;
	
	@FindBy(xpath = "//input[@id='signInSubmit']")
	public WebElement signInButton;
	
	public void clickSignIn() {
		try {
			Thread.sleep(2000);
			signIn.click();
			ExtentListeners.info("SignIn button Clicked");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void enterEmail(String Username) {
		try {
			Thread.sleep(2000);
			emailinputbox.sendKeys(Username);
			
			ExtentListeners.info("Email Entered : "+ Username);
			Thread.sleep(1000);
			continueButtonafterEmailinput.click();
			ExtentListeners.info("Click on Continue Button ");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterPassword(String password) {
		try {
			Thread.sleep(2000);
			passworInputBox.sendKeys("Ingle@1997");
			ExtentListeners.info("Password Entered : "+ password );
			Thread.sleep(1000);
			signInButton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void doSignIn(String Username,String Password) {
		try {
			
			ExtentListeners.info("SignIn button Clicked");
			emailinputbox.sendKeys(Username);
			ExtentListeners.info("Email Entered : "+ Username);
			Thread.sleep(1000);
			continueButtonafterEmailinput.click();
			ExtentListeners.info("Click on Continue Button ");
			Thread.sleep(2000);
			passworInputBox.sendKeys("Ingle@1997");
			ExtentListeners.info("Password Entered : "+ Password );
			Thread.sleep(1000);
			signInButton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
