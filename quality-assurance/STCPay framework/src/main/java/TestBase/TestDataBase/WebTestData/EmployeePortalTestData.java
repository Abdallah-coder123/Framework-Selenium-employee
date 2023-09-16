package TestBase.TestDataBase.WebTestData;

import Helpers.ReadWriteHelper;
import Pages.Web.CaseListPage;
import Pages.Web.LoginPage;
import Pages.Web.IbanMakerPage;
import Pages.Web.CountryMackerPage;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Map;

public class EmployeePortalTestData {

    protected CaseListPage caseListPage;
    protected LoginPage loginPage;

    protected CountryMackerPage countryMackerPage;

    protected IbanMakerPage ibanMaker;
    protected String empBaseUrl;
    protected String uiServer;
    protected String amlockBaseUrl;
    protected String sandBoxUrl;
    protected String serverName;
    protected long defaultWaitTime;
    protected long retries;

    protected Map employeePortal;
    protected Map caseList;
    protected String caseId;
    protected Map caseType;

    protected String allType;
    protected String remittanceType;
    protected String onboardingType;
    protected String merchantOnboardingType;

    protected Map testConfig;
    protected Map envConfig;

    protected String allStatus;
    protected String naStatus;
    protected String acceptedStatus;
    protected String rejectedStatus;
    protected String suspendedStatus;
    protected String fraudStatus;
    protected String allStage;
    protected String pendingStage;
    protected String completedbyuserStage;
    protected String completedbysystemStage;

    protected Map automationStatus;
    protected Map automationStage;
    protected Map users;

    protected String adminUser;
    protected String adminPass;

    protected ArrayList mtcn;

    public EmployeePortalTestData() {


        testConfig =
                ReadWriteHelper.getDataFromJson("src/main/java/Configs/testConfig.json");
        envConfig = (Map) testConfig.get("envConfig");
        empBaseUrl = (String) envConfig.get("empBaseUrl");
        uiServer = (String) envConfig.get("uiServerName");
        amlockBaseUrl = (String) envConfig.get("amlockBaseUrl");
        sandBoxUrl = (String) envConfig.get("sandBoxUrl");
        serverName = (String) envConfig.get("serverName");
        defaultWaitTime = (long) envConfig.get("defaultWaitTime");
//    retries = (long) envConfig.get("retries");

        employeePortal =
                ReadWriteHelper.getDataFromJson(
                        "src/main/resources/Fixtures/WebData/EmployeePortal.json"
                );
        users = (Map) employeePortal.get("users");
        adminUser = (String) users.get("adminUser");
        adminPass = (String) users.get(("adminPassword"));
        caseList = (Map) employeePortal.get("caseList");
        caseId = (String) caseList.get("caseId");
        caseType = (Map) caseList.get("caseType");
        allType = (String) caseList.get("0");
        remittanceType = (String) caseList.get("1");
        onboardingType = (String) caseList.get("2");
        merchantOnboardingType = (String) caseList.get("3");
        automationStatus = (Map) caseList.get("automationStatus");
        allStatus = (String) automationStatus.get("0");
        naStatus = (String) automationStatus.get("1");
        acceptedStatus = (String) automationStatus.get("2");
        rejectedStatus = (String) automationStatus.get("3");
        suspendedStatus = (String) automationStatus.get("4");
        fraudStatus = (String) automationStatus.get("5");
        automationStage = (Map) caseList.get("automationStage");
        allStage = (String) automationStage.get("0");
        pendingStage = (String) automationStage.get("1");
        completedbyuserStage = (String) automationStage.get("2");
        completedbysystemStage = (String) automationStage.get("3");
        mtcn = (ArrayList) caseList.get("mtcn");
    }

    protected void pageInstantiation(WebDriver driver) {
        caseListPage = new CaseListPage(driver);
        loginPage = new LoginPage(driver);
        ibanMaker = new IbanMakerPage(driver);
        countryMackerPage = new CountryMackerPage(driver);
    }
}
