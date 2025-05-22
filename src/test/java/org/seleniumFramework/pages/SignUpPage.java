package org.seleniumFramework.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.utils.BrowserUtility;

public class SignUpPage extends BrowserUtility {

    private static final By EMAIL_LOCATOR = By.id("email_create");
    private static final By SUBMIT_LOCATOR = By.id("SubmitCreate");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public AccountCreationPage goToAccountCreationPage() {
        visibilityOfElementLocated(EMAIL_LOCATOR);
        Faker faker = new Faker();
        enterText(EMAIL_LOCATOR, faker.internet().emailAddress());
        click(SUBMIT_LOCATOR);
        return new AccountCreationPage(getDriver());
    }

}
