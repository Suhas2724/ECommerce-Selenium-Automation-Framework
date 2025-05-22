package org.seleniumFramework.pages;

import org.openqa.selenium.By;
import org.seleniumFramework.constants.Browser;

import static org.seleniumFramework.constants.Environment.*;

import org.seleniumFramework.utils.BrowserUtility;
import org.seleniumFramework.utils.JSONUtility;

public final class HomePage extends BrowserUtility {


    static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");

    public HomePage(Browser browserName) {
        super(browserName);//call the parent class
        goToWebsite(JSONUtility.readJSON(DEV));
    }

    public LoginPage goToLoginPage() { //page functions -> cant be void return type
        click(SIGN_IN_LINK_LOCATOR);
        return new LoginPage(getDriver());
    }

    public SignUpPage goToSignUpPage() { //page functions -> cant be void return type
        click(SIGN_IN_LINK_LOCATOR);
        return new SignUpPage(getDriver());
    }


}
