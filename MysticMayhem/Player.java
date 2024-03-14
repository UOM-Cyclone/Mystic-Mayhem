package MysticMayhem;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import MysticMayhem.Characters.Archer;
import MysticMayhem.Characters.Healer;
import MysticMayhem.Characters.Knight;
import MysticMayhem.Characters.Mage;
import MysticMayhem.Characters.Medic;
import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.Dragon;
import MysticMayhem.Characters.MythicalCreature;
import MysticMayhem.Characters.Ranger;
import MysticMayhem.Characters.Squire;
import MysticMayhem.Characters.Warlock;

import MysticMayhem.Characters.*;
import MysticMayhem.Characters.Character;

import MysticMayhem.Equipments.*;
import MysticMayhem.Grounds.Ground;
import MysticMayhem.Grounds.Marshland;

public class Player implements Serializable {
    private static int count = 0;
    private static Map<String,Player> players = new HashMap<>();
    private static ArrayList<String> usernames = new ArrayList<>();

    private ArrayList<Character> archers = new ArrayList();
    private ArrayList<Character> knights = new ArrayList();
    private ArrayList<Character> mages = new ArrayList();
    private ArrayList<Character> healers = new ArrayList();
    private ArrayList<Character> mythicalCreatures = new ArrayList();
    
    private ArrayList<Armour> armors = new ArrayList();
    private ArrayList<Artefacts> artefacts = new ArrayList();
  
    private int gc, xp, uID;
    private String username, name;
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
    
    public ArrayList<Armour> getArmors(){return this.armors;}
    public ArrayList<Artefacts> getArtefacts(){return this.artefacts;}

    public void addArcher(Character archer){archers.add(archer);}
    public void addKnight(Character knight){knights.add(knight);}
    public void addMage(Character mage){mages.add(mage);}
    public void addHealer(Character healer){healers.add(healer);}
    public void addMythicalCreature(Character archer){mythicalCreatures.add(archer);}
    public void addArmor(Armour eq){armors.add(eq);}
    public void addArtefact(Artefacts eq){artefacts.add(eq);}

    public void removeArcher(Character archer){archers.remove(archer);}
    public void removeKnight(Character knight){knights.remove(knight);}
    public void removeMage(Character mage){mages.remove(mage);}
    public void removeHealer(Character healer){healers.remove(healer);}
    public void removeMythicalCreature(Character mythicalCreature){mythicalCreatures.remove(mythicalCreature);}
    public void removeArmor(Armour eq){armors.remove(eq);}
    public void removeArtefact(Artefacts eq){artefacts.remove(eq);}

    public static ArrayList<String> getUserNames(){
        return usernames;
    }

    public static int getPlayerCount(){
        return count;
    }

    public static void saveGameData(){
        try {
            FileOutputStream file = new FileOutputStream("players.cyc");
            ObjectOutputStream out = new ObjectOutputStream(file);
            System.out.println(players);
            out.writeObject(players);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("kela una");
        } catch (IOException e) {
            System.out.println("aiyoooo");
        }

        try {
            FileOutputStream file = new FileOutputStream("usernames.cyc");
            ObjectOutputStream out = new ObjectOutputStream(file);
            System.out.println(usernames);
            out.writeObject(usernames);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("kela una");
        } catch (IOException e) {
            System.out.println("aiyoooo");
        }
    }

    public static void loadGameData(){
        try {
            FileInputStream file = new FileInputStream("players.cyc");
            ObjectInputStream in = new ObjectInputStream(file);
            players = (Map<String, Player>) in.readObject();
            System.out.println(players);
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            Player player1 = new Player("GeraltofRivia","whitewolf",new Marshland(),32,215);
            players.put("whitewolf", player1);
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            System.out.println("");
        }
        try {
            FileInputStream file = new FileInputStream("usernames.cyc");
            ObjectInputStream in = new ObjectInputStream(file);
            usernames = (ArrayList<String>) in.readObject();
            System.out.println(usernames);
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("");
        } catch (IOException e) {
            System.out.println("");
        } catch (ClassNotFoundException e) {
            System.out.println("");
        }
    }

    public static Player getRandomPlayer(){
        Player randomPlayer;
        do {
            Random rand = new Random();
            int randomnum = rand.nextInt(usernames.size());
            randomPlayer = players.get(usernames.get(randomnum));
        } while (randomPlayer.getArmy().isReadyToBattle());

        return randomPlayer;
    }

    public void setArmy(Army army){this.army = army;}

    public Player(String name, String uName, Ground hg){
        this.name = name;
        this.username = uName;
        this.hg = hg;
        gc = 500;
        xp = 0;
        count++;
        uID  =  count;
        usernames.add(uName);

        players.put(uName, this);
    }

    Player(String name, String uName, Ground hg, int xp, int gc){
        this.name = name;
        this.username = uName;
        this.hg = hg;
        this.gc = gc;
        this.xp = xp;
        
        Army tempArmy = new Army();
        Archer tempArcher = new Ranger();
        tempArcher.addArmour(new Chainmail());
        Healer tempHealer = new Medic();
        tempHealer.addArtefacts(new Amulet());

        tempArmy.addArcher(tempArcher);
        tempArmy.addKnight(new Squire());
        tempArmy.addMage(new Warlock());
        tempArmy.addHealer(tempHealer);
        tempArmy.addMythicalCreature(new Dragon());

        this.setArmy(tempArmy);

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

    public Ground getHomeGround(){
        return this.hg;
    }

    public void increaseXP(int i){
        this.xp += i;
    }

    public void changeGC(int amount){
        this.gc += amount;
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
