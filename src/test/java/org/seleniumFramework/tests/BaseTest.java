package org.seleniumFramework.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.pages.HomePage;
import org.seleniumFramework.utils.BrowserUtility;
import org.seleniumFramework.utils.LambdaTestUtility;
import org.seleniumFramework.utils.LoggerUtility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.seleniumFramework.constants.Browser.CHROME;

public class BaseTest {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLambdaTest = true;
    private boolean isHeadLess = true;


    @BeforeMethod(description = "Load the homepage of the website")
    public void setUp(ITestResult result) {
        WebDriver lambdaDriver;
        if (isLambdaTest) {
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession("chrome", result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        } else {
            logger.info("Loading the homepage of the website");
            homePage = new HomePage(CHROME, isHeadLess); // Initialize the HomePage with Chrome browser in non-headless mode

        }

    }

    @AfterMethod(description = "Closing the browser after test")
    public void tearDown() {
        if (isLambdaTest) {
            LambdaTestUtility.quitSession();
        } else {
            homePage.quitDriver();
        }

    }

    public BrowserUtility getInstance() {
        return homePage;
    }

}
