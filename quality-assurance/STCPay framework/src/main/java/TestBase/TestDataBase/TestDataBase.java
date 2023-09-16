package TestBase.TestDataBase;

import Helpers.DateHelper;
import Helpers.ReadWriteHelper;

import java.util.Map;

public class TestDataBase {

    protected String header1, h1;
    protected String header2, h2;
    protected String header3, h3;
    protected String header4, h4;
    protected String authorization;
    protected String authEndPoint;
    protected String contentType;
    protected String acceptLanguage;
    protected Map<String, String> authBody;

    protected String accept;
    protected String referer;
    protected String authUsername;
    protected String authPassword;
    protected String empBaseUrl;
    protected String uiServer;
    protected String amlockBaseUrl;
    protected String sandBoxUrl;
    protected String serverName;
    protected long defaultWaitTime;
    protected long retries;

    protected Map testConfig;
    protected Map envConfig;
    protected boolean sendEmail;
    protected Map remittance;

    public TestDataBase() {
        testConfig =
                ReadWriteHelper.getDataFromJson("src/main/java/Configs/testConfig.json");
        envConfig = (Map) testConfig.get("envConfig");
        empBaseUrl = (String) envConfig.get("empBaseUrl");
        uiServer = (String) envConfig.get("uiServerName");
        amlockBaseUrl = (String) envConfig.get("amlockBaseUrl");
        sandBoxUrl = (String) envConfig.get("sandBoxUrl");
        serverName = (String) envConfig.get("serverName");
        defaultWaitTime = (long) envConfig.get("defaultWaitTime");
        retries = (long) envConfig.get("retries");

        //        Headers
        Map apiBaseData = ReadWriteHelper.getDataFromJson(
                "src/main/resources/Fixtures/ApiTestData/apiBaseData.json"
        );
        Map headersKeys = (Map) apiBaseData.get("headers");
        authorization = (String) headersKeys.get("auth");
        header1 = (String) headersKeys.get("header1");
        header2 = (String) headersKeys.get("header2");
        header3 = (String) headersKeys.get("header3");
        header4 = (String) headersKeys.get("header4");

        //    ACCESS TOKEN
        Map authData = ReadWriteHelper.getDataFromJson(
                "src/main/resources/Fixtures/ApiTestData/Authorization/authenticateData.json"
        );
        authEndPoint = (String) authData.get("endPoint");
        authUsername = (String) authData.get("authUsername");
        authPassword = (String) authData.get("authPassword");
        authBody = (Map<String, String>) authData.get("adminBody");
        //            ReadWriteHelper.getBodyFromJson("src/main/resources/Fixtures/ApiTestData/Authorization/payload.json");

        Map headers = (Map) authData.get("headers");
        contentType = (String) headers.get("Content-Type");
        acceptLanguage = (String) headers.get("Accept-Language");
        accept = (String) headers.get("Accept");
        referer = (String) headers.get("Referer");

        Map emailConfig = (Map) testConfig.get("emailConfig");
        sendEmail = (boolean) emailConfig.get("sendEmail");

// Employee Portal

    }
}
