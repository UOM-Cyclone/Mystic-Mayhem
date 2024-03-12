package MysticMayhem;

import MysticMayhem.Characters.*;
import MysticMayhem.Characters.Character;
import MysticMayhem.Grounds.Ground;

import java.util.Vector;

public class Army {
    Archer archer = null;
    Knight knight = null;
    Mage mage = null;
    Healer healer = null;
    MythicalCreature mythicalCreature= null;


    public void addArcher(Archer archer){this.archer = archer;}
    public  void addKnight(Knight knight){this.knight = knight;}
    public  void addMage(Mage mage){this.mage = mage;}
    public  void addHealer(Healer healer){this.healer = healer;}
    public  void addMythicalCreature(MythicalCreature mythicalCreature){this.mythicalCreature = mythicalCreature;}


    public Vector<Character> toBattle(Ground ground){ // to send army to battle
        Vector<Character> battleArmy = null;
        Archer clonedArcher = cloneArcher(archer);
        clonedArcher.addBattleGround(ground);
        battleArmy.add(clonedArcher);
        return battleArmy;
    }

    private Archer cloneArcher(Archer archer){ //clone archer to send
        Archer clonedArcher = null;
        if(archer instanceof Shooter){
            clonedArcher = new Shooter();
            clonedArcher.copy(archer);
        } else if(archer instanceof Ranger){
            clonedArcher = new Ranger();
            clonedArcher.copy(archer);
        }else if(archer instanceof Sunfire){
            clonedArcher = new Sunfire();
            clonedArcher.copy(archer);
        }else if(archer instanceof Zing){
            clonedArcher = new Zing();
            clonedArcher.copy(archer);
        }else if(archer instanceof Saggitarius){
            clonedArcher = new Saggitarius();
            clonedArcher.copy(archer);
        }
        return clonedArcher;
    }
}
