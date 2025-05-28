package org.seleniumFramework.tests;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.constants.Browser;
import org.seleniumFramework.pages.HomePage;
import org.seleniumFramework.utils.BrowserUtility;
import org.seleniumFramework.utils.*;
import org.seleniumFramework.utils.LoggerUtility;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.seleniumFramework.constants.Browser.CHROME;

public class BaseTest {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLambdaTest;

    @Parameters({"browser", "isLambdaTest", "isHeadLess"})
    @BeforeMethod(description = "Load the homepage of the website")
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLambdaTest,
            @Optional("true") boolean isHeadLess,
            ITestResult result) {

        this.isLambdaTest = isLambdaTest;

        WebDriver lambdaDriver;
        if (isLambdaTest) {
            lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(lambdaDriver);
        } else {
            logger.info("Loading the homepage of the website");
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess); // Initialize the HomePage with Chrome browser in non-headless mode

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
