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

    private MythicalCreature cloneCreature(MythicalCreature mythicalCreature){ //clone archer to send
        MythicalCreature clonedCreature = null;
        if(mythicalCreature instanceof Dragon){
            clonedCreature = new Dragon();
            clonedCreature.copy(mythicalCreature);
        } else if(mythicalCreature instanceof Basilisk){
            clonedCreature = new Basilisk();
            clonedCreature.copy(mythicalCreature);
        }else if(mythicalCreature instanceof Hydra){
            clonedCreature = new Hydra();
            clonedCreature.copy(mythicalCreature);
        }else if(mythicalCreature instanceof Phoenix){
            clonedCreature = new Phoenix();
            clonedCreature.copy(mythicalCreature);
        }else if(mythicalCreature instanceof Pegasus){
            clonedCreature = new Pegasus();
            clonedCreature.copy(mythicalCreature);
        }
        return clonedCreature;
    }

    private Healer cloneHealer(Healer healer){ //clone archer to send
        Healer clonedHealer = null;
        if(healer instanceof Soother){
            clonedHealer = new Soother();
            clonedHealer.copy(healer);
        } else if(healer instanceof Medic){
            clonedHealer = new Medic();
            clonedHealer.copy(healer);
        }else if(healer instanceof Alchemist){
            clonedHealer = new Alchemist();
            clonedHealer.copy(healer);
        }else if(healer instanceof Saint){
            clonedHealer = new Saint();
            clonedHealer.copy(healer);
        }else if(healer instanceof Lightbringer){
            clonedHealer = new Lightbringer();
            clonedHealer.copy(healer);
        }
        return clonedHealer;
    }

    private void cloneCharacter(Vector<Character> list, Character character){
        if (character == null){
            return;
        } else if (character instanceof Archer){
            list.add(cloneArcher((Archer) character));
        } else if(character instanceof Knight){
            list.add(cloneKnight((Knight) character));
        } else if (character instanceof Mage){
            list.add(cloneMage((Knight) character));
        } else if (character instanceof Healer){
            list.add(cloneHealer((Healer) character));
        } else if (character instanceof MythicalCreature){
            list.add(cloneCreature((MythicalCreature) character));
        }
    }
}
