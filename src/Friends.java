import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Friends {
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        String text = Files.readString(Paths.get("src/friends.json"));
        System.out.println(JSONValue.parse(text));
        JSONObject json = (JSONObject)JSONValue.parse(text);
        JSONObject friens = json;
        JSONArray friendsSarai = (JSONArray) friens.get("friends");
        System.out.println(friendsSarai.size());
        System.out.println(friendsSarai.stream().map(Friends::getName).collect(Collectors.toList()));


       getFriends(friendsSarai);

    }

    static String getName(Object ami) {
        JSONObject amiSarai = (JSONObject) ami;
        return amiSarai.get("fname") + " " + amiSarai.get("lname") + amiSarai.get("friends");
    }

    static String getFriends (JSONArray amis) {
        ArrayList<String> list = new ArrayList<>();

        //Pour chaque ami de Sarai  je veux récupérer ses amis
        for(Object ami :amis) {
            JSONObject friendsOfFriends = (JSONObject) ami;
            String lesPrenomAmis = (String) friendsOfFriends.get("fname");
            String lesNomsAmis = (String) friendsOfFriends.get("lname");
            String lesAmisAmis = (String) friendsOfFriends.get("friens");
//            System.out.println(friendsOfFriends);
            list.add(lesPrenomAmis + " " + lesNomsAmis + " " + lesAmisAmis);
            System.out.println(list);
        }
        return String.valueOf(list);
//        ArrayList<String> listeAmi = null;
//        //Pour chaque ami de SArai je veux récupérer ses amis
//        for(Object ami : amis) {
//            JSONArray amiAmis = (JSONArray) ami;
//            if (amiAmis.contains(ami)) {
//                listeAmi.add(getFriends((JSONArray) ami));
//            }
//        }
//
//        return getName(listeAmi);
    }

}
