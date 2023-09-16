package TestBase.ApiBase;

import io.restassured.response.Response;
import java.util.Map;

public interface ApiRequest {
  public Response sendRequest(Map<String, String> reqData);

  public Response sendRequest(
    Map<String, String> reqData,
    Map<String, Object> params
  );
}
