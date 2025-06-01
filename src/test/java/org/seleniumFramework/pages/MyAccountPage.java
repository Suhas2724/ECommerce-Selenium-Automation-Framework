package org.seleniumFramework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumFramework.utils.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {

    private static final By USER_NAME_LOCATOR = By.xpath("//a[@title=\"View my customer account\"]/span");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[@class='alert alert-danger']/ol/li");
    private static final By SEARCH_BAR_LOCATOR = By.id("search_query_top");
    private static final By SUBMIT_SEARCH_LOCATOR = By.xpath("//button[@name=\"submit_search\"]");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        visibilityOfElementLocated(USER_NAME_LOCATOR);
        return getVisibleText(USER_NAME_LOCATOR);
    }

    public String getErrorMessage() {
        return getVisibleText(ERROR_MESSAGE_LOCATOR);
    }

    public SearchResultsPage enterTextInSearchBar(String searchText){
        enterText(SEARCH_BAR_LOCATOR,searchText);
        click(SUBMIT_SEARCH_LOCATOR);

        return new SearchResultsPage(getDriver());

    }

}
