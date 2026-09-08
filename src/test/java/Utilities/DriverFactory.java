package Utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Properties;

public class DriverFactory {

    static AppiumDriver driver;

    public static void initDriver(Properties config) throws MalformedURLException {
        if (driver != null) return;

        String platformName = config.getProperty("platformName").trim();
        String executionType = config.getProperty("executionType").trim();
        String appiumURL = config.getProperty("appiumServer").trim();

        if (platformName.equalsIgnoreCase("Android")) {
            initAndroidDriver(config, executionType, appiumURL);
        } else if (platformName.equalsIgnoreCase("IOS")) {
            initIOSDriver(config, executionType, appiumURL);
        } else {
            throw new RuntimeException("Unsupported platformName: " + platformName);
        }

    }


    private static void initAndroidDriver(Properties config, String executionType, String appiumURL)
            throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(config.getProperty("platformName"))
                .setAutomationName(config.getProperty("automationName"));

        if (executionType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName(config.getProperty("browserName"));
            System.out.println("Launching the Android Chrome browser for mobile web testing");
        } else if (executionType.equalsIgnoreCase("nativeApp")) {

            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            options.setApp(appPath);
            System.out.println("Launching the Android native app for testing");
        } else {
            throw new RuntimeException("Unsupported executionTpe for Android: " + executionType);
        }

        driver = new AppiumDriver(URI.create(appiumURL).toURL(), options);
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            String webURL = config.getProperty("webURL");
            driver.get(webURL);
        }

    }

    private static void initIOSDriver(Properties config, String executionType, String appiumURL)
            throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName(config.getProperty("platformName"))
                .setAutomationName(config.getProperty("automationName"));

        if (executionType.equalsIgnoreCase("mobileWeb")) {
            options.withBrowserName(config.getProperty("browserName"));
            System.out.println("Launching the IOS Safari browser for mobile web testing");
        } else if (executionType.equalsIgnoreCase("nativeApp")) {

            String appPath = System.getProperty("user.dir") + "/" + config.getProperty("appPath");
            options.setApp(appPath);
            System.out.println("Launching the IOS native app for testing");
        } else {
            throw new RuntimeException("Unsupported executionTpe for IOS: " + executionType);
        }

        driver = new AppiumDriver(URI.create(appiumURL).toURL(), options);
        if (executionType.equalsIgnoreCase("mobileWeb")) {
            String webURL = config.getProperty("webURL");
            driver.get(webURL);
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

