package Utils;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.yaml.snakeyaml.Yaml;

public class DataUtils {

  protected static Logger log = LogManager.getLogger(DataUtils.class);

  @DataProvider
  public Object[][] yamlData() {
    return readYAML("src/main/resources/Fixtures/NewData.yaml");
  }

  private Object[][] readYAML(String file) {
    //        Input-stream to read Data
    InputStream inputStream = null;

    try {
      inputStream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    //        Create YAML instance to read yaml file
    Yaml yaml = new Yaml();
    Map<String, Object> data = yaml.load(inputStream);
    Object[][] testData;
    if (data != null) {
      //        Java array to store data
      testData = new Object[1][1];
    } else {
      log.error("Error retrieving YAML data");
      throw new RuntimeException();
    }
    testData[0][0] = data;
    return testData;
  }

  @DataProvider
  public static Object[][] jsonData() {
    String path = "src/main/resources/Fixtures/Data.json";
    JSONParser parser = new JSONParser();
    JSONObject jsonObj = null;

    //        Read JSON file
    try {
      Object json = parser.parse(new FileReader(path));
      jsonObj = (JSONObject) json;
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    //        Array to store JSON data
    Object[][] data = new Object[1][1];

    // Store JSON data as key/value pairs in a hashMap
    HashMap<String, String> hashMap = new LinkedHashMap<>();
    if (jsonObj != null) {
      Set<String> jsonObjKeys = jsonObj.keySet();
      for (String jsonObjKey : jsonObjKeys) {
        hashMap.put(jsonObjKey, (String) jsonObj.get(jsonObjKey));
      }
    } else {
      log.error("Error retrieving JSON data");
      throw new RuntimeException();
    }

    //        Store HashMap in an array and return array
    data[0][0] = hashMap;
    return data;
  }

  @DataProvider
  public static Object[][] lastPayment() {
    String path = "src/main/resources/Fixtures/ApiTestData/lastPayment.json";
    JSONParser parser = new JSONParser();
    JSONObject jsonObj = null;

    //        Read JSON file
    try {
      Object json = parser.parse(new FileReader(path));
      jsonObj = (JSONObject) json;
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    //        Array to store JSON data
    Object[][] data = new Object[1][1];

    // Store JSON data as key/value pairs in a hashMap
    HashMap<String, String> hashMap = new LinkedHashMap<>();
    if (jsonObj != null) {
      Set<String> jsonObjKeys = jsonObj.keySet();
      for (String jsonObjKey : jsonObjKeys) {
        hashMap.put(jsonObjKey, (String) jsonObj.get(jsonObjKey));
      }
    } else {
      log.error("Error retrieving JSON data");
      throw new RuntimeException();
    }

    //        Store HashMap in an array and return array
    data[0][0] = hashMap;
    return data;
  }
}
