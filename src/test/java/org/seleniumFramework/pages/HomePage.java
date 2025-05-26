package org.seleniumFramework.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.constants.Browser;

import static org.seleniumFramework.constants.Environment.*;

import org.seleniumFramework.utils.BrowserUtility;
import org.seleniumFramework.utils.JSONUtility;
import org.seleniumFramework.utils.LoggerUtility;

public final class HomePage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());
    static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

    public HomePage(Browser browserName,boolean isHeadless) {
        super(browserName,isHeadless);//call the parent class
        goToWebsite(JSONUtility.readJSON(DEV).getUrl());
    }

    public HomePage(WebDriver driver){
        super(driver);
        goToWebsite(JSONUtility.readJSON(QA).getUrl());
    }

    public LoginPage goToLoginPage() { //page functions -> cant be void return type
        logger.info("Trying to perform click to go to Sign In Page");
        click(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }

    public SignUpPage goToSignUpPage() { //page functions -> cant be void return type
        click(SIGN_IN_LINK_LOCATOR);
        return new SignUpPage(getDriver());
    }


}
