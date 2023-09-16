package Pages.Web;

import Helpers.ActionsHelper;
import TestBase.WebBase.WebPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends WebPageBase {

    ActionsHelper actions = new ActionsHelper();

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='userNameOrEmailAddress']")
    private WebElement username;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Log in']")
    private WebElement login;

    @FindBy(xpath = "//span[contains(text(), 'Welcome to Portal!')]")
    public WebElement welcome;

    @FindBy(xpath = "//img[contains(@alt, 'logo')]")
    public WebElement stcPay;

    public void login() {
        username.sendKeys(adminUser);
        password.sendKeys(adminPass);
        login.click();
    }

    public void successfulLogin(WebDriver driver) {
        actions.waitForLoad("/GetExternalAuthenticationProviders", 200, driver);
        this.login();
        actions.waitForLoad("/GetCurrentLoginInformations", 200, driver);
        actions.refreshPage(driver);
        actions.waitForLoad("/GetProfilePicture", 200, driver);
        actions.sleepBySeconds(1);
    }
}
