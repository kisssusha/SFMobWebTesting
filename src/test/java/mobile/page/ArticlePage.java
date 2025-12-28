package mobile.page;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ArticlePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_title_text")
    private WebElement articleTitle;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_subtitle_text")
    private WebElement articleSubtitle;

    @AndroidFindBy(accessibility = "Navigate up")
    private WebElement backButton;

    @AndroidFindBy(id = "org.wikipedia:id/page_toolbar_button_show_toc")
    private WebElement tableOfContentsButton;

    @AndroidFindBy(id = "org.wikipedia:id/toc_list")
    private WebElement tableOfContentsList;

    public ArticlePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }

    public String getArticleTitle() {
        wait.until(ExpectedConditions.visibilityOf(articleTitle));
        return articleTitle.getText();
    }

    public void navigateBack() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
    }
}
