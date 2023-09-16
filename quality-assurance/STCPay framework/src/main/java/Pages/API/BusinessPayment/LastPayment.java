package Pages.API.BusinessPayment;

import Helpers.ReadWriteHelper;
import TestBase.WebBase.WebTestBase;
import Utils.DataUtils;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class LastPayment extends WebTestBase {

  String filePath = "src/main/resources/Fixtures/ApiTestData/lastPayment.json";

  @Test(
    description = "test1",
    dataProvider = "lastPayment",
    dataProviderClass = DataUtils.class
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

  @Test
  public void test() throws ParseException {
    HashMap lastPaymentData = ReadWriteHelper.jsonData(filePath);
    String endPoint = (String) lastPaymentData.get("endPoint");

    System.out.println(endPoint);

    JSONParser parser = new JSONParser();
    JSONObject headersData = (JSONObject) parser.parse(
      String.valueOf(lastPaymentData.get("headers"))
    );
    JSONObject body = (JSONObject) parser.parse(
      String.valueOf(lastPaymentData.get("body"))
    );
    String contentType = (String) headersData.get("Content-Type");
    String merchantID = (String) body.get("MerchantId");

    System.out.println(contentType);
    System.out.println(merchantID);
  }
}
