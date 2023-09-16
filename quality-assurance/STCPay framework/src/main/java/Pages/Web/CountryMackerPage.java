package Pages.Web;


import Helpers.ActionsHelper;
import Helpers.DateHelper;
import TestBase.WebBase.WebPageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class CountryMackerPage extends WebPageBase {


    public CountryMackerPage(WebDriver driver) {super(driver);}

    ActionsHelper action = new ActionsHelper();
    DateHelper dateHelper = new DateHelper();

    @FindBy(xpath = "//span[normalize-space()='Compliance']")
    public WebElement compliance;

    @FindBy(xpath = "//span[contains(text(),'Remittance Country Maker')]")
    public WebElement RemittanceCountryMaker;

    @FindBy(xpath = "//select[@name='status']")
    public WebElement status;

    @FindBy(xpath = "//select[@name='country']")
    public WebElement country;

    @FindBy(xpath = "//select[@name='action']")
    public WebElement actions;

    @FindBy(xpath = "//input[@name='dateCreatedRange']]")
    public WebElement dateCreatedRange;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchButton;


    @FindBy(xpath = "(//div[@class=\"ps__rail-y\"])[1]")
    public WebElement dropDownList;

    @FindBy(xpath = "(//button[normalize-space()='New'])[1]")
    public WebElement newButton;

    @FindBy(xpath = "(//input[@id='1'])[1]")
    public WebElement selectSanction ;

    @FindBy(xpath = "(//input[@id='2'])[1]")
    public WebElement selectReview ;


    @FindBy(xpath = "(//input[@id='3'])[1]")
    public WebElement selectWhiteList ;

    @FindBy(xpath = "(//input[@id='3'])[1]")
    public WebElement ddlNew ;

    @FindBy(xpath = "(//div)[207]")
    public WebElement popup;


    @FindBy(xpath = "(//button[@class='w-100 btn btn-outline-primary'][normalize-space()='Show Details'])[1]")
    public WebElement showDetailsButton;

    @FindBy(xpath = "(//div[@class='col-12'])[2]")
    public WebElement textAction;

    @FindBy(xpath = ("(//div[@class='col-12'])[1]"))
    public WebElement textCountry;

    @FindBy(xpath = ("(//td[normalize-space()='Approved'])[1]"))
    public List<WebElement> statusList;

    @FindBy(xpath = ("(//button)[18]"))
    public WebElement addAction;

    @FindBy(xpath = ("(//select[@name='country'])[2]"))
    public WebElement selectCountry;

    @FindBy(xpath = " (//button)[3]")
    public  WebElement export ;


    @FindBy(xpath = " (//button[@type='button'])[1]")
    public  WebElement close ;

    @FindBy(xpath = "//div[@class='col-lg-12']")
    public WebElement showDetailsReqDetails;

    @FindBy (xpath = "//button[@class='btn btn-outline-danger']")
    public  WebElement cancel;

    public List<WebElement> actionList;

    public static int sizeTable;

    @FindBy(xpath = "//span[contains(text(),'Remittance Country Maker')]")
    public WebElement countryMaker;

    public void openRemittanceCountryMacker(WebDriver driver) throws InterruptedException {
        this.compliance.click();
        action.sleepBySeconds(1);
        action.scrollElementIntoView(driver, countryMaker);
        this.RemittanceCountryMaker.click();
    }

    public void ddl (WebElement ddlName, String statusCode)
    {
//        action.dropDown(ddlName, statusCode);
    }



    public void drobDownList (String statusCode , String countryCode , String actionCode)
    {
        Select ddlStatus = new Select(status);
        ddlStatus.selectByValue(statusCode);

        Select ddlCountry = new Select(country);
        ddlCountry.selectByValue(countryCode);

        Select ddlAction = new Select(actions);
        ddlAction.selectByValue(actionCode);


    }
    public void ddlsFlow(WebDriver driver, WebElement ddlName, String statusCode ) throws InterruptedException {
        openRemittanceCountryMacker(driver);
        newButton.click();
        ddl(ddlName, statusCode);
    }


    public void showDetails(WebDriver driver) throws InterruptedException {
       this.showDetailsButton.click();
    }

//    public void countryMackerSearchPending() throws InterruptedException {
//        this.compliance.click();
//        action.scrollElementIntoView(driver,RemittaneCountryMakerString );
//        this.RemittanceCountryMaker.click();
//        Select ddlStatus = new Select(status);
//
//
//
//    }

    public void countryMackerSearchApproved() throws InterruptedException {
        this.compliance.click();
        //  action.sleepBySeconds(1);
        // action.(driver,1222,263);
        this.dropDownList.click();
        this.RemittanceCountryMaker.click();

    }
    public void countryMackerSearchRejected() throws InterruptedException {
        this.compliance.click();
        //  action.sleepBySeconds(1);
        // action.(driver,1222,263);
        this.dropDownList.click();
        this.RemittanceCountryMaker.click();
        //  this.
    }

    public boolean checkOnAutoStatusInsideTable(
            int autoStatusCode,
            WebDriver driver
    ) {
//
//        String path="//div[@class='ui-table-scrollable-body']/table/tbody/tr";
//      sizeTable=action.tableSize(driver,path);
       eleList(driver);

        if (autoStatusCode == 1) {
            for (int i = 0; i < sizeTable; i++) {
                if (
                        !statusList.get(i).getText().equalsIgnoreCase("Pending")
                ) return false;
                System.out.println(statusList.get(i).getText());
            }
            return true;
        } else if (autoStatusCode == 2) {
            for (int i = 0; i < sizeTable; i++) {
                if (
                        !statusList.get(i).getText().equalsIgnoreCase("Approved")
                ) return false;
                System.out.println(statusList.get(i).getText());
            }
            return true;
        } else if (autoStatusCode == 3) {
            for (int i = 0; i < sizeTable; i++) {
                if (
                        !statusList.get(i).getText().equalsIgnoreCase("Rejected")
                ) return false;
                System.out.println(statusList.get(i).getText());
            }
            return true;
        }

        return false;
    }

    public List<WebElement> eleList(WebDriver driver) {

        String path="//div[@class='ui-table-scrollable-body']/table/tbody/tr";
        sizeTable=action.tableSize(driver,path);
        statusList = getListCountry(driver,sizeTable, 6);
        if (statusList.size()==0)
        {
            return null;
        }
        return statusList;
    }

    public List<WebElement> getListCountry(WebDriver driver, int tr, int td) {
        String path = "";

        List elList = new ArrayList<>();
        for (int i = 1; i <= tr; i++) {
            path = "//tr[" + i + "]/td[" + td + "]";
            elList.add((driver.findElement(By.xpath(path))));
        }
        return elList;
    }

    public boolean getTypeAction(String typeAction)
    {
        String actionTxt=this.textAction.getText();
        if(actionTxt.contains(typeAction))
        {
            return true;
        }else {
            return false;
        }
    }


    public boolean getNameCountry(String countryName)
    {
        String country=this.textCountry.getText();
        if(country.contains(countryName))
        {
            return true;
        }else {
            return false;
        }
    }

    public void newReqChange(WebDriver driver ,int valueOfAction ,String country) throws InterruptedException {
        int inputAction = valueOfAction;
     openRemittanceCountryMacker(driver);
        newButton.click();
        if (inputAction == 1) {
            selectSanction.click();
        } else if (inputAction == 2) {
            selectReview.click();
        } else  {selectWhiteList.click();}
        countryDDl(selectCountry,country);
        action.sleepBySeconds(2);
        addAction.click();
         showDetailsButton.click();

    }
    public void countryDDl (WebElement selectCountry,String value)
    {
        Select countryDDl = new Select(selectCountry);
        countryDDl.selectByValue(value);


    }

//    public void addNewRequest (WebElement action,String value){
//        action.click();
//        Select select = new Select(clickOnNewNationality);
//        select.selectByValue(value);
//        addButton.click();
//    }



}
