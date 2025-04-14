package com.amazon.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/Features/" }, glue = { 
"com.amazon.StepDefinitions" }, tags= "@Login", monochrome = true,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},publish=true)

public class TestRunner extends AbstractTestNGCucumberTests{
	//

}
