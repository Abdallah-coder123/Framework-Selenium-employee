package Pages.Web;
import Helpers.ActionsHelper;
import Helpers.DateHelper;
import TestBase.ApiBase.PutRequest;
import TestBase.WebBase.WebPageBase;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
public class CaseListPage extends WebPageBase {
    ActionsHelper actions = new ActionsHelper();
    DateHelper dateHelper = new DateHelper();
    public CaseListPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[normalize-space()='Compliance']")
    public WebElement compliance;
    @FindBy(xpath = "//span[text()=' Case List ' ][1]")
    public WebElement caseList;
    @FindBy(xpath = "//input[@name='remittanceCaseId']")
    public WebElement caseId;
    @FindBy(xpath = "//tr/td[3]/span[@class=\"ui-cell-data ng-star-inserted\"]")
    public WebElement tableCaseID;
    @FindBy(
            xpath = "//h3[contains(@class, 'm-subheader__title m-subheader__title--separator')]"
    )
    public WebElement caseListHeader;
    @FindBy(xpath = "//input[@name='customerId']")
    public WebElement custId;
    @FindBy(xpath = "//label[contains(text(),'Cust ID')]")
    public WebElement labelCustId;
    @FindBy(xpath = "//select[@name='caseStatusId']")
    public WebElement automationStatus;
    @FindBy(xpath = "//input[@name='custName']")
    public WebElement senderNameEng;
    @FindBy(xpath = "//select[@name='caseStageId']")
    public WebElement AutomationStage;
    @FindBy(xpath = "//input[@name='dateCreatedRange']")
    public WebElement date;
    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchButton;
    @FindBy(xpath = "//input[@name='mtcn']")
    public WebElement mtcnField;
    @FindBy(xpath = "//select[@name='caseTypeId']")
    public WebElement caseType;
    @FindBy(xpath = "//table[@xpath=1]")
    public WebElement table;
    @FindBy(xpath = "//tbody[@class = 'ui-datatable-data ui-widget-content']/tr")
    public WebElement rowsNumber;
    @FindBy(xpath = "//input[@id = \"dateCreatedRange\"]")
    public WebElement datePickerField;
    @FindBy(xpath = "//a[@class=\"ui-paginator-next ui-paginator-element ui-state-default ui-corner-all\"]")
    public WebElement nextButton;
    @FindBy(xpath = "//a[contains(@class, \"ui-state-default ui-corner-all ng-star-inserted\")]")
    public List<WebElement> pageNumBtn;
    @FindBy(xpath = "(//button[@href='#'][normalize-space()='Case Details'])[2]")
    public WebElement caseDetail;
    @FindBy(xpath = "//textarea[@name='proposedCaseComments']")
    public WebElement comments;
    @FindBy(xpath = "//button[@class=\"btn btn-danger\"]")
    public WebElement rejectBtn;
    @FindBy(xpath = "//button[@class=\"btn btn-warning\"]")
    public WebElement suspendBtn;
    @FindBy(xpath = "//button[normalize-space()='Refresh']")
    public WebElement refresh;
    @FindBy(xpath = "//i[@class='fa fa-refresh']/ancestor::button[1]")
    public WebElement close;
    @FindBy(xpath = "//span[@class='total-records-count'][1]")
    public WebElement total;
    @FindBy(xpath = "//div[@aria-labelledby=\"swal2-title\"]")
    public WebElement errorPopUp;
    @FindBy(xpath = "//div[@class=\"swal2-content\"]")
    public WebElement popUpText;
    @FindBy(xpath = "//textarea[@name='proposedCaseComments']")
    public WebElement automationComments;
    @FindBy(xpath = "(//div[@class='btn-group ng-tns-c14-5 ng-star-inserted'])[1]")
    public WebElement caseDetails;
    @FindBy(xpath = "//button[@class='btn btn-success']")
    public WebElement acceptButton;
    @FindBy(xpath = "//tbody[@class='ui-datatable-data ui-widget-content']//span")
    public WebElement textRow;
    public int closeSize;
    @FindBy(xpath = "(//a[@class='ui-paginator-page ui-paginator-element ui-state-default ui-corner-all ng-star-inserted'])")
    public WebElement pageButtons;
    @FindBy(xpath = "//tr[1]/td[12]/span")
    public WebElement dateField;
    @FindBy(xpath = "//button[@class=\"previous\"][1]")
    public WebElement previousYear;
    @FindBy(xpath = "//div/button[text()='Yes']")
    public WebElement yesBtn;
    @FindBy(xpath = "//div/button[text()='Cancel'][@class=\"swal2-cancel swal2-styled\"]")
    public WebElement cancelBtn;
    public List<WebElement> typeList;
    public List<WebElement> autoStatusList;
    public List<WebElement> dateRange;
    public List<WebElement> autoStageList;
    public List<WebElement> rows;
    public List<WebElement> dateList;
    public String tablePath = "//div[@class='ui-datatable-scrollable-body']/div/table/tbody/tr";
    public void openCaseList(WebDriver driver) throws InterruptedException {
        compliance.click();
        actions.waitForExistence(caseList, "Case List", driver);
        caseList.click();
    }
    public void dropDownList(
            WebElement caseType,
            String caseTypeValue,
            WebElement automationStatusType,
            String automationStatus,
            WebElement automationStageType,
            String automationStage
    ) {
        Select select = new Select(caseType);
        select.selectByValue(caseTypeValue);
        Select selectStatus = new Select(automationStatusType);
        selectStatus.selectByValue(automationStatus);
        Select SelectStage = new Select(automationStageType);
        SelectStage.selectByValue(automationStage);
    }
    public List<WebElement> getTypeList(WebDriver driver) {
        typeList = actions.getElList(driver, 5, 4);
        return typeList;
    }
    public List<WebElement> getAutoStatus(WebDriver driver) {
        autoStatusList = actions.getElList(driver, 5, 5);
        return autoStatusList;
    }
    public List<WebElement> getAutoStage(WebDriver driver) {
        autoStageList = actions.getElList(driver, 5, 6);
        return autoStageList;
    }
    public List<WebElement> dateRangePick(WebDriver driver) {
        Random rand = new Random();
        int upperbound = dateHelper.currentDay();
        int rndStartDate = rand.nextInt(upperbound - 1) + 1;
        int rndEndDate = rand.nextInt(27);
        String startPath =
                "//bs-days-calendar-view[1]//span[@bsdatepickerdaydecorator][text() = '" +
                        rndStartDate +
                        "']";
        String endPath =
                "//bs-days-calendar-view[2]//span[@bsdatepickerdaydecorator][text() = '" +
                        rndEndDate +
                        "']";
        dateRange = new ArrayList<>();
        dateRange.add(driver.findElement(By.xpath(startPath)));
        dateRange.add(driver.findElement(By.xpath(endPath)));
        return dateRange;
    }
    public String getCaseId() {
        actions.sleepBySeconds(2);
        return tableCaseID.getText();
    }
    public List<LocalDate> getDateList(WebDriver driver) throws ParseException {
        int tableSize = actions.tableSize(driver, tablePath);
        List<LocalDate> list = actions.getDateList(driver, tableSize, 12);
        return list;
    }
    public List<WebElement> verifyDate(WebDriver driver) {
        int tableSize = actions.tableSize(driver, tablePath);
        dateList = actions.getElList(driver, tableSize, 12);
        return dateList;
    }
    public void fillMtcn(WebDriver driver, String mtcnText) throws InterruptedException {
        openCaseList(driver);
        actions.waitForExistence(mtcnField, "MTCN Field", driver);
        mtcnField.sendKeys(mtcnText);
        this.searchButton.click();
    }
    public void senderName(WebDriver driver, String senderNameEng) throws InterruptedException {
        this.openCaseList(driver);
        this.senderNameEng.click();
        actions.sleepBySeconds(2);
        this.senderNameEng.sendKeys(senderNameEng);
        actions.sleepBySeconds(2);
        //    this.searchButton.click();
    }
    public void caseId(WebDriver driver, String id) throws InterruptedException {
        openCaseList(driver);
        caseId.sendKeys(id);
        searchButton.click();
    }
    public void verify5rows(WebDriver driver) throws InterruptedException {
        this.openCaseList(driver);
        this.caseId.click();
        actions.sleepBySeconds(2);
        this.caseId.sendKeys("#@##$%");
        actions.sleepBySeconds(2);
        this.searchButton.click();
    }
    public void search(
            WebDriver driver,
            String caseTypeCode,
            String autoStatusCode,
            String autoStageCode,
            boolean datePick
    ) throws InterruptedException {
        openCaseList(driver);
        dropDownList(
                this.caseType,
                caseTypeCode,
                this.automationStatus,
                autoStatusCode,
                this.AutomationStage,
                autoStageCode
        );
        if (datePick == true) {
            String tot = total.getText();
            while (tot.equalsIgnoreCase("Total: 0")) {
                try {
                    this.datePickerField.click();
                    this.dateRangePick(driver).get(0).click();
                    this.dateRangePick(driver).get(1).click();
                    this.searchButton.click();
                    tot = total.getText();
                } catch (Exception e) {
                }
            }
        } else {
            this.searchButton.click();
        }
        actions.verifyResult(
                "There is No data in Table",
                "The table has valid data",
                textRow.getText() != "No data"
        );
    }
    //    public List<WebElement> verifyTheSystemDisplayTableFiveRow(WebDriver driver) {
//        int tableSize = actions.tableSize(driver, tablePath);
//        rows = actions.getElList(driver, tableSize, 1);
////        System.out.println("Number of rows:: " + rows.size());
//        return rows;
//
//    }
    public WebElement clickOnPageNum(WebDriver driver, int pageNum) {
//      WebElement pagebtn = pageButtons.get(pageNum);
        List<WebElement> pageOfButtons = driver.findElements(By.xpath("(//a[@class='ui-paginator-page ui-paginator-element ui-state-default ui-corner-all ng-star-inserted'])"));
        // pagebtn.click();
        WebElement pagebtn = pageOfButtons.get(pageNum);
        pagebtn.click();
        System.out.println(">>>>>>>>>>>>>>>> " + pagebtn.getClass());
        return pagebtn;
    }
    public void NextButtonClickable(WebDriver driver) throws InterruptedException {
        openCaseList(driver);
        searchButton.click();
        actions.waitForTableExistence(getAutoStage(driver), "Search Table",
                driver, tablePath);
        nextButton.click();
        actions.waitForLoad("/services/app/Compliance", 200, driver);
    }
    public void userAction(WebDriver driver, String action, String id) throws InterruptedException {
        actions.waitForExistence(caseDetail, "Case Detail button", driver);
        caseDetail.click();
        actions.waitForExistence(comments, "Comments Field", driver);
        comments.sendKeys(action);
        if(action == "Reject") {
            actions.waitForExistence(rejectBtn, "Reject button", driver);
            rejectBtn.click();
        } else if (action == "Accept") {
            actions.waitForExistence(acceptButton, "Accept button", driver);
            this.acceptButton.click();
        } else if (action == "Suspend") {
            actions.waitForExistence(suspendBtn, "Suspend button", driver);
            this.suspendBtn.click();
        }
        actions.waitForExistence(popUpText, "PopUp Text", driver);
        actions.verifyResult("Text does not contain " + id,
                "Text contains " + id,
                popUpText.getText().contains(id)
        );
        yesBtn.click();
    }
    public void pageRefresh(WebDriver driver)
            throws InterruptedException {
        search(driver, "-1", "-1", "-1", false);
        actions.waitForExistence(caseDetail, "Case Detail button", driver);
        caseDetail.click();
        actions.waitForExistence(refresh, "Refresh Button", driver);
        refresh.click();
    }
    public void closeCaseDetails(WebDriver driver)
            throws InterruptedException {
        search(driver, "-1", "-1", "-1", false);
        actions.waitForExistence(caseDetail, "Case Details Button", driver);
        caseDetail.click();
        actions.waitForExistence(close, "Close Button", driver);
        close.click();
    }
}