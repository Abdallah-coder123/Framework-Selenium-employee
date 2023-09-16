package TestBase.WebBase;

import Helpers.ActionsHelper;
import Helpers.EmailHelper;
import Pages.Web.CaseListPage;
import Pages.Web.LoginPage;
import Reporting.WebReport.ExtentManager;
import TestBase.TestDataBase.WebTestData.EmployeePortalTestData;
import TestBase.WebBase.Browsers.ChromeBrowser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

public class WebTestBase extends EmployeePortalTestData {

    protected WebDriver driver;
    protected String baseUrl = empBaseUrl + uiServer;

    @BeforeMethod(enabled = true)
    public void setUp(ITestContext context) {
        getDriver().get(baseUrl);
        pageInstantiation(driver);


        //        Context for Report
        //        >>>>>>>>>>>>>>>>>>
        context.setAttribute("WebDriver", driver);
        //    //        //Login
        //            LoginPage loginPage = new LoginPage(driver);
        //            ActionsHelper actions = new ActionsHelper();
        //            actions.waitForLoad(
        //                    "/GetExternalAuthenticationProviders",
        //                    200,
        //                    driver
        //            );
        //            loginPage.login();
        //            actions.waitForLoad("/GetCurrentLoginInformations", 200, driver);
        //            actions.refreshPage(driver);

    }


    public WebDriver getDriver() {
        ChromeBrowser chrome = new ChromeBrowser();
        driver = chrome.openBrowser();
        return driver;
    }


    @AfterMethod(enabled = true)
    public void quit() {
        driver.quit();
    }

    @AfterSuite(enabled = false)
    public static void sendEmail() throws ParseException {
        EmailHelper mail = new EmailHelper();
        ExtentManager manager = new ExtentManager();
        mail.sendEmail(
                System.getProperty("user.dir") + "/src/main/resources/Reports/WebReports/" + manager.reportFileName,
                "Web Application Test for "
        );
    }

}
