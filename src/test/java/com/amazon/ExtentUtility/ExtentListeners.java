package com.amazon.ExtentUtility;

import java.util.Arrays;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.amazon.BaseClasses.PageBaseClass;
import com.amazon.MailUtility.SendEmail;
import com.amazon.log4jUtility.Log4jManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;



public class ExtentListeners implements ITestListener{

	WebDriver driver;
	static PageBaseClass pagebase = new PageBaseClass();
	Log4jManager log4j = new Log4jManager();
	
	public static int pass;
	public static int fail;
	public static int skip;
	
	ExtentTest test;
	static Date d = new Date();
	static String fileName = System.getProperty("user.dir")+"/reports/"+"ExtentReport_"+d.toString().replace(":", "_").replace(" ", "_") + ".html";
	ExtentReports extent = ExtentManager.createInstance(fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName() + "@TestCase : " + result.getMethod().getMethodName());
		testReport.set(test);
		
	}
	
	public void onTestSuccess(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>"+ "TEST CASE :-" + methodName.toUpperCase() + "PASSED"+ "</br>";
		Markup m= MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		if(result.getStatus() == ITestResult.SUCCESS) {
			pass++;
			
		}
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		String ExceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured : Click to see"
								+ "</font>" + "</b>" + "</summary>" + ExceptionMessage.replaceAll(",", "<br>") + "</details>"
								+ "\n");
		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
		if(result.getStatus() == ITestResult.FAILURE) {
			fail++;
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case :- " + methodName + "Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);
		if(result.getStatus() == ITestResult.SKIP) {
			skip++;
		}
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	public void onStart(ITestContext context) {
		log4j.logGen();
	}
	
	public void onFinish(ITestContext context) {
		
		if(extent != null) {
			try {
				log4j.logToExcel();
			}catch(Exception e) {
				e.printStackTrace();
			}
			extent.flush();
			PageBaseClass.zipFolder();
			//SendEmail.sendEmailWithAttachment("abingle27@gmail.com");
			
		}
		driver.quit();
	}
	
	public static void pass(String message) {
		testReport.get().pass(message);
		ExtentCucumberAdapter.addTestStepLog(message);
		pagebase.log.info(message);
	}
	
	public static void info(String message) {
		testReport.get().info(message);
		ExtentCucumberAdapter.addTestStepLog(message);
		pagebase.log.info(message);
	}
	
	public static void fail(String message) {
		testReport.get().fail(message);
		ExtentCucumberAdapter.addTestStepLog(message);
		pagebase.log.info(message);
	}
}
