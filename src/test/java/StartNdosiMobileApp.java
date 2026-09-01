import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class StartNdosiMobileApp {

    public static AndroidDriver driver;

    @Before
    public void setup(){

    }

    @Test
    public void launchNdosiQAApp() throws MalformedURLException {
        // Code to launch the Ndosi QA mobile app using Appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app",System.getProperty("user.dir") + "/src/main/Apps/app-qa-release.apk");


        driver = new AndroidDriver(new URL("http://192.168.3.122:4723/"),capabilities);


    }
    @After
    public void quitApp(){
        System.out.println("App successfully started");
        driver.findElement(By.id("com.ndosi.mobile:id/btn_close")).click();

    }
    }

