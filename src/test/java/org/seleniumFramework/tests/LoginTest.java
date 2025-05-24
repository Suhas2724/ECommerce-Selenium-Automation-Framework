package org.seleniumFramework.tests;

import org.seleniumFramework.listeners.RetryAnalyzer;
import org.seleniumFramework.listeners.TestListener;
import org.seleniumFramework.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest{

    @Test(description = "Login test", groups = {"e2e", "sanity"}, dataProviderClass = org.seleniumFramework.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider", retryAnalyzer = RetryAnalyzer.class)
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Test Account");
    }

    @Test(description = "Login test", groups = {"e2e", "sanity"}, dataProviderClass = org.seleniumFramework.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Test Account");
    }

    @Test(description = "Login test", groups = {"e2e", "sanity"}, dataProviderClass = org.seleniumFramework.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider")
    public void loginExcelTest(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Test Account");
    }
}
