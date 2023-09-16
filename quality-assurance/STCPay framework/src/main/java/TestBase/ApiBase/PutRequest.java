package TestBase.ApiBase;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class PutRequest extends ApiTestBase implements ApiRequest {

  @Override
  public Response sendRequest(Map<String, String> reqData) {
    RestAssured.useRelaxedHTTPSValidation();
    Response response = RestAssured
      .given()
      .header(authorization, reqData.get("token"))
      .header(header1, reqData.get("header1"))
      .header(header2, reqData.get("header2"))
      .header(header3, reqData.get("header3"))
      .header(header4, reqData.get("header4"))
      .body(requestBody)
      .put(endPoint);
    return response;
  }

  @Override
  public Response sendRequest(
    Map<String, String> reqData,
    Map<String, Object> params
  ) {
    return null;
  }
}
