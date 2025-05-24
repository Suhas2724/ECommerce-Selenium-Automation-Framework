package org.seleniumFramework.tests;

import org.apache.logging.log4j.Logger;
import org.seleniumFramework.listeners.RetryAnalyzer;
import org.seleniumFramework.listeners.TestListener;
import org.seleniumFramework.pages.HomePage;
import org.seleniumFramework.pojo.User;
import org.seleniumFramework.utils.LoggerUtility;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.seleniumFramework.constants.Browser.CHROME;
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
