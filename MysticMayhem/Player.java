package MysticMayhem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import MysticMayhem.Grounds.Ground;

public class Player {
    private static int count = 0;
    private static Map<String,Player> players = new HashMap<>();
    private static ArrayList<String> usernames = new ArrayList<>();
    private int gc, xp, uID;
    private String username, pwd, name;
    private Ground hg;

    public static Map<String,Player> getPlayers(){
        return players;
    }

    public static ArrayList<String> getUserNames(){
        return usernames;
    }

    public static int getPlayerCount(){
        return count;
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

    public int getGC() {
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

    public void changeGC(int amount){
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
