package WebTest;

import Helpers.ActionsHelper;
import Helpers.DateHelper;
import TestBase.WebBase.WebTestBase;
import Utils.Retries;
import org.testng.annotations.Test;

public class IbanMakerTest extends WebTestBase {

    ActionsHelper actions = new ActionsHelper();
    DateHelper dates = new DateHelper();

    @Test(
            description = "TC001_Verify that the system display table when the user select 'ALL' from the 'Status' DDL ",
            retryAnalyzer = Retries.class,
            priority = 1
    )
    public void Status_All_Action_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("1", "-1");
        ibanMaker.eleList(driver);
        ibanMaker.searchBtn.click();
        //I don't kNOW when i can assert when choose All_All
    }

    @Test(
            description = "TC002 Verify that the system display table when the user select 'Pending' from 'Status' DDL ",
            retryAnalyzer = Retries.class,
            priority = 2
    )
    public void Status_Pending_Action_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("1", "-1");
        ibanMaker.eleList(driver);
        boolean condition = ibanMaker.checkOnAutoStatusInsideTable(
                1,
                driver
        );
        actions.waitForExistence(
                ibanMaker.statusList.get(0),
                "The status is Pending",
                driver
        );
        actions.verifyResult(
                "Not all values Pending inside Status (table)",
                ibanMaker.statusList.get(0).getText(),
                condition
        );
    }

    @Test(
            description = "TC003 Verify that the system display table when the user select 'Approved' from 'Status' DDL ",
            retryAnalyzer = Retries.class,
            priority = 3
    )
    public void Status_Approved_Action_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("2", "-1");
        ibanMaker.eleList(driver);

        boolean condition = ibanMaker.checkOnAutoStatusInsideTable(
                2,
                driver
        );
        actions.waitForExistence(
                ibanMaker.statusList.get(0),
                "The status is Approved",
                driver
        );
        actions.verifyResult(
                "Not all values Approved inside Status (table)",
                ibanMaker.statusList.get(0).getText(),
                condition
        );
    }

    @Test(
            description = "TC004_Verify that the system display table when the user selects 'Rejected' from 'Statues' DDL",
            retryAnalyzer = Retries.class,
            priority = 4
    )
    public void Status_Rejected_Action_All() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("3", "-1");
        ibanMaker.eleList(driver);
        boolean condition = ibanMaker.checkOnAutoStatusInsideTable(
                3,
                driver
        );
        actions.waitForExistence(
                ibanMaker.statusList.get(0),
                "The status is Rejected",
                driver
        );
        actions.verifyResult(
                "Not all values Rejected inside Status (table)",
                ibanMaker.statusList.get(0).getText(),
                condition
        );
    }

    @Test(
            description = "TC0010_Verify that the system display table when the user selects 'Sanction' from the 'Action' DDL ",
            retryAnalyzer = Retries.class,
            priority = 5
    )
    public void verifyTheSystemDisplaySanction() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("-1", "1");
        ibanMaker.showTypeAction(driver);
        boolean condition = ibanMaker.getTypeAction("Sanction");
        actions.verifyResult(
                "The type of action not sanction",
                ibanMaker.actionTxt.getText(),
                condition
        );
    }

    @Test(
            description = "TC0011_Verify that the system display table when the user selects 'Review' from the 'Action' DDL ",
            retryAnalyzer = Retries.class,
            priority = 6
    )
    public void verifyTheSystemDispalyReview() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("-1", "2");
        ibanMaker.showTypeAction(driver);
        boolean condition = ibanMaker.getTypeAction("Review");
        actions.verifyResult(
                "The type of action not Review",
                ibanMaker.actionTxt.getText(),
                condition
        );
    }

    @Test(
            description = "TC0011 Verify that the system display table when the user select 'Whitelist' from 'Action' DDL",
            retryAnalyzer = Retries.class,
            priority = 7
    )
    public void verifyTheSystemDisplayWhiteList() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("-1", "3");
        ibanMaker.showTypeAction(driver);
        boolean condition = ibanMaker.getTypeAction("Whitelist");
        actions.verifyResult(
                "The type of action not WhiteList",
                ibanMaker.actionTxt.getText(),
                condition
        );
    }

    @Test(
            description = "TC0013_Verify that the system displays 5 rows when the user clicks on 'Search' button",
            retryAnalyzer = Retries.class,
            priority = 8
    )
    public void VerifyIsFiveRows() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("-1", "3");
        boolean condition = ibanMaker.verifyIsFiveRow();
        actions.verifyResult(
                "Not five rows",
                "The table is contains five rows",
                condition
        );
    }

    @Test(
            description = "TC0022_Verify that 'Show details' button is displayed the details correctly",
            retryAnalyzer = Retries.class,
            priority = 9
    )
    public void showDetailsBtnIsWorking() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("-1", "3");
        ibanMaker.showDetailsBtn.click();
        actions.scrollPage(driver, 400, 450);
        boolean condition = ibanMaker.getTypeAction("Whitelist");
        actions.waitForExistence(
                ibanMaker.requestDetails,
                "Request Details",
                driver
        );
        actions.verifyResult(
                "The showDetails button not working",
                "The details button is work",
                condition
        );
    }

    @Test(
            description = "Verify the System show pending and sanction when the user select (Pending,Sanction)",
            retryAnalyzer = Retries.class,
            priority = 10
    )
    public void Select_Pending_Sanction() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("1", "1");
        actions.waitForExistence(
                ibanMaker.statusCell,
                "Pending",
                driver
        );
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                1,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Sanction"
        );

        actions.scrollPage(driver, 400, 400);
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Pending and The type of action not sanction",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "Verify that the system display table when the user selects 'Approved' from 'Status,' sanction' from Action",
            retryAnalyzer = Retries.class,
            priority = 11
    )
    public void Select_Approved_Sanction() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("2", "1");
        actions.waitForExistence(
                ibanMaker.statusCell,
                "Approved",
                driver
        );
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                2,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Sanction"
        );

        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Approved and type of action not sanction",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "Verify that the system display table when the user selects ('Rejected','Sanction')  ",
            retryAnalyzer = Retries.class,
            priority = 12
    )
    public void Select_Rejected_Sanction() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("3", "1");
        actions.waitForExistence(
                ibanMaker.statusCell,
                "Rejected",
                driver
        );
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                3,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Sanction"
        );
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Rejected and type of action not sanction",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "Verify that the system display table when the user selects ('Rejected','Review')",
            retryAnalyzer = Retries.class,
            priority = 13
    )
    public void Select_Rejected_Review() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("3", "2");
        actions.waitForExistence(
                ibanMaker.statusCell,
                "Rejected",
                driver
        );
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                3,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Review"
        );

        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Rejected and type of action not Review",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = " Verify that the system display table when the user selects ('Pending','Review')",
            retryAnalyzer = Retries.class,
            priority = 14
    )
    public void Select_Pending_Review() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("1", "2");
        actions.waitForExistence(
                ibanMaker.statusCell,
                "Pending",
                driver
        );
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                1,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Review"
        );

        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Pending and type of action not Review",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "Verify that the system display table when the user selects ('Approved','Review') ",
            retryAnalyzer = Retries.class,
            priority = 15
    )
    public void Select_Approved_Review() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("2", "2");
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                2,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Review"
        );

        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Approved and type of action not Review",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "Verify that the system display table when the user selects ('Pending','WhiteList')",
            retryAnalyzer = Retries.class,
            priority = 16
    )
    public void Select_Pending_WhiteList() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("1", "3");
        //  actions.waitForExistence(ibanMaker.statusCell,"Pending",driver);
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                1,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Whitelist"
        );
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Pending and the type of action not WhiteList",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    //
    @Test(
            description = "Verify that the system display table when the user selects ('Approved','WhiteList') ",
            retryAnalyzer = Retries.class,
            priority = 16
    )
    public void Select_Approved_WhiteList() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("2", "3");
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                2,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Whitelist"
        );
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not approved and the type of action not WhiteList",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "Verify that the system display table when the user selects ('Rejected','WhiteList') ",
            retryAnalyzer = Retries.class,
            priority = 16
    )
    public void Select_Rejected_WhiteList() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("3", "3");
        boolean conditionStatusInsideTable = ibanMaker.checkOnAutoStatusInsideTable(
                3,
                driver
        );
        ibanMaker.showDetailsBtn.click();
        boolean conditionTypeAction = ibanMaker.getTypeAction(
                "Whitelist"
        );
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "Type of action",
                driver
        );
        actions.verifyResult(
                "The type of status not Rejected and the type of action not WhiteList",
                ibanMaker.actionTxt.getText(),
                conditionStatusInsideTable && conditionTypeAction
        );
    }

    @Test(
            description = "TC0026_UI_Verify that the 'Close' button is clickable",
            retryAnalyzer = Retries.class,
            priority = 17
    )
    public void closeButtonIsClickable() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.dropDownList("3", "3");
        ibanMaker.searchBtn.click();
        actions.sleepBySeconds(1);
        ibanMaker.showDetailsBtn.click();
        ibanMaker.closeButton.click();
        boolean condition = actions.isElementExist(
                ibanMaker.RequestCaseDetails
        );
        actions.verifyResult(
                "The Button is bot clickable",
                "The button is clickable",
                !condition
        );
    }

    @Test(
            description = "TC0018_Verify that the System opens window 'new Change Request' when clicking new",
            retryAnalyzer = Retries.class,
            priority = 18
    )
    public void newButtonIsWork() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.NewButton.click();
        actions.waitForExistence(
                ibanMaker.poup,
                "Dispaly Poup",
                driver
        );
        actions.verifyResult(
                "The poup does not display",
                "The new button is work and the poup is displayed",
                ibanMaker.poup.isDisplayed()
        );
    }

    @Test(
            description = "TC0019_Verify that the 'New' button can add new request of type 'Sanction' correctly",
            retryAnalyzer = Retries.class,
            priority = 19
    )
    public void addNewSanction() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.NewButton.click();
        ibanMaker.addNewChangeRequest(
                ibanMaker.sanctionRadioButton,
                "938823102118677"
        );
        ibanMaker.dropDownList("1", "1");
        ibanMaker.showDetailsBtn.click();
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "The Type is Sanction",
                driver
        );
        actions.verifyResult(
                "Not Add Sanction",
                "Add Sanction is successfully",
                ibanMaker.checkOnAutoStatusInsideTable(1, driver) &&
                        ibanMaker.actionTxt.getText().contains("Sanction")
        );
    }

    @Test(
            description = "TC0020_Verify that the 'New' button can add new request of type 'Review' correctly ",
            retryAnalyzer = Retries.class,
            priority = 20
    )
    public void addNewReview() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.NewButton.click();
        ibanMaker.addNewChangeRequest(
                ibanMaker.reviewRadioButton,
                "9388231021186777"
        );
        ibanMaker.dropDownList("1", "2");
        ibanMaker.searchBtn.click();
        actions.sleepBySeconds(2);
        ibanMaker.showDetailsBtn.click();
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "The Type is Review",
                driver
        );
        actions.verifyResult(
                "Not Add Review ",
                "The Add Review is successfully",
                ibanMaker.checkOnAutoStatusInsideTable(1, driver) &&
                        ibanMaker.actionTxt.getText().contains("Review")
        );
    }

    @Test(
            description = "TC0021_Verify that the 'New' button can add new request of type 'Whitelist' correctly  ",
            retryAnalyzer = Retries.class,
            priority = 21
    )
    public void addNewWhiteList() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        actions.sleepBySeconds(2);
        ibanMaker.NewButton.click();
        ibanMaker.addNewChangeRequest(
                ibanMaker.whiteListRadioButton,
                "93882310211867778"
        );
        ibanMaker.dropDownList("1", "3");
        actions.sleepBySeconds(2);
        ibanMaker.showDetailsBtn.click();
        actions.waitForExistence(
                ibanMaker.actionTxt,
                "The Type is WhiteList",
                driver
        );
        actions.verifyResult(
                "Not Add WhiteList ",
                "The Add WhiteList is successfully",
                ibanMaker.checkOnAutoStatusInsideTable(1, driver) &&
                        ibanMaker.actionTxt.getText().contains("Whitelist")
        );
    }

    //Should be review (Abdallah -> Ahmed)
    //The iban without validation
    //and some issues
    @Test(
            description = "Verify the Serach by Iban is Working Successfully",
            retryAnalyzer = Retries.class,
            priority = 22
    )
    public void SearchByIban() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.NewButton.click();
        ibanMaker.addNewChangeRequest(
                ibanMaker.whiteListRadioButton,
                "777777777777"
        );
        ibanMaker.searchByIban("777777777777");
        //    System.out.println(">>>>>>>>>>>>>>>>>>> "+ibanMaker.ibanTxt.getAttribute("value"));
        actions.waitForExistence(
                ibanMaker.requestDetailsIban,
                "The value by Iban",
                driver
        );
        actions.verifyResult(
                "The Iban Is equal inside textBox",
                ibanMaker.ibanTxt.getText(),
                ibanMaker.requestDetailsIban
                        .getText()
                        .contains(ibanMaker.ibanTxt.getText())
        );
    }

    @Test(
            description = "TC0023_Verify the cancel Button is work inside poup",
            retryAnalyzer = Retries.class,
            priority = 23
    )
    public void cancelBtn() throws InterruptedException {
        loginPage.successfulLogin(driver);
        ibanMaker.openRemittanceIbanMaker(driver);
        ibanMaker.NewButton.click();
        actions.sleepBySeconds(1);
        actions.waitForExistence(
                ibanMaker.cancelBtnPoup,
                "The cancel button Poup",
                driver
        );
        ibanMaker.cancelBtnPoup.click();
        boolean condition = actions.isElementExist(
                ibanMaker.RequestCaseDetails
        );
        actions.verifyResult(
                "The cancel button Not Work",
                "The cancel button is work",
                !condition
        );
    }


}
