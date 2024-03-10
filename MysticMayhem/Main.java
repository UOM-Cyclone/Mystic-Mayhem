package MysticMayhem;

import MysticMayhem.Characters.Shooter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Profile {
    static Map<String,Profile> profiles = new HashMap<>();
    static ArrayList<String> usernames = new ArrayList<>();
    String username;
    String userID;
    Profile(String username){
        this.username = username;
        profiles.put(username,this);
        usernames.add(username);
    }
    public static Profile getRandomProfile(){
        Random rand = new Random();
        int randomnum = rand.nextInt(usernames.size());
        return profiles.get(usernames.get(randomnum));
    }

}




public class Main {
    public static void main(String []args){
        System.out.println("test");

        Shooter obj= new Shooter();

        System.out.println(obj.defence(3));


        Profile shanil = new Profile("shanil123");
        Profile thumul = new Profile("thumul456");
        Profile devinda = new Profile("devinda789");
        Profile dinara = new Profile("dinara000");

        System.out.println(Profile.getRandomProfile().username);
    }
}
