package mobile.test;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.web.ArticlePage;
import pages.web.MainPage;
import pages.web.SearchResultsPage;
import utils.WebDriverFactory;

public class WikipediaWebTest {

    private WebDriver driver;
    private MainPage mainPage;
    private SearchResultsPage searchResultsPage;
    private ArticlePage articlePage;

    @BeforeClass
    public void setup() {
        driver = WebDriverFactory.createDriver();
        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        articlePage = new ArticlePage(driver);
    }

    / ✅ Сценарий 1: сайт открывается */
    @Test
    public void siteShouldOpen() {
        mainPage.open();
        Assert.assertTrue(driver.getTitle().contains("Wikipedia"));
    }

    / ✅ Сценарий 2: переход на английскую версию */
    @Test
    public void shouldOpenEnglishVersion() {
        mainPage.open();
        mainPage.selectEnglish();
        Assert.assertTrue(driver.getCurrentUrl().contains("en.wikipedia.org"));
    }

    / ✅ Сценарий 3: поиск статьи */
    @Test
    public void searchShouldReturnResults() {
        mainPage.open();
        mainPage.selectEnglish();
        mainPage.search("Selenium");
        Assert.assertTrue(driver.getTitle().contains("Search"));
    }

    / ✅ Сценарий 4: открытие статьи и проверка элементов */
    @Test
    public void articleShouldOpenAndContainTOC() {
        mainPage.open();
        mainPage.selectEnglish();
        mainPage.search("Selenium");
        searchResultsPage.openFirstResult();

        Assert.assertEquals(articlePage.getTitleText(), "Selenium");
        Assert.assertTrue(articlePage.isTableOfContentsDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}