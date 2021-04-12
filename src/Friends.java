import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Friends {
    static ArrayList<String> list;
    public static void main(String[] args) throws IOException {
        String data = Files.readString(Paths.get("src/friends.json"));
//        System.out.println(JSONValue.parse(data));
        JSONObject sarai = (JSONObject)JSONValue.parse(data);
        // question 1
        System.out.println(getFriendsCount(sarai));
        //question 2
        System.out.println(getFriendsNames(sarai));
        //question 3
        System.out.println(getFriendsFriends(sarai));
        // question 4
        getAllNames(sarai);
        // question 5
        //getAllRelation(sarai, null);
        getAllRelation2(sarai);

    }

//    question 1
    public static int getFriendsCount(JSONObject personne) {
        JSONArray friends = (JSONArray) personne.get("friends");
        return friends.size();
    }

    public static ArrayList<String> getFriendsNames(JSONObject person) {
        JSONArray friends = (JSONArray) person.get("friends");
        ArrayList<String> friendsNames = new ArrayList<>();
        for (Object friendObject: friends) {
            JSONObject friend = (JSONObject) friendObject;
            String fname = (String) friend.get("fname");
            String lname = (String) friend.get("lname");
            friendsNames.add(fname+" "+lname);
        }
        return friendsNames;
    }

    public static ArrayList<String> getFriendsFriends(JSONObject person) {
        JSONArray friends = (JSONArray) person.get("friends");
        ArrayList<String> friendsFriendsNames = new ArrayList<>();
        for (Object friendObject: friends) {
            JSONObject friend = (JSONObject) friendObject;
            ArrayList<String> names = getFriendsNames(friend);
            friendsFriendsNames.addAll(names);
        }
        return friendsFriendsNames;
    }

    public static ArrayList<String> getAllNames(JSONObject person) {
        String fname = (String) person.get("fname");
        String lname = (String) person.get("lname");
        System.out.println(fname+" "+lname);
        JSONArray friends = (JSONArray) person.get("friends");
        ArrayList<String> friendsFriendsNames = new ArrayList<>();
        for (Object friendObject: friends) {
            JSONObject friend = (JSONObject) friendObject;
            getAllNames(friend);
        }
        return friendsFriendsNames;
    }

    public static void getAllRelation(JSONObject person, JSONObject parent) {
        String fname = (String) person.get("fname");
        String lname = (String) person.get("lname");
        if(parent != null) {
            String parentFname = (String) parent.get("fname");
            String parentLname = (String) parent.get("lname");
            System.out.println(parentFname+" "+parentLname+" - "+fname+" "+lname);
        }
        JSONArray friends = (JSONArray) person.get("friends");
        for (Object friendObject: friends) {
            JSONObject friend = (JSONObject) friendObject;
            getAllRelation(friend, person);
        }
    }

    public static void getAllRelation2(JSONObject person) {
        JSONArray friends = (JSONArray) person.get("friends");
        for (Object friendObject: friends) {
            JSONObject friend = (JSONObject) friendObject;
            getAllRelation2(friend, person);
        }
    }
    public static void getAllRelation2(JSONObject person, JSONObject parent) {
        String fname = (String) person.get("fname");
        String lname = (String) person.get("lname");
        if(parent != null) {
            String parentFname = (String) parent.get("fname");
            String parentLname = (String) parent.get("lname");
            System.out.println(parentFname+" "+parentLname+" - "+fname+" "+lname);
        }
        JSONArray friends = (JSONArray) person.get("friends");
        for (Object friendObject: friends) {
            JSONObject friend = (JSONObject) friendObject;
            getAllRelation2(friend, person);
        }
    }
}
