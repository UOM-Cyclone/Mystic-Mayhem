package MysticMayhem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import MysticMayhem.Characters.Archer;
import MysticMayhem.Equipments.Amulet;
import MysticMayhem.Grounds.Ground;

public class Player {
    private static int count = 0;
    private static Map<String,Player> players = new HashMap<>();
    private static ArrayList<String> usernames = new ArrayList<>();
    private int xp, uID;
    private float gc;
    private String username, pwd, name;
    private Ground hg;
    private Army army;

    public Army getArmy(){return army;}
    public static Map<String,Player> getPlayers(){
        return players;
    }

    public static ArrayList<String> getUserNames(){
        return usernames;
    }

    public static int getPlayerCount(){
        return count;
    }

    public static Player getRandomPlayer(){
        Random rand = new Random();
        int randomnum = rand.nextInt(usernames.size());
        return players.get(usernames.get(randomnum));
    }

    public Player(String name, String uName, String pwd){
        this.name = name;
        this.username = uName;
        this.pwd = pwd; 
        gc = 500;
        xp = 0;
        count++;
        uID  =  count;
        usernames.add(uName);

        players.put(uName, this);
    }

    Player(String name,String uName){
        
    }

    public void updateHashMap(){
        players.put(this.username, this);
    }

    public String getUserName(){
        return this.username;
    }

    public String getName(){
        return this.name;
    }

    public float getGC() {
        return this.gc;
    }

    public int getXP(){
        return this.xp;
    }

    public String getPwd(){
        return this.pwd;
    }

    public Ground getHomeGround(){
        return this.hg;
    }

    public void increaseXP(int i){
        this.xp += i;
    }

    public void changeGC(float amount){
        this.gc += amount;
    }

    public void chnagePwd(String newPwd){
        this.pwd = newPwd;
    }

    public void changeUserName(String newUName){
        this.username = newUName;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void setHomeGround(Ground ground){
        this.hg = ground;
    }
}
