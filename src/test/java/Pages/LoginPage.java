package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public LoginPage(AppiumDriver driver, Properties config)
    {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    private By burgerMenuButtonNativeLocator = By.xpath("//android.widget.Button)");
    private By burgerMenuButtonWebLocator = By.xpath("//button[@class='nav-burger']");

    private By signInButtonNativeLocator = By.xpath("//android.widget.Button[@text='Sign In']");
    private By signInButtonWebLocator = By.xpath("//button[@class='nav-signin']");

    private By emailFieldNativeLocator = By.xpath("//android.widget.EditText[@hint='Email']");
    private By emailFieldWebLocator = By.id("login-email");

    private By passwordFieldNativeLocator = By.xpath("//android.widget.EditText[@hint='Password']");
    private By passwordFieldWebLocator = By.id("login-password");

    private By loginButtonNativeLocator = By.xpath("//android.widget.Button[@text='Login']");
    private By loginButtonWebLocator = By.id("login-button");

    private WebElement getElement(By nativeLocator, By webLocator)
    {
       String execType = config.getProperty("executionType").trim();

       if (execType.equalsIgnoreCase("nativeApp"))
       {
           return wait.until(ExpectedConditions.elementToBeClickable(nativeLocator));

    }else if(execType.equalsIgnoreCase("mobileWeb"))
       {
           return wait.until(ExpectedConditions.elementToBeClickable(webLocator));
       }else
       {
           throw new RuntimeException("Unsupported executionType: " + execType);
       }
    }

    public void clickBurgerMenuButton() {
        getElement(burgerMenuButtonNativeLocator, burgerMenuButtonWebLocator).click();
    }

    public void clickSignInButton() {
        getElement(signInButtonNativeLocator, signInButtonWebLocator).click();
    }
    public void enterEmail(String email) {
        WebElement emailElement = getElement(emailFieldNativeLocator, emailFieldWebLocator);
        emailElement.click();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = getElement(passwordFieldNativeLocator, passwordFieldWebLocator);
        passwordElement.click();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        getElement(loginButtonNativeLocator, loginButtonWebLocator).click();
    }
}
