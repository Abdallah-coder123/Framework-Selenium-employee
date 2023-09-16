package TestBase.ApiBase;

import Helpers.EmailHelper;
import Reporting.APIReport.ExtentManager;
import TestBase.TestDataBase.TestDataBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.io.IOException;
import java.util.Map;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class ApiTestBase extends TestDataBase {

  protected String baseUrl;

  protected String token;
  protected String message, details;
  protected String requestBody;
  protected String response;
  protected String endPoint;

  @BeforeClass
  public void envSetup() {
    if (serverName.equalsIgnoreCase("Portal.API")) {
      baseUrl = empBaseUrl + serverName + "/";
    } else if (serverName.equalsIgnoreCase("sandBox")) {
      baseUrl = sandBoxUrl;
    } else {
      baseUrl = amlockBaseUrl;
    }
    RestAssured.baseURI = baseUrl;
  }

  //    public ApiTestBase() {
  //        Map apiBaseData = ReadWriteHelper.getDataFromJson(
  //                "src/main/resources/Fixtures/ApiTestData/apiBaseData.json"
  //        );
  //        Map headers = (Map) apiBaseData.get("headers");
  //        authorization = (String) headers.get("auth");
  //        header1 = (String) headers.get("header1");
  //        header2 = (String) headers.get("header2");
  //        header3 = (String) headers.get("header3");
  //        header4 = (String) headers.get("header4");
  //    }

  public void accessToken() throws IOException {
    //    Access Token
    RestAssured.useRelaxedHTTPSValidation();
    String request = RestAssured
      .given()
      .header(header1, contentType)
      .header(header2, accept)
      .header(header3, acceptLanguage)
      .header(header4, referer)
      .body(authBody)
      .post(authEndPoint)
      .getBody()
      .prettyPrint();

    token = JsonPath.from(request).get("result.accessToken");
  }

  public String sendRequest(String methodVerb, Map<String, String> reqData) {
    ApiRequest apiRequest = null;
    if (methodVerb.equalsIgnoreCase("POST")) {
      apiRequest = new PostRequest();
    } else if (methodVerb.equalsIgnoreCase("GET")) {
      apiRequest = new GetRequest();
    } else if (methodVerb.equalsIgnoreCase("PUT")) {
      apiRequest = new PutRequest();
    } else if (methodVerb.equalsIgnoreCase("DEL")) {
      apiRequest = new DelRequest();
    }
    String response = apiRequest.sendRequest(reqData).prettyPrint();

    return response;
  }

  @AfterSuite
  public void sendEmail() throws ParseException {
    EmailHelper email = new EmailHelper();
    String reportPath = ExtentManager.path + ExtentManager.reportFileName;
    if (sendEmail) {
      email.sendEmail(reportPath, "API Platform");
    }
  }
}
