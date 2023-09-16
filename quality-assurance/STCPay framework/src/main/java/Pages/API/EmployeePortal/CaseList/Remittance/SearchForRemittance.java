package Pages.API.EmployeePortal.CaseList.Remittance;

import Helpers.ActionsHelper;
import Helpers.ReadWriteHelper;
import Pages.API.Authorization.Authenticate;
import TestBase.ApiBase.ApiTestBase;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

public class SearchForRemittance extends ApiTestBase {

  String searchEndpoint, DetailsEndpoint;
  Map searchData, reqData;
  Authenticate auth = new Authenticate();
  ActionsHelper actions = new ActionsHelper();

  public void setSearchForRemittance() throws IOException {
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    token = actions.getJSONString(resBody, "result.accessToken");
    String jsonPath =
      "src/main/resources/Fixtures/ApiTestData/CaseList/Search/SearchForRemittance/remittanceData.json";
    searchData = ReadWriteHelper.getDataFromJson(jsonPath);
    searchEndpoint = (String) searchData.get("endPoint");
    Map headers = (Map) searchData.get("headers");
    contentType = (String) headers.get("Content-Type");
    accept = (String) headers.get("Accept");
    acceptLanguage = (String) headers.get("Accept-Language");
    referer = (String) headers.get("Referer");

    reqData = new HashMap<>();
    reqData.put("endpoint", searchEndpoint);
    reqData.put("token", "Bearer " + token);
    reqData.put("header1", contentType);
    reqData.put("header2", accept);
    reqData.put("header3", acceptLanguage);
    reqData.put("header4", empBaseUrl + referer);
  }

  @Test(description = "search test")
  public void searchForRemittance() throws IOException {
    setSearchForRemittance();
    String res = sendRequest("GET", this.reqData);
    int caseId = actions.getJSONInt(res, "result.items[0].id");
    System.out.println("case ID : " + caseId);
  }
}
