package TestBase.ApiBase;

import io.restassured.response.Response;
import java.util.Map;

public class PostRequest extends ApiTestBase implements ApiRequest {

  @Override
  public Response sendRequest(Map<String, String> reqData) {
    //    RestAssured.useRelaxedHTTPSValidation();
    //    Response response = String.valueOf(RestAssured
    //      .given()
    //      .header(authorization, reqData.get("token"))
    //      .header(header1, reqData.get("header1"))
    //      .header(header2, reqData.get("header2"))
    //      .header(header3, reqData.get("header3"))
    //      .header(header4, reqData.get("header4"))
    //      .body(reqData.get("body"))
    //      .post(reqData.get("endPoint"))
    //      .then()
    //      .extract()
    //      .response());
    //
    //    return response;
    return null;
  }

  @Override
  public Response sendRequest(
    Map<String, String> reqData,
    Map<String, Object> params
  ) {
    return null;
  }
}
