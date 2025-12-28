package mobile.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ArticlePage {

    private WebDriver driver;

    private By title = By.id("firstHeading");
    private By tableOfContents = By.id("toc");

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(title).getText();
    }

    public boolean isTableOfContentsDisplayed() {
        return driver.findElement(tableOfContents).isDisplayed();
    }
}