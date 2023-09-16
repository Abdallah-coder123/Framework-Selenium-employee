package WebTest;

import static io.restassured.RestAssured.given;

import Helpers.ActionsHelper;
import Pages.Web.HomePage;
import TestBase.WebBase.WebTestBase;
import Utils.DataUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

//@Listeners(WebTestListener.class)
public class STCPayTest extends WebTestBase {

  ActionsHelper action = new ActionsHelper();

  @Test(
    description = "TC001 Positive scenario",
    dataProvider = "yamlData",
    dataProviderClass = DataUtils.class,
    priority = 1
  )
  public void positiveScenario(HashMap<String, String> hashMap) {
    String baseurl = hashMap.get("baseUrl");
    String password = hashMap.get("password");
    System.out.println(password);
    HomePage home = new HomePage(driver);
    home.navigateToUrl(baseurl);

    //        Assertion
    String expectedResult = "One app, takes you further";
    String actualResult = "Does not exist";
    action.verifyResult(
      actualResult,
      expectedResult,
      home.header.isDisplayed()
    );

    action.waitForExistence(home.personal, "Personal Tab");
    home.personal.click();
  }

  //    @Test(description = "TC002 Negative scenario", priority = 2)
  //    public void negativeScenario() throws IOException, ParseException {
  //        HomePage home = new HomePage(driver);
  ////        home.navigateToUrl();
  //
  ////        Assertion
  //        String expectedResult = "One app, takes you further";
  //        String actualResult = "Does not exist";
  //        if (!home.header.isDisplayed()) {
  //            actualResult = expectedResult;
  //        }
  //        Assert.assertEquals(actualResult, expectedResult);
  //    }
  //
  //    @Test(description = "TC003 Get Offers status code", priority = 3)
  //    public void checkAPI() throws IOException, ParseException {
  //        Offers offers = new Offers(driver);
  //        offers.getOffersAPI().assertThat().statusCode(200);
  //        offers.getOffersAPI().assertThat().statusLine("HTTP/1.1 200 OK");
  //    }
  //
  @Test(
    description = "mock server",
    dataProvider = "data",
    dataProviderClass = DataUtils.class,
    priority = 4
  )
  public void mock(HashMap<String, String> hashMap) {
    String baseUri = hashMap.get("baseUri");
    String apiKey = hashMap.get("apiKey");
    RestAssured.baseURI = baseUri;
    RequestSpecification httpRequest = given().header("x-api-key", apiKey);
    Response res = httpRequest.request(Method.GET, "/api/stcpay/home");
    ResponseBody body = res.getBody();
    JSONObject obj = new JSONObject(body.asString());

    if (obj.has("url")) {
      System.out.println("status code: " + res.getStatusCode());
      System.out.println("body: " + body.asString());
      String url = obj.getString("url");
      String actualUrl = "Wrong URL";
      String expectedUrl = url + "offers/";
      int password = obj.getInt("password");
      if (password == 123456) {
        actualUrl = expectedUrl;
      } else {
        System.out.println("Not Equal");
      }
      Assert.assertEquals(actualUrl, expectedUrl);
    } else {
      System.out.println("Not Found");
    }
  }

  @Test(
    description = "Post req",
    dataProvider = "data",
    dataProviderClass = DataUtils.class,
    priority = 5
  )
  public void postTest(HashMap<String, String> hashMap) {
    //        TEST DATA
    String firstName = hashMap.get("firstName");
    String lastName = hashMap.get("lastName");
    String email = hashMap.get("email");
    String password = hashMap.get("password");
    String baseUri = hashMap.get("postUri");

    JSONObject reqParams = new JSONObject();
    reqParams.put("firstName", firstName);
    reqParams.put("lastName", lastName);
    reqParams.put("email", email);
    reqParams.put("password", password);

    Response response = given()
      .baseUri(baseUri)
      .header("Content-Type", "application/json")
      //Desrilizetion convert from java Object to Json Object
      .body(reqParams.toString())
      .log()
      .all()
      //When
      .when()
      .post("/api/v1/users/register")
      //Then
      .then()
      .log()
      .all()
      .extract()
      .response();
  }

  @Test(
    description = "Post req",
    dataProvider = "yamlData",
    dataProviderClass = DataUtils.class,
    priority = 5
  )
  public void test(HashMap<String, String> hashMap) {
    String credentials = hashMap.get("Credentials");
    String password = hashMap.get("password");
    System.out.println(credentials);
    System.out.println("test :==== " + password);
  }

  @Test(
    description = "test1",
    dataProvider = "lastPayment",
    dataProviderClass = DataUtils.class,
    priority = 1
  )
  public void setLastPaymentData(HashMap<String, String> hashMap) {
    //        ReadWriteHelper.jsonData(filePath);

    String endPoint = hashMap.get("endPoint");
    //        String headers = hashMap.get("headers");
    //        String body = hashMap.get("body");

    System.out.println("endPoint : " + endPoint);
    //        System.out.println("headers : "+ headers);
    //        System.out.println("body : "+ body);

  }
}
