package org.seleniumFramework.tests;

import org.apache.logging.log4j.Logger;
import org.seleniumFramework.pages.HomePage;
import org.seleniumFramework.utils.BrowserUtility;
import org.seleniumFramework.utils.LoggerUtility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.seleniumFramework.constants.Browser.CHROME;

public class BaseTest {

    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "Load the homepage of the website")
    public void setUp() {
        logger.info("Loading the homepage of the website");
        homePage = new HomePage(CHROME);
    }

    @AfterMethod(description = "Closing the browser after test")
    public void tearDown() {
        homePage.quitDriver();
    }

    public BrowserUtility getInstance(){
        return homePage;
    }

}
