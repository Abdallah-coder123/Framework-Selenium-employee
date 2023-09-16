package WebTest;

import Helpers.ActionsHelper;
import Pages.Web.CaseType;
import Pages.Web.LoginPage;
import TestBase.WebBase.WebTestBase;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class Testing extends WebTestBase {

  @Test(description = "TC001")
  public void testCase() {
    System.out.println("case Type : " + CaseType.valueOf("all"));
    LoginPage loginPage = new LoginPage(driver);
    ActionsHelper actions = new ActionsHelper();
    actions.waitForLoad("/GetExternalAuthenticationProviders", 200, driver);
    loginPage.login();
    actions.waitForLoad("/GetCurrentLoginInformations", 200, driver);
    actions.refreshPage(driver);

    //        Assertion
    System.out.println(loginPage.welcome);
    String expectedResult = String.valueOf(loginPage.welcome);
    String actualResult = "Does not exist";
    actions.verifyResult(
      actualResult,
      expectedResult,
      loginPage.stcPay.isDisplayed()
    );
  }
}
