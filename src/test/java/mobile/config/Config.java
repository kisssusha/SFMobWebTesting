package mobile.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is = Config.class
                .getClassLoader()
                .getResourceAsStream("mobile.properties")) {

            if (is == null) {
                throw new RuntimeException("mobile.properties not found in classpath");
            }
            PROPERTIES.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось загрузить mobile.properties", e);
        }
    }

    private static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Свойство не найдено: " + key);
        }
        return value;
    }

    public static String getDeviceName() {
        return getProperty("mobile.device.name");
    }

    public static String getAppiumUrl() {
        return getProperty("mobile.appium.server.url");
    }

    public static String getPlatformName() {
        return getProperty("mobile.platform.name");
    }

    public static String getPlatformVersion() {
        return getProperty("mobile.platform.version");
    }

    public static String getAutomationName() {
        return getProperty("mobile.automation.name");
    }

    public static String getAppPath() {
        return getProperty("mobile.app.path");
    }

    public static String getAppPackage() {
        return getProperty("mobile.app.package");
    }

    public static String getAppActivity() {
        return getProperty("mobile.app.activity");
    }
}
