package com.amazon.StepDefinitions;


import org.openqa.selenium.support.PageFactory;

import com.amazon.BaseClasses.PageBaseClass;
import com.amazon.ObjectRepository.OR;
import com.amazon.PageClasses.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login extends PageBaseClass {

	LoginPage loginPage;
	
	@Given("^Open Amazon")
	public void open_amazon() {
		try {
		invokeBrowser("chrome");
		openApplication(OR.readPropertiesFile("URL"));
		maximaizeWindow();
		takeScreenShot();
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	@When("^Click on SignIn Button")
	public void click_on_signin_button() {
		try {
			
			Thread.sleep(2000);
			loginPage = new LoginPage();
			PageFactory.initElements(driver, loginPage);
			
			loginPage.clickSignIn();
			takeScreenShot();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("^Enter Email and Click on Continue Button(.*)$")
	public void enter_email(String Username) {
		try {
			loginPage.enterEmail(Username);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("^Enter Password and Click on Sign Button(.*)$")
	public void enter_password_and_click_on_sign_button(String Password) {
		try {
			loginPage.enterPassword(Password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
