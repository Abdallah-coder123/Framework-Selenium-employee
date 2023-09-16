package Pages.API.Authorization;

import TestBase.ApiBase.ApiTestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.io.IOException;
import java.util.Map;

public class Authenticate extends ApiTestBase {

  public Response accessToken(Map<String, String> body) throws IOException {
    authBody = body;
    RestAssured.useRelaxedHTTPSValidation();
    return (Response) RestAssured
      .given()
      .header(header1, contentType)
      .header(header2, accept)
      .header(header3, acceptLanguage)
      .header(header4, referer)
      .body(authBody)
      .post(authEndPoint)
      .getBody();
  }
}
