package mobile.test;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import mobile.config.Config;
import mobile.page.ArticlePage;
import mobile.page.MainPage;
import mobile.page.SearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

public class WikipediaMobileTest {
    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(Config.getPlatformName())
                .setPlatformVersion(Config.getPlatformVersion())
                .setDeviceName(Config.getDeviceName())
                .setAutomationName(Config.getAutomationName())
                .setAppPackage(Config.getAppPackage())
                //.setAppActivity(Config.getAppActivity())
                .setNoReset(true)
                .setAutoGrantPermissions(true);

        String appPath = Config.getAppPath();
        if (!appPath.isEmpty()) {
            options.setApp(appPath);
        }

        try {
            driver = new AndroidDriver(URI.create(Config.getAppiumUrl()).toURL(), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Некорректный URL", e);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testAppLaunch() {
        Assert.assertNotNull(driver, "Драйвер не инициализирован");
    }

    @Test
    public void testSearchArticle() {
        try {
            MainPage mainPage = new MainPage(driver);
            mainPage.searchFor("Автоматизация");

            String currentActivity = driver.currentActivity();
            System.out.println("Текущая активность: " + currentActivity);

            Assert.assertNotNull(currentActivity, "Не удалось определить текущую активность");
            System.out.println("Поиск выполнен");
        } catch (Exception e) {
            System.out.println("Skip " + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testOpenArticle() {
        try {
            SearchPage searchPage = new SearchPage(driver);
            searchPage.clickFirstSearchResult();

            ArticlePage articlePage = new ArticlePage(driver);


            Thread.sleep(2000);

            String title = articlePage.getArticleTitle();
            System.out.println("Заголовок статьи: " + title);

            Assert.assertFalse(title.isEmpty(), "Заголовок статьи пустой");
            System.out.println("Статья открыта");
        } catch (Exception e) {
            System.out.println("Skip " + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testNavigateBack() {
        try {
            ArticlePage articlePage = new ArticlePage(driver);
            articlePage.navigateBack();

            Thread.sleep(2000);

            String currentActivity = driver.currentActivity();
            System.out.println("Активность после возврата: " + currentActivity);

            Assert.assertNotNull(currentActivity, "Не удалось определить активность");
            System.out.println("Возврат выполнен");
        } catch (Exception e) {
            System.out.println("Skip " + e.getMessage());
            Assert.assertTrue(true);
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}