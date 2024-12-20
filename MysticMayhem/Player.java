package MysticMayhem;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import MysticMayhem.Characters.*;
import MysticMayhem.Equipments.*;
import MysticMayhem.Grounds.*;

public class Player implements Serializable {
    public static int playerCount = 0;
    public static Map<String,Player> players = new HashMap<>();

    private String name ;
    private final String username;
    private final Ground homeGround;
    private int gc, xp, uID;
    private final Army army = new Army();
    private final Vector<Archer> archers = new Vector<>();
    private final Vector<Knight> knights = new Vector<>();
    private final Vector<Mage> mages = new Vector<>();
    private final Vector<Healer> healers = new Vector<>();
    private final Vector<MythicalCreature> creatures = new Vector<>();


    public Player(String name, String username, Ground ground){
        this.name = name;
        this.username = username;
        this.homeGround = ground;
        this.gc = 500;
        this.xp = 1;
        this.uID = ++playerCount;
        players.put(username,this);
    }

    Player(String name, String uName, Ground homeGround, int xp, int gc){
        this.name = name;
        this.username = uName;
        this.homeGround = homeGround;
        this.gc = gc;
        this.xp = xp;
        this.uID = ++playerCount;

        Archer tempArcher = new Ranger();
        tempArcher.addArmour(new Chainmail());
        Healer tempHealer = new Medic();
        tempHealer.addArtefacts(new Amulet());

        this.army.addArcher(tempArcher);
        this.army.addKnight(new Squire());
        this.army.addMage(new Warlock());
        this.army.addHealer(tempHealer);
        this.army.addMythicalCreature(new Dragon());
        players.put(uName, this);
    }

    public Vector<Archer> getArchers(){return archers;}
    public Vector<Knight> getKnights(){return knights;}
    public Vector<Mage> getMages(){return mages;}
    public Vector<Healer> getHealers(){return healers;}
    public Vector<MythicalCreature> getCreatures(){return creatures;}
    public String getName(){return name;}
    public Ground getHomeGround(){return homeGround;}
    public Army getArmy(){return army;}
    public void increaseXP(int val){xp += val;}
    public int getGC(){return gc;}
    public void changeGC(int val){this.gc += val;}
    public int getXP(){return xp;}

    public String getUsername(){
        return this.username;
    }

    public void setName(String name){this.name = name;}

    public static void saveGameData(){
        try {
            FileOutputStream file = new FileOutputStream("players.cyc");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(players);
            out.close();
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR player data was not saved !");
        }
    }
//
    public static void loadGameData(){
        try {
            FileInputStream file = new FileInputStream("players.cyc");
            ObjectInputStream in = new ObjectInputStream(file);
            players = (Map<String, Player>) in.readObject();
            playerCount = players.size();
            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            Player player1 = new Player("GeraltofRivia","whitewolf",new Marshland(),32,215);
        } catch (IOException e) {
            System.out.println("ERROR! Saved data not loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("This is invalid file");
        }
    }

   public static Player getRandomPlayer(){
       Random rand = new Random();
       Player randomPlayer = null;
       List<Player> usernames = new ArrayList<>(Player.players.values());
       List<Player> readyPlayers = usernames.stream().filter(player -> player.getArmy().isReadyToBattle())
               .collect(Collectors.toList());
       if (readyPlayers.size() <= 1){
           return null;
       } else {
           return readyPlayers.get(rand.nextInt(readyPlayers.size()));
       }

   }
}
