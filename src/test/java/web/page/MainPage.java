package mobile.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;

    private By searchInput = By.id("searchInput");
    private By searchButton = By.xpath("//button[@type='submit']");
    private By englishLink = By.id("js-link-box-en");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.wikipedia.org/");
    }

    public void selectEnglish() {
        driver.findElement(englishLink).click();
    }

    public void search(String text) {
        driver.findElement(searchInput).sendKeys(text);
        driver.findElement(searchButton).click();
    }
}
