package org.seleniumFramework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumFramework.constants.Browser;

import java.time.Duration;

public abstract class BrowserUtility {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BrowserUtility(Browser browserName) {
        if (browserName == Browser.CHROME) {
            driver = new ChromeDriver();
            maximizeWindow();
        } else if (browserName == Browser.SAFARI) {
            driver = new SafariDriver();
            maximizeWindow();
        } else if (browserName == Browser.FIREFOX) {
            driver = new FirefoxDriver();
            maximizeWindow();
        } else {
            System.err.println("Invalid browser name ... Please select Chrome or Safari");
        }
    }

    public BrowserUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void goToWebsite(String url) {
        driver.get(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void enterText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void calenderSelect(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public void visibilityOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void quitDriver() {
        driver.quit();
    }

    public String getVisibleText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText();
    }


}
