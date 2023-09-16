package ApiTest.AuthenticateTest;

import Helpers.ActionsHelper;
import Pages.API.Authorization.Authenticate;
import TestBase.ApiBase.ApiTestBase;
import io.restassured.response.Response;
import java.io.IOException;
import org.testng.annotations.Test;

//@Listeners({ WebTestListener.class })
public class PositiveGenerateToken extends ApiTestBase {

  @Test(
    description = "AUTH001 Generate token for Admin User successfully",
    priority = 1
  )
  public void adminUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "<b><font color=green>200</b></font>" + res.asPrettyString(),
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "User ID should be 40118",
      userId.equals("40118")
    );
  }

  @Test(
    description = "AUTH002 Generate token for Cap Checker user successfully",
    priority = 2
  )
  public void capCheckerUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "CAPCHECKERTEST");
    authBody.put(authPassword, "CAPCHECKERTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40119</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH003 Generate token for Cap Maker user successfully",
    priority = 3
  )
  public void CapMakerUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "CAPMAKERTEST");
    authBody.put(authPassword, "CAPMAKERTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40120</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH004 Generate token for Compliance Manager User successfully",
    priority = 4
  )
  public void CompManagerUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "COMPMANAGERTEST");
    authBody.put(authPassword, "COMPMANAGERTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40121</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH005 Generate token for Director of Compliance User successfully",
    priority = 5
  )
  public void DircCompUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "DIRCCOMPTEST");
    authBody.put(authPassword, "DIRCCOMPTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40122</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH006 Generate token for FX Checker User successfully",
    priority = 6
  )
  public void FXCheckerUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "FXCHECKERTEST");
    authBody.put(authPassword, "FXCHECKERTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40123</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH007 Generate token for FX Maker User successfully",
    priority = 7
  )
  public void FXMakerUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "FXMAKERTEST");
    authBody.put(authPassword, "FXMAKERTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40124</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH008 Generate token for KAKO User successfully",
    priority = 8
  )
  public void KAKOUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "KAKOTEST");
    authBody.put(authPassword, "KAKOTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40125</b></font>",
      res.statusCode() == 200
    );
  }

  @Test(
    description = "AUTH009 Generate token for SALESMM User successfully",
    priority = 9
  )
  public void SALESMMUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authUsername, "SALESMMTEST");
    authBody.put(authPassword, "SALESMMTEST@@##");
    Response res = auth.accessToken(authBody);

    action.verifyResult(
      res.statusCode() + res.asPrettyString(),
      "Success <b><font color=green>200</b></font>",
      res.statusCode() == 200
    );

    String resBody = res.prettyPrint();
    token = action.getJSONString(resBody, "result.accessToken");
    String userId = Integer.toString(
      action.getJSONInt(resBody, "result.userId")
    );
    action.verifyResult(
      userId,
      "<b><font color=green>40126</b></font>",
      res.statusCode() == 200
    );
  }
}
