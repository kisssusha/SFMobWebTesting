package web.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import web.page.ArticlePage;
import web.page.MainPage;
import web.page.SearchResultsPage;
import web.config.WebDriverFactory;

public class WikipediaWebTest {


    private WebDriver driver;
    private MainPage mainPage;
    private SearchResultsPage searchResultsPage;
    private ArticlePage articlePage;

    @BeforeClass
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        articlePage = new ArticlePage(driver);
    }

    // Сценарий 1
    @Test
    public void siteShouldOpen() {
        mainPage.open();
        Assert.assertTrue(driver.getTitle().contains("Wikipedia"));
    }

    // Сценарий 2
    @Test
    public void shouldOpenEnglishVersion() {
        mainPage.open();
        mainPage.selectEnglish();
        Assert.assertTrue(driver.getCurrentUrl().contains("en.wikipedia.org"));
    }

    // Сценарий 3
    @Test
    public void testArticleLinksFromSearch() {
        mainPage.open();
        mainPage.search("Test");
        int count = searchResultsPage.getSearchResultsCount();
        Assert.assertTrue(
                count >= 0,
                "Кол-во вариантов ответа на запрос меньше нуля: " + count
        );
        // Сценарий 4
        if (count > 0) {
            searchResultsPage.openFirstResult();
            Assert.assertTrue(
                    articlePage.isTableOfContentsDisplayed(),
                    "После перехода по результату контент статьи не отображается"
            );
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}