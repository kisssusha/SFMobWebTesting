package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "div.mw-search-result-heading a")
    private List<WebElement> searchResults;

    @FindBy(css = "ul.mw-search-results li")
    private List<WebElement> searchResultItems;

    @FindBy(css = ".mw-search-nonefound")
    private WebElement noResultsMessage;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public int getSearchResultsCount() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfAllElements(searchResults),
                    ExpectedConditions.visibilityOfAllElements(searchResultItems),
                    ExpectedConditions.visibilityOf(noResultsMessage)
            ));

            if (!searchResults.isEmpty()) {
                return searchResults.size();
            }
            if (!searchResultItems.isEmpty()) {
                return searchResultItems.size();
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private WebElement getFirstSearchResult() {
        if (!searchResults.isEmpty()) {
            return searchResults.getFirst();
        }

        if (!searchResultItems.isEmpty()) {
            return searchResultItems.getFirst().findElement(By.cssSelector("a"));
        }

        throw new NoSuchElementException("Результаты поиска на Википедии не найдены");
    }

    public void openFirstResult() {
        WebElement firstResult = getFirstSearchResult();
        wait.until(ExpectedConditions.elementToBeClickable(firstResult)).click();
    }
}