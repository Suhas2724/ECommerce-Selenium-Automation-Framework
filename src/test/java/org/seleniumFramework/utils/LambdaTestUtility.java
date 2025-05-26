package org.seleniumFramework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LambdaTestUtility {

    private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> capabilitiesThreadLocal = new ThreadLocal<>();

    public static WebDriver initializeLambdaTestSession(String browser, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "127");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "suhasc2277");
        ltOptions.put("accessKey", "LT_XpInS5SnsDnX9w9kaLmDmChjVdvSa66u2Jc9TdIaDG9lekK");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "4.23.0");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitiesThreadLocal.set(capabilities);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesThreadLocal.get());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driverThreadLocal.set(driver);
        return driverThreadLocal.get();
    }

    public static void quitSession(){
        if (driverThreadLocal.get() != null){
            driverThreadLocal.get().quit();
        }
    }
}
