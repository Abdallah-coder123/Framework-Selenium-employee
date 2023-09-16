package Helpers;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadWriteHelper {

  public static HashMap<String, Object> jsonData(String path) {
    JSONParser parser = new JSONParser();
    JSONObject jsonObj = null;

    //        Read JSON file
    try {
      Object json = parser.parse(new FileReader(path));
      jsonObj = (JSONObject) json;
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }

    // Store JSON data as key/value pairs in a hashMap
    HashMap<String, Object> hashMap = new LinkedHashMap<>();
    if (jsonObj != null) {
      Set<String> jsonObjKeys = jsonObj.keySet();
      for (String jsonObjKey : jsonObjKeys) {
        hashMap.put(jsonObjKey, jsonObj.get(jsonObjKey));
      }
    } else {
      //            log.error("Error retrieving JSON data");
      throw new RuntimeException();
    }

    return hashMap;
  }

  public static Map<String, Object> saveJsonInMap(JSONObject object) {
    Map<String, Object> map = new HashMap();

    Iterator keys = object.keySet().iterator();
    while (keys.hasNext()) {
      String key = (String) keys.next();
      Object value = object.get(key);
      if (value instanceof JSONObject) {
        value = saveJsonInMap((JSONObject) value);
      }
      if (value instanceof JSONArray) {
        value = saveJsonInList((JSONArray) value);
      }
      map.put(key, value);
    }
    return map;
  }

  public static List<Object> saveJsonInList(JSONArray object) {
    List<Object> list = new ArrayList<Object>();

    for (int i = 0; i < object.size(); i++) {
      Object value = object.get(i);
      if (value instanceof JSONObject) {
        value = saveJsonInMap((JSONObject) value);
      }
      if (value instanceof JSONArray) {
        value = saveJsonInList((JSONArray) value);
      }
      list.add(value);
    }

    return list;
  }

  public static Map<String, Object> getDataFromJson(String filePath) {
    JSONParser parser = new JSONParser();
    Map<String, Object> map = new HashMap();
    try {
      JSONObject obj = (JSONObject) parser.parse(new FileReader(filePath));

      Iterator keys = obj.keySet().iterator();
      while (keys.hasNext()) {
        String key = (String) keys.next();
        Object value = obj.get(key);

        if (value instanceof JSONObject) {
          value = saveJsonInMap((JSONObject) value);
        }
        if (value instanceof JSONArray) {
          value = saveJsonInList((JSONArray) value);
        }
        map.put(key, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  public static Object getJsonInfo(String jsonFile) {
    JSONParser parser = new JSONParser();
    Object obj = null;
    try {
      obj = parser.parse(new FileReader(jsonFile));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {}
    return obj;
  }

  public static String getBodyFromJson(String jsonFile) throws IOException {
    byte[] inputs = Files.readAllBytes(Paths.get(jsonFile));
    String body = new String(inputs);

    return body;
  }
}
