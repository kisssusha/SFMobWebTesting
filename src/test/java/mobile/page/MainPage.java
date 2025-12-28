package mobile.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchFor(String query) {
        WebElement searchContainer = wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("org.wikipedia:id/search_container")
                )
        );
        searchContainer.click();

        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("org.wikipedia:id/search_src_text")
                )
        );

        searchInput.clear();
        searchInput.sendKeys(query);
    }
}
