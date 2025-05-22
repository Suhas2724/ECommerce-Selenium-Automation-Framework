package org.seleniumFramework.tests;

import static org.seleniumFramework.constants.Browser.*;

import org.seleniumFramework.pages.HomePage;

import static org.testng.Assert.*;

import org.seleniumFramework.pojo.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {

    HomePage homePage;

    @BeforeMethod(description = "Load the homepage of the website")
    public void setUp() {
        homePage = new HomePage(CHROME);
    }

    @AfterMethod(description = "Closing the browser after test")
    public void tearDown() {
        homePage.quitDriver();
    }

    @Test(description = "Login test", groups = {"e2e", "sanity"}, dataProviderClass = org.seleniumFramework.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Test Account");
    }

    @Test(description = "Login test", groups = {"e2e", "sanity"}, dataProviderClass = org.seleniumFramework.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Test Account");
    }

    @Test(description = "Login test", groups = {"e2e", "sanity"}, dataProviderClass = org.seleniumFramework.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginTestWithInvalidCredentials(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getErrorMessage(), "Authentication failed.");
    }
}
