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

    private Knight cloneKnight(Knight knight){
        Knight clonedKnight = null;
        if(kinght instanceof Squire){
            clonedKnight = new Squire();
            clonedKnight.copy(knight);
        } else if(knight instanceof Cavalier){
            clonedKnight = new Cavalier();
            clonedKnight.copy(knight);
        }else if(knight instanceof Templar){
            clonedKnight = new Templer();
            clonedKnight.copy(knight);
        }else if(knight instanceof Zoro){
            clonedKnight = new Zoro();
            clonedKnight.copy(knight);
        }else if(knight instanceof Swiftblade){
            clonedKnight = new Swiftblade();
            clonedKnight.copy(knight);
        }
        return clonedKnight;
    }

    private Mage cloneMage(Mage mage){ //clone archer to send
        Mage clonedMage = null;
        if(mage instanceof Warlock){
            clonedMage = new Warlock();
            clonedMage.copy(mage);
        } else if(mage instanceof Illusionlist){
            clonedMage = new Illusionlist();
            clonedMage.copy(mage);
        }else if(mage instanceof Enchanter){
            clonedMage = new Enchanter();
            clonedMage.copy(mage);
        }else if(mage instanceof Conjurer){
            clonedMage = new Conjurer();
            clonedMage.copy(mage);
        }else if(mage instanceof Eldritch){
            clonedMage = new Eldritch();
            clonedMage.copy(mage);
        }
        return clonedMage;
    }
}
