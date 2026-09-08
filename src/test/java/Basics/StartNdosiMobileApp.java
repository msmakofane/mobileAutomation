package Basics;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class StartNdosiMobileApp {

    public static AndroidDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/main/Apps/app-qa-release.apk");

        driver = new AndroidDriver(new URL("http://192.168.3.122:4723/"), capabilities);
    }

    @Test
    public void launchNdosiQAApp() throws MalformedURLException {
        // Code to launch the Ndosi QA mobile app using Appium

        driver.findElement(By.xpath("//android.widget.Button")).click();
    }

    @AfterTest
    public void quitApp() {
        System.out.println("App successfully started");


    }
}

