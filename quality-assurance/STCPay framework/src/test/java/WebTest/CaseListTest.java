package WebTest;

import Helpers.ActionsHelper;
import Helpers.DateHelper;
import Pages.Web.CaseListPage;
import Pages.Web.LoginPage;
import Reporting.WebReport.WebTestListener;
import TestBase.WebBase.WebTestBase;
import Utils.Retries;
import com.github.javafaker.Faker;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(WebTestListener.class)
public class CaseListTest extends WebTestBase {
    ActionsHelper actions = new ActionsHelper();
    DateHelper dates = new DateHelper();
    Faker faker = new Faker();

    @Test(description = "TC002 Verify that the CustId Field Exist",
            retryAnalyzer = Retries.class,
            priority = 1)
    public void CustIDVisibility() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.openCaseList(driver);
        actions.waitForExistence(caseListPage.custId, "CustId Field", driver);
        actions.verifyResult(
                "Does not exist!",
                "Cust ID label should be displayed",
                caseListPage.custId.isDisplayed() &&
                        caseListPage.custId.getDomProperty("placeholder").equalsIgnoreCase("Search...")
        );
    }

    @Test(
            description = "TC003 select (CaseType All) (Status All) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 2
    )
    public void selectAll_All_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "-1", false);
        actions.waitForExistence(caseListPage.caseType, "Case Type DDL", driver);
        actions.waitForTableExistence(caseListPage.getTypeList(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "That there is No data in Table",
                "That the table contains valid data",
                caseListPage.textRow.getText() != "No data"
        );
    }

    @Test(
            description = "Select (CaseType Onboarding) (Status All) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 3
    )
    public void selectOnboarding_All_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "2", "-1", "-1", false);
        actions.waitForExistence(caseListPage.caseType, "Case Type DDL", driver);
        actions.waitForTableExistence(caseListPage.getTypeList(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values are Onboarding inside CaseType column",
                "All values are Onboarding inside CaseType column",
                actions.VerifyTableContent(driver, caseListPage.getTypeList(driver), caseListPage.tablePath, "Onboarding")
        );
    }

    @Test(
            description = "Select (CaseType Remittance) (Status All) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 4
    )
    public void selectRemittance_All_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "1", "-1", "-1", false);
        actions.waitForExistence(caseListPage.caseType, "Case Type DDL", driver);
        actions.waitForTableExistence(caseListPage.getTypeList(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values are Remittance inside CaseType column",
                "All values are Remittance inside CaseType column",
                actions.VerifyTableContent(driver, caseListPage.getTypeList(driver), caseListPage.tablePath, "Remittance")
        );
    }

    @Test(
            description = "Select (CaseType MerchantOnboarding) (Status All) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 5
    )
    public void selectMerchantOboarding_StatusAll_StageAll()
            throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "3", "-1", "-1", false);
        actions.waitForExistence(caseListPage.caseType, "Case Type DDL", driver);
        actions.waitForTableExistence(caseListPage.getTypeList(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values are MerchantOnboarding inside CaseType column",
                "All values are MerchantOnboarding inside CaseType column",
                actions.VerifyTableContent(driver, caseListPage.getTypeList(driver), caseListPage.tablePath, "MerchantOnboarding")
        );
    }

    @Test(
            description = "Select (CaseType All) (Status NA) (Stage All) ",
            retryAnalyzer = Retries.class,
            priority = 6
    )
    public void selectCaseAll_StatusNA_StageAll()
            throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "0", "-1", false);
        actions.waitForExistence(caseListPage.automationStatus, "Automation Status DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStatus(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values NA inside Automation Status column",
                "All values to be NA inside Automation Status column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStatus(driver), caseListPage.tablePath, "NA")
        );
    }

    @Test(
            description = "Select (CaseType All) (Status  Accepted) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 7
    )
    public void selectCaseTypeAll_StatusAccepted_StageAll()
            throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "2", "1", "-1", false);
        actions.waitForExistence(caseListPage.automationStatus, "Automation Status DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStatus(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values Accepted inside AutoStatus column",
                "All values to be Accepted inside AutoStatus column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStatus(driver), caseListPage.tablePath, "Accepted")
        );
    }

    @Test(
            description = "Date Picker",
            retryAnalyzer = Retries.class,
            priority = 8
    )
    public void datePicker() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "-1", true);
        actions.sleepBySeconds(5);
        actions.waitForTableExistence(caseListPage.verifyDate(driver), "Search Table",
                driver, caseListPage.tablePath);
        String searchedDate = caseListPage.verifyDate(driver).get(1).getText();
        actions.verifyResult(
                "That the date is not valid ",
                "That the date is valid",
                dates.validateJavaDate(searchedDate)
        );
    }

    //    Murad
    @Test(
            description = "Verify that the User can search by MTCN",
            retryAnalyzer = Retries.class,
            priority = 9
    )
    public void VerfiyMTCN() throws InterruptedException {
        loginPage.successfulLogin(driver);
        String firstMTCN = (String) mtcn.get(1);
        caseListPage.fillMtcn(driver, firstMTCN);
        actions.waitForExistence(caseListPage.textRow, "Search Table", driver);
        actions.verifyResult(
                "That there is No data in Table",
                "That the table contains valid data",
                caseListPage.textRow.getText() != "No data"
        );
    }

    @Test(
            description = "select (CaseType All) (Status Rejected) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 11
    )
    public void selectAll_Rejected_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "2", "2", "-1", false);
        actions.waitForExistence(caseListPage.automationStatus, "Automation Status DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStatus(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values Rejected inside AutoStatus column",
                "All values are Rejected inside AutoStatus column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStatus(driver), caseListPage.tablePath, "Rejected")
        );
    }

    //    There is no fraud Data
    @Test(
            description = "Select (CaseType All) (Status Fraud) (Stage All)",
            retryAnalyzer = Retries.class,
            enabled = false,
            priority = 12
    )
    public void selectAll_Fraud_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "4", "-1", false);
        actions.waitForExistence(caseListPage.automationStatus, "Automation Status DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStatus(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values Fraud inside AutoStatus column",
                "All values are Fraud inside AutoStatus column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStatus(driver), caseListPage.tablePath, "Fraud")
        );
    }

    @Test(
            description = "Select (CaseType All) (Status Suspended) (Stage All)",
            retryAnalyzer = Retries.class,
            priority = 13
    )
    public void selectAll_Suspended_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "3", "-1", false);
        actions.waitForExistence(caseListPage.automationStatus, "Automation Status DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStatus(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values Suspended inside AutoStatus column",
                "All values are Suspended inside AutoStatus column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStatus(driver), caseListPage.tablePath, "Suspended")
        );
    }

    @Test(
            description = "Select (CaseType All) (Status All) (Stage Pending)",
            retryAnalyzer = Retries.class,
            priority = 14
    )
    public void selectAll_All_Pending() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "1", false);
        actions.waitForExistence(caseListPage.AutomationStage, "Automation Stage DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStage(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values Pending inside AutoStage column",
                "All values are Pending inside AutoStage column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStage(driver), caseListPage.tablePath, "Pending")
        );
    }

    @Test(
            description = "Select (CaseType All) (Status All) (Stage CompletedByUser)",
            retryAnalyzer = Retries.class,
            priority = 15
    )
    public void selectAll_All_CompletedByUser() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "2", false);
        actions.waitForExistence(caseListPage.AutomationStage, "Automation Stage DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStage(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values CompletedByUser inside AutoStage column",
                "All values are CompletedByUser inside AutoStage column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStage(driver), caseListPage.tablePath, "CompletedByUser")
        );
    }

    @Test(
            description = "Select (CaseType All) (Status All) (Stage CompletedBySystem)",
            retryAnalyzer = Retries.class,
            priority = 16
    )
    public void selectAll_All_CompletedBySystem() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "3", false);
        actions.waitForExistence(caseListPage.AutomationStage, "Automation Stage DDL", driver);
        actions.waitForTableExistence(caseListPage.getAutoStage(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult(
                "Not all values CompletedBySystem inside AutoStage column",
                "All values are CompletedBySystem inside AutoStage column",
                actions.VerifyTableContent(driver, caseListPage.getAutoStage(driver), caseListPage.tablePath, "CompletedBySystem")
        );
    }

    @Test(
            description = "Verify the CaseId does not Accept Characters",
            retryAnalyzer = Retries.class,
            priority = 17
    )
    public void VerifyCaseIdNotAcceptCharacters() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.caseId(driver, "#####");
        actions.verifyResult(
                "Error popup did not appear",
                "Error popup to appear",
                caseListPage.errorPopUp.isDisplayed() &&
                        caseListPage.errorPopUp.getDomProperty("role").equalsIgnoreCase("dialog")
        );
    }

    @Test(
            description = "Verify the sender Name is english",
            enabled = true,
            retryAnalyzer = Retries.class,
            priority = 18
    )
    public void verifySenderNameEnglish() throws InterruptedException {
        loginPage.successfulLogin(driver);
        String name = faker.name().firstName();
        caseListPage.senderName(driver, name);
        actions.waitForExistence(caseListPage.senderNameEng, "Sender Name EN.", driver);
        System.out.println("name >>>>>>>" + name);
        actions.verifyResult(
                "Not English Language",
                "It is English",
                name.matches("^[a-zA-Z][a-zA-Z\\\\s]+$")
        );
    }

    @Test(description = "Verify that the number of rows is 5 in table",
            retryAnalyzer = Retries.class,
            priority = 19)
    public void VerifyTheShowFiveRows() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "-1", false);
        actions.waitForTableExistence(caseListPage.getAutoStage(driver), "Search Table",
                driver, caseListPage.tablePath);
        actions.verifyResult("That the Table does not contain 5 rows",
                "That the Table contains 5 rows)",
                actions.tableSize(driver, caseListPage.tablePath) == 5);
    }

    @Test(description = "Verify that the next button is clickable",
            retryAnalyzer = Retries.class,
            priority = 20)
    public void theNextButtonIsClickable() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.NextButtonClickable(driver);
        actions.verifyResult(
                "Not Activated",
                "Activated",
                Integer.parseInt(caseListPage.pageNumBtn.get(1).getText()) == 2
        );
    }

    @Test(description = "Verify that the user can reject the case in the 'ACTION' section",
            retryAnalyzer = Retries.class,
            priority = 21)
    public void rejectAction()
            throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "1", false);
        String oldId = caseListPage.getCaseId();
        caseListPage.userAction(driver, "Reject", oldId);
        String newId = caseListPage.getCaseId();
        actions.verifyResult("CaseId still Exist",
                "New CaseId",
                !newId.equalsIgnoreCase(oldId));
    }

    @Test(description = "Verify that the user can accept the Case in the 'ACTION' section",
            retryAnalyzer = Retries.class,
            priority = 23)
    public void verifyWritrAccept() throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.search(driver, "-1", "-1", "1", false);
        String oldId = caseListPage.getCaseId();
        caseListPage.userAction(driver, "Accept", oldId);
        String newId = caseListPage.getCaseId();
        actions.verifyResult("CaseId still Exist",
                "New CaseId",
                !newId.equalsIgnoreCase(oldId));
    }

    // Go Back
    @Test(description = "Verify that the system refresh the case info page successfully",
            retryAnalyzer = Retries.class,
            priority = 24)
    public void refresh()
            throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.pageRefresh(driver);
        actions.waitForLoad("api/services/app/Compliance", 200, driver);
        actions.verifyResult("Refersh Button is not Working",
                "Refresh Button is Working",
                true);
    }

    @Test(description = "Verify that the system close the case info page successfully",
            retryAnalyzer = Retries.class,
            priority = 25)
    public void closeCaseDetails()
            throws InterruptedException {
        loginPage.successfulLogin(driver);
        caseListPage.closeCaseDetails(driver);
        actions.verifyResult
                ("Close Button is Not working",
                        "Close button is working",
                        actions.isElementExist(caseListPage.close) == false);
    }

    @Test(description = "Verify that the CaseID popup is visible",
            retryAnalyzer = Retries.class,
            priority = 26)
    public void VerifyThepoupCaseIdisdisplay() throws InterruptedException {
        CaseListPage caseListPage = new CaseListPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successfulLogin(driver);
        caseListPage.caseId(driver, "+-*/");
        actions.verifyResult(
                "Does Not Exist",
                "Special Character error popup",
                caseListPage.errorPopUp.isDisplayed() &&
                        caseListPage.errorPopUp.getDomProperty("role").equalsIgnoreCase("dialog")
        );
    }
}