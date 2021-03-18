import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Students {
    private static float moyenne;
    private static ArrayList<String> listBetterStudent = new ArrayList<>();
    private static ArrayList<String> listNotesSortByAscending = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArrayList<String> listEleve = new ArrayList<>();
        ArrayList<Integer> listNotes = new ArrayList<>();
        String text = Files.readString(Paths.get("src/students.json"));
        JSONObject json = (JSONObject)JSONValue.parse(text);
//        System.out.println(json);

        JSONObject hoc = json;
        JSONArray students = (JSONArray) hoc.get("students");
        System.out.println("le nombre d'étudiant est: " + students.size());

        for (Object student: students) {
            JSONObject eleve = (JSONObject)student;
            String firstName = (String) eleve.get("fname");
            String lastName = (String) eleve.get("lname");
            String eleveComplet = firstName + " " + lastName;
            listEleve.add(eleveComplet);
        }
        System.out.println(listEleve);

        for (Object notes: students) {
            JSONObject note = (JSONObject)notes;
            long moyennes = (long) note.get("moyenne");
//            System.out.println(moyennes);
            listNotes.add(Math.toIntExact(moyennes));
        }
        //System.out.println(listNotes);
        average(listNotes);
        betterStudents(students);
        sortByNotesAscending(listNotes,students);

    }
    static float average(ArrayList tableau) {
        int sum = 0;

        //additionner tous les nombres du tableau
        for (int i = 0; i < tableau.size(); i++) {
            sum += (int)tableau.get(i);
//            System.out.println(sum);
        }
        //puis on divise la somme par le chiffre total des nombres du tableau
        moyenne = (float) sum / tableau.size();
        //retourne la moyenne
        System.out.println("la moyenne est de " + moyenne);
        return moyenne;
    }

    static void betterStudents (JSONArray tableau) {
        for (Object element: tableau) {
            JSONObject elem = (JSONObject)element;
            long moyenneEleve = (long) elem.get("moyenne");

            String firstName = (String) elem.get("fname");
            String lastName = (String) elem.get("lname");
            if (moyenneEleve >= moyenne) {
                String eleveComplet = firstName + " " + lastName;
                listBetterStudent.add(eleveComplet);
            }
        }
        System.out.println(listBetterStudent);
    }

    static void sortByNotesAscending(ArrayList<Integer> list, JSONArray jsonArray){
        //je range les moyennes par ordre croissant
        list.sort(Students::comparaisonDeMoyenne);
        for (int i = 0; i < list.size(); i++) {
            //je veux retrouver l'élève qui a la note à l'index i
            int valeur  = list.get(i);
            for (Object element: jsonArray) {
                JSONObject elem = (JSONObject) element;
                long moyenneEleve = (long) elem.get("moyenne");
                String firstName = (String) elem.get("fname");
                String lastName = (String) elem.get("lname");
                if (moyenneEleve == valeur){
                    String eleveComplet = firstName + " " + lastName;
                    listNotesSortByAscending.add(eleveComplet);
                }
            }
        }
        System.out.println(listNotesSortByAscending);
    }

    static int comparaisonDeMoyenne(int a, int b){
        if (a>b){
            return 1;
        } else {
            return -1;
        }
    }
}
