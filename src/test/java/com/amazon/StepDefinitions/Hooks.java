package com.amazon.StepDefinitions;

import java.io.IOException;

import com.amazon.BaseClasses.PageBaseClass;
import com.amazon.ExtentUtility.ExtentListeners;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends PageBaseClass{

	@Before
	public void beforeScenario() {
		ExtentListeners.info("Browser is Opened");
	}
	
	@AfterStep
	public void afterstep(Scenario scenario) throws IOException {
		scenario.attach(getByteScreenshot(), "image/png", "."+"image");
	}
	
	
	public void afterScenario(Scenario scenario) throws IOException {
		if(scenario.isFailed()) {
			scenario.attach(getByteScreenshot(), "image/png", "."+"image");
		}
	driver.close();
	ExtentListeners.info("Browser is Closed");
	}
}
