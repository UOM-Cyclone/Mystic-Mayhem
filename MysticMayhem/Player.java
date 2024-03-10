package MysticMayhem;
import MysticMayhem.Grounds.Ground;

public class Player {
    private static int count = 0;
    private int gc, xp, uID;
    private String username, pwd, name;
    Ground hg;
    public Player(String name, String uName, String pwd){
        this.name = name;
        this.username = uName;
        this.pwd = pwd; 
        gc = 500;
        xp = 0;
        count++;
        uID  =  count;
    }

    Player(String name,String uName){
        
    }
}
