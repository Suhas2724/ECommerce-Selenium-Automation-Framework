package org.seleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.utils.BrowserUtility;

public final class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR = By.id("SubmitLogin");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress, String password) {
        visibilityOfElementLocated(EMAIL_TEXT_BOX_LOCATOR);
        enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        click(SUBMIT_BUTTON_LOCATOR);

        return new MyAccountPage(getDriver());

    }
}
