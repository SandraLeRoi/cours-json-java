import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CoursJson {
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Paths.get("src/languages.json"));
        JSONArray json = (JSONArray)JSONValue.parse(text);
        System.out.println(json);
        json.forEach(language -> ((JSONObject)language).get("name"));
//        for (Object languageObject: json) {
//            JSONObject language = (JSONObject)languageObject;
//            String name = (String)language.get("name");
//            System.out.println(name);
//        }
    }
}
