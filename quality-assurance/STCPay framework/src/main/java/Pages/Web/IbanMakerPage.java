package Pages.Web;

import Helpers.ActionsHelper;
import Helpers.DateHelper;
import TestBase.WebBase.WebPageBase;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class IbanMakerPage extends WebPageBase {

  public IbanMakerPage(WebDriver driver) {
    super(driver);
  }

  ActionsHelper actions = new ActionsHelper();
  DateHelper dateHelper = new DateHelper();

  @FindBy(xpath = "//span[normalize-space()='Compliance']")
  public WebElement compliance;

  @FindBy(xpath = "// select[@name='status']")
  public WebElement selectStatus;

  @FindBy(xpath = "// input[@name='iban']")
  public WebElement ibanTxt;

  @FindBy(xpath = "// select[@name='action']")
  public WebElement selectAction;

  @FindBy(xpath = "//button[normalize-space()='Search']")
  public WebElement searchBtn;

  @FindBy(xpath = "//span[normalize-space()='Remittance IBAN Maker']")
  public WebElement remittanceIbanMakerBtn;

  @FindBy(xpath = "//button[@class='w-100 btn btn-outline-primary']")
  public WebElement showDetailsBtn;

  @FindBy(xpath = "(//div[@class='col-12'])[2]")
  public WebElement actionTxt;

  @FindBy(xpath = "//div[@class='ui-table-scrollable-body']")
  public WebElement rowsTable;

  @FindBy(xpath = "(//div[@class='m-portlet__body'])[2]")
  public WebElement requestDetails;

  @FindBy(xpath = "//tbody/tr[1]/td[6]")
  public WebElement statusCell;

  @FindBy(xpath = "//button[normalize-space()='New']")
  public WebElement NewButton;

  @FindBy(xpath = "button[@class='btn btn-outline-danger']")
  public WebElement CancelButton;

  @FindBy(xpath = "//button[@class='btn btn-secondary']")
  public WebElement closeButton;

  @FindBy(xpath = "(//div[@class='m-portlet__body'])[2]")
  public WebElement RequestCaseDetails;

  @FindBy(xpath = "(//div[@class='modal-body'])[4]")
  public WebElement poup;

  @FindBy(xpath = "(//input[@name='action'])[1]")
  public WebElement sanctionRadioButton;

  @FindBy(xpath = "(//input[@name='action'])[2]")
  public WebElement reviewRadioButton;

  @FindBy(xpath = "(//input[@name='action'])[3]")
  public WebElement whiteListRadioButton;

  @FindBy(xpath = "(//div[@class='col-12'])[1]")
  public WebElement requestDetailsIban;

  @FindBy(xpath = "//div[@class='col-xl-6']//input[@type='text']")
  public WebElement ibanPoup;

  @FindBy(xpath = "//button[normalize-space()='Add']")
  public WebElement addBtn;

  @FindBy(xpath = "(//div[@class='col-xl-12'])[2]")
  public List<WebElement> radioBtnList;

  @FindBy(xpath = "//button[@class='btn btn-outline-danger']")
  public WebElement cancelBtnPoup;

  @FindBy(xpath = "//button[normalize-space()='Export to excel']")
  public WebElement exportExcel;

  public List<WebElement> statusList;

  public int sizeTable;

  @FindBy(xpath = "//span[normalize-space()='Remittance IBAN Maker']")
  public WebElement ibanString;


  public void openRemittanceIbanMaker(WebDriver driver)
    throws InterruptedException {
    this.compliance.click();
    actions.sleepBySeconds(1);
    actions.scrollElementIntoView(driver, ibanString);
    this.remittanceIbanMakerBtn.click();
  }

  public List<WebElement> getListRemittanceIbanMaker(
    WebDriver driver,
    int tr,
    int td
  ) {
    String path = "";

    List elList = new ArrayList<>();
    for (int i = 1; i <= tr; i++) {
      path = "//tr[" + i + "]/td[" + td + "]";
      elList.add((driver.findElement(By.xpath(path))));
    }
    return elList;
  }

  public void dropDownList(String statusValue, String actionValue) {
    Select StatusDDL = new Select(this.selectStatus);
    StatusDDL.selectByValue(statusValue);
    Select ActionDDL = new Select(this.selectAction);
    ActionDDL.selectByValue(actionValue);
    searchBtn.click();
  }

  public List<WebElement> eleList(WebDriver driver) {
    String tablePath =
      "//div[@class='ui-table-scrollable-body']/table/tbody/tr";
    sizeTable = actions.tableSize(driver, tablePath);
    statusList = getListRemittanceIbanMaker(driver, sizeTable, 6);
    return statusList;
  }

  public boolean checkOnAutoStatusInsideTable(
    int autoStatusCode,
    WebDriver driver
  ) {
    //    String tablePath =
    //      "//div[@class='ui-table-scrollable-body']/table/tbody/tr";
    //    sizeTable = actions.tableSize(driver, tablePath);
    eleList(driver);
    if (autoStatusCode == 1) {
      for (int i = 0; i < sizeTable; i++) {
        if (
          !statusList.get(i).getText().equalsIgnoreCase("Pending")
        ) return false;
      }
      return true;
    } else if (autoStatusCode == 2) {
      for (int i = 0; i < sizeTable; i++) {
        if (
          !statusList.get(i).getText().equalsIgnoreCase("Approved")
        ) return false;
      }
      return true;
    } else if (autoStatusCode == 3) {
      for (int i = 0; i < sizeTable; i++) {
        if (
          !statusList.get(i).getText().equalsIgnoreCase("Rejected")
        ) return false;
      }
      return true;
    }

    return false;
  }

  public boolean getTypeAction(String typeAction) {
    String actionTxt = this.actionTxt.getText();
    if (actionTxt.contains(typeAction)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean verifyIsFiveRow() {
    List<WebElement> rows = rowsTable.findElements(By.tagName("tr"));
    if (rows.size() == 5) return true; else return false;
  }

  public void addNewChangeRequest(WebElement typeAction, String Iban) {
    typeAction.click();
    ibanPoup.sendKeys(Iban);
    actions.sleepBySeconds(1);
    addBtn.click();
  }

  public void showTypeAction(WebDriver driver) {
    showDetailsBtn.click();
    actions.waitForExistence(actionTxt, "Type of action", driver);
    actions.scrollPage(driver, 400, 400);
  }

  public String generateIban() {
    long first14 = (long) (Math.random() * 100000000000000L);
    long number = 93882310211867778L + first14;
    return String.valueOf(number);
  }

  public void searchByIban(String IbanNum)
  {
    ibanTxt.sendKeys(IbanNum);
    dropDownList("-1", "-1");
    actions.sleepBySeconds(2);
    showDetailsBtn.click();
  }
}
