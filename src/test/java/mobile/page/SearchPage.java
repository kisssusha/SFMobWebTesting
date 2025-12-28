package mobile.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage {

    private final AndroidDriver driver;

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void clickFirstSearchResult() {
        List<WebElement> results = driver.findElements(
                AppiumBy.id("org.wikipedia:id/page_list_item_title")
        );
        if (!results.isEmpty()) {
            results.getFirst().click();
        }
    }
}
