package org.seleniumFramework.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.utils.BrowserUtility;

public class AccountCreationPage extends BrowserUtility {

    public AccountCreationPage(WebDriver driver) {
        super(driver);
    }

    private static final By PERSONAL_INFO_LOCATOR = By.xpath("//h3[text()=\"Your personal information\"]");
    private static final By GENDER_RADIO_BUTTON = By.id("id_gender1");
    private static final By FIRST_NAME_LOCATOR = By.id("customer_firstname");
    private static final By LAST_NAME_LOCATOR = By.id("customer_lastname");
    private static final By CREATE_PASSWORD_LOCATOR = By.id("passwd");
    private static final By DAY_LOCATOR = By.id("days");
    private static final By MONTH_LOCATOR = By.id("months");
    private static final By YEAR_LOCATOR = By.id("years");
    private static final By SUBMIT_ACCOUNT_LOCATOR = By.id("submitAccount");
    private static final By SUCCESS_ALERT_LOCATOR = By.xpath("//div/p[@class=\"alert alert-success\"]");

    public String signUp(){
        visibilityOfElementLocated(PERSONAL_INFO_LOCATOR);
        click(GENDER_RADIO_BUTTON);
        enterText(FIRST_NAME_LOCATOR,new Faker().name().firstName());
        enterText(LAST_NAME_LOCATOR,new Faker().name().lastName());
        enterText(CREATE_PASSWORD_LOCATOR,"password");
        visibilityOfElementLocated(DAY_LOCATOR);
        calenderSelect(DAY_LOCATOR,"15");
        calenderSelect(MONTH_LOCATOR,"8");
        calenderSelect(YEAR_LOCATOR,"2003");
        click(SUBMIT_ACCOUNT_LOCATOR);
        visibilityOfElementLocated(SUCCESS_ALERT_LOCATOR);
        return getVisibleText(SUCCESS_ALERT_LOCATOR);
    }



}
