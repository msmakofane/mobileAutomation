package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    protected AppiumDriver driver;
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;

    @BeforeClass
    public void setUpAndLogin() throws IOException {

        config = new Properties();
        try (InputStream inputStream = BaseTest.class.getClassLoader().getResourceAsStream("configs/config.properties")) {
            if (inputStream == null) {
                throw new IOException("Could not find resource: configs/config.properties");
            }
            config.load(inputStream);
        }

        DriverFactory.initDriver(config);
        driver = DriverFactory.getDriver();

        loginPage = new LoginPage(driver, config);
        LoginToNdosiAutomation();

    }

    public void LoginToNdosiAutomation() {
        loginPage.clickBurgerMenuButton();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
        }
    }

