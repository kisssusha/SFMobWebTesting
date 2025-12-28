package web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://www.wikipedia.org/");
    }

    public void selectEnglish() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("js-link-box-en"))).click();
    }

    public void search(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchInput")));
        input.clear();
        input.sendKeys(text);
        input.sendKeys(Keys.ENTER);
    }
}
