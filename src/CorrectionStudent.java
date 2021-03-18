import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleToIntFunction;
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
        long total = students.stream().mapToInt(CorrectionStudent::getMoy).sum();
        float average = (float)total/ students.size();
        System.out.println(average);

        //exo4
        List goodStudents = (List) students.stream().filter(student -> (long)((JSONObject)student).get("moyenne")>average).collect(Collectors.toList());
        System.out.println(goodStudents);
        System.out.println(getNames(goodStudents));

        //exo5
        students.sort(CorrectionStudent::compareStudents);
        System.out.println(getNames(students));
    }

    static int compareStudents(Object student1, Object student2){
        long moyenne1 = (long)((JSONObject)student1).get("moyenne");
        long moyenne2 = (long)((JSONObject)student2).get("moyenne");
        return (int)(moyenne1 - moyenne2);
    }

    static int getMoy(Object student){
        JSONObject studentJson = (JSONObject) student;
        return (int) (long) studentJson.get("moyenne");
    }
    static String getName(Object student) {
        JSONObject studentJson = (JSONObject) student;
        return studentJson.get("fname") + " " + studentJson.get("lname") + " "+ studentJson.get("moyenne");
    }

    static ArrayList<String> getNames(List students) {
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
