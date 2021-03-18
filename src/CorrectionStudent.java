import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CorrectionStudent {
    public static void main(String[] args) throws IOException {
        String file = Files.readString(Paths.get("src/students.json"));
        JSONObject group = (JSONObject) JSONValue.parse(file);
        JSONArray students = (JSONArray) group.get("students");
        //nombre des élèves
        System.out.println(students.size());

        // tableau des élèves
        //solution 1
        System.out.println(getNames(students));
        //deuxième solution
        System.out.println(students.stream().map(CorrectionStudent::getName).collect(Collectors.toList()));

        //e3
        long total = students.stream().map(CorrectionStudent::getMoy).reduce();
    }

    static  long getMoy(Object student){
        JSONObject studentJson = (JSONObject) student;
        return (Long) studentJson.get("moyenne");
    }
    static String getName(Object student) {
        JSONObject studentJson = (JSONObject) student;
        return studentJson.get("fname") + " " + studentJson.get("lname") + " "+ studentJson.get("moyenne");
    }

    static ArrayList<String> getNames(JSONArray students) {
        ArrayList<String> names = new ArrayList<>();
        for (Object student : students) {
            JSONObject studentJson = (JSONObject) student;
            String fname = (String) studentJson.get("fname");
            String lname = (String) studentJson.get("lname");
            Long moyenne = (Long) studentJson.get("moyenne");
            names.add(fname + " " + lname + " " + moyenne);
        }
        return names;
    }
}
