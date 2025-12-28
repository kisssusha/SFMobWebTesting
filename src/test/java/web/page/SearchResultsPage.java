package mobile.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {

    private WebDriver driver;

    private By firstResult = By.cssSelector(".mw-search-result-heading a");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openFirstResult() {
        driver.findElement(firstResult).click();
    }
}