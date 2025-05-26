package org.seleniumFramework.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumFramework.constants.Browser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BrowserUtility {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Initializing the browser: " + browserName);
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
            maximizeWindow();
        } else if (browserName == Browser.SAFARI) {
            driver.set(new SafariDriver());
            maximizeWindow();
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
            maximizeWindow();
        } else {
            logger.info("Invalid browser name ... Please select Chrome or Safari or Firefox");
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Initializing the browser: " + browserName);
        if (browserName == Browser.CHROME) {
            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
//                options.addArguments("disable-gpu");
                driver.set(new ChromeDriver(options));
                maximizeWindow();
            } else {
                driver.set(new ChromeDriver());
                maximizeWindow();
            }
        } else if (browserName == Browser.SAFARI) {
            driver.set(new SafariDriver());
            maximizeWindow();
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());
            maximizeWindow();
        } else {
            logger.info("Invalid browser name ... Please select Chrome or Safari or Firefox");
        }
    }

    public BrowserUtility(WebDriver driver) {
        this.driver.set(driver);
    }

    public void goToWebsite(String url) {
        logger.info("Navigating to the website: " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void click(By locator) {
        logger.info("Clicking on the element: " + locator);
        driver.get().findElement(locator).click();
    }

    public void enterText(By locator, String text) {
        logger.info("Entering text: " + text + " into the element: " + locator);
        driver.get().findElement(locator).sendKeys(text);
    }

    public void calenderSelect(By locator, String value) {
        logger.info("Selecting value: " + value + " from the calendar");
        Select select = new Select(driver.get().findElement(locator));
        select.selectByValue(value);
    }

    public void visibilityOfElementLocated(By locator) {
        logger.info("Waiting for the element to be visible: " + locator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(1));
        logger.info("Waiting for the element to be visible: " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void quitDriver() {
        logger.info("Quitting the browser");
        driver.get().quit();
    }

    public String getVisibleText(By locator) {
        logger.info("Getting visible text from the element: " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Returning the visible text: " + element.getText());
        return element.getText();
    }

    public String captureScreenshot(String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String relativePath = "screenshots/" + screenshotName + "_" + dateName + ".png";
        String destination = System.getProperty("user.dir") + "/reports/" + relativePath;
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getMessage());
        }
        return relativePath;
    }

}
