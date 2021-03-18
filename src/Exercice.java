import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Exercice {
    private static int compteur = 0;
    private static Pattern pattern;
    private static Matcher matcher;
    private static ArrayList<String> listComptesAVerifier1;
    private static int nombresComptesInput1;
    public static void main(String[] args) throws IOException {
        ArrayList<String> listComptesAVerifier1 = new ArrayList<>();
        ArrayList<String> text = (ArrayList<String>) Files.readAllLines(Paths.get("src/ex1/input1.txt"));
        System.out.println(text);
        nombresComptesInput1 = Integer.parseInt(text.get(0));
        System.out.println(nombresComptesInput1);
        System.out.println(listComptesAVerifier1);
        ajouterComptesAVerifier(text);
        System.out.println(listComptesAVerifier1);
        pattern = Pattern.compile("[0-9]{5}$");


    }

    static ArrayList<String> ajouterComptesAVerifier(ArrayList<String> list) {
        for (int i = 1; i < list.size(); i++) {
            String elem = list.get(i);
            System.out.println(elem);
            listComptesAVerifier1.add(elem);
        }
        System.out.println(listComptesAVerifier1);
        return listComptesAVerifier1;
    }

//    static int verifierComptes(int number, ArrayList list){
//        for (int i = 0; i < number; i++) {
//            if (list.get(i) == pattern)
//            compteur += 1;
//        }
//    }
}
