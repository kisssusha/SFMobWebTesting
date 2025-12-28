package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ArticlePage {


    private final WebDriver driver;
    private final WebDriverWait wait;


    @FindBy(id = "firstHeading")
    private WebElement title;

    @FindBy(id = "toc")
    private WebElement tableOfContents;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isTableOfContentsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(tableOfContents));
        return tableOfContents.isDisplayed();
    }
}