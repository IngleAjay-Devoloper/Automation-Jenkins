package com.amazon.ExtentUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;
	
	public static ExtentReports createInstance(String fileName) {
		
		ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
		
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Results");
		spark.config().setEncoding("utf-8");
		spark.config().setReportName("Automation Test Report");
		spark.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Environment", "DEV:2");
		extent.setSystemInfo("Build Number", "10.08.01");
		extent.setSystemInfo("Browser", "Chrome");
		
		return extent;
		
	}
}
