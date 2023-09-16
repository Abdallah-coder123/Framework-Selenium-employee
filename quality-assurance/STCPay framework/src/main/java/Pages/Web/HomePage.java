package Pages.Web;

import TestBase.WebBase.WebPageBase;
//import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends WebPageBase {

  //    Constructor

  public HomePage(WebDriver driver) {
    super(driver);
  }

  //    Elements
  @FindBy(xpath = "//h1[contains(@class, \"header\")]")
  public WebElement header;

  @FindBy(xpath = "//li[@id=\"menu-item-\"]")
  public WebElement personal;

//  @Step("Navigate to URL")
  public void navigateToUrl(String url) {
    System.out.println("STCPay TEST");
    driver.get(url);
  }

//  @Step("Assertion")
  public void logoText(String actualResult, String expectedResult) {
    Assert.assertEquals(actualResult, expectedResult);
  }
}
