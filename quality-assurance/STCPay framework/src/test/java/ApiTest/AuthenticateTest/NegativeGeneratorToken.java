package ApiTest.AuthenticateTest;

import Helpers.ActionsHelper;
import Pages.API.Authorization.Authenticate;
import Reporting.APIReport.WebTestListener;
import TestBase.ApiBase.ApiTestBase;
import io.restassured.response.Response;
import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ WebTestListener.class })
public class NegativeGeneratorToken extends ApiTestBase {

  @Test(
    description = "AUTH001 Generate token for Admin User successfully",
    priority = 1
  )
  public void adminUser() throws IOException {
    Authenticate auth = new Authenticate();
    ActionsHelper action = new ActionsHelper();
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
      res.statusCode() == 200
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
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
    authBody.put(authPassword, "nnn@@##");
    Response res = auth.accessToken(authBody);
    String resBody = res.prettyPrint();
    String success = String.valueOf(action.getJSONBoolean(resBody, "success"));
    action.verifyResult(
      "success : " + success,
      "success : " + String.valueOf(true),
      res.statusCode() == 200
    );
  }
}
