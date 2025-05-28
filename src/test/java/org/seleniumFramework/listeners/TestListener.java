package org.seleniumFramework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.Logger;
import org.seleniumFramework.tests.BaseTest;
import org.seleniumFramework.utils.BrowserUtility;
import org.seleniumFramework.utils.ExtentReporterUtility;
import org.seleniumFramework.utils.LoggerUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    /*
     *
     *
     */
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));

        ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " Passed");

        ExtentReporterUtility.getExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " Passed");
    }

    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " Failed");
        logger.error(result.getThrowable().getMessage());
        ExtentReporterUtility.getExtentTest().log(Status.FAIL, result.getMethod().getMethodName() + " Failed");
        ExtentReporterUtility.getExtentTest().log(Status.FAIL, result.getThrowable().getMessage());

        Object testClass = result.getInstance();

        BrowserUtility browserUtility = ((BaseTest) testClass).getInstance();
        logger.info("Capturing the screenshot");
        String screenShotPath = browserUtility.captureScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching the screenshot to the report");
        ExtentReporterUtility.getExtentTest().addScreenCaptureFromPath(screenShotPath);

    }

    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() + " Failed");
        ExtentReporterUtility.getExtentTest().log(Status.SKIP, result.getMethod().getMethodName() + " Skipped");
    }

    public void onStart(ITestContext context) {
        logger.info("Test suite started");
        ExtentReporterUtility.setUpSparkReporter("report");
    }

    public void onFinish(ITestContext context) {
        logger.info("Test suite completed");
        ExtentReporterUtility.flushExtentReports();
    }

}
