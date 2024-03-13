package MysticMayhem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import MysticMayhem.Characters.Archer;
import MysticMayhem.Characters.Healer;
import MysticMayhem.Characters.Knight;
import MysticMayhem.Characters.Mage;
import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.MythicalCreature;
import MysticMayhem.Equipments.Amulet;
import MysticMayhem.Grounds.Ground;

public class Player {
    private static int count = 0;
    private static Map<String,Player> players = new HashMap<>();
    private static ArrayList<String> usernames = new ArrayList<>();

    private ArrayList<Character> archers = new ArrayList();
    private ArrayList<Character> knights = new ArrayList();
    private ArrayList<Character> mages = new ArrayList();
    private ArrayList<Character> healers = new ArrayList();
    private static ArrayList<Character> mythicalCreatures = new ArrayList();
  
    private int gc, xp, uID;
    private String username, pwd, name;
    private Ground hg;
    private Army army;

    public Army getArmy(){return army;}
    public static Map<String,Player> getPlayers(){
        return players;
    }

    public ArrayList<Character> getArchers(){return this.archers;}
    public ArrayList<Character> getKnights(){return this.knights;}
    public ArrayList<Character> getMages(){return this.mages;}
    public ArrayList<Character> getHealers(){return this.healers;}
    public ArrayList<Character> getMythicalCreatures(){return this.mythicalCreatures;}

    public void addArcher(Character archer){archers.add(archer);}
    public void addKnight(Character knight){knights.add(knight);}
    public void addMage(Character mage){mages.add(mage);}
    public void addHealer(Character healer){healers.add(healer);}
    public void addMythicalCreature(Character archer){mythicalCreatures.add(archer);}

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

    public void setArmy(Army army){this.army = army;}

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
