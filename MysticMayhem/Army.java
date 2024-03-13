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
        Vector<Character> battleArmy = new Vector<>();
        cloneCharacter(battleArmy, archer);
        cloneCharacter(battleArmy, knight);
        cloneCharacter(battleArmy, mage);
        cloneCharacter(battleArmy, healer);
        cloneCharacter(battleArmy, mythicalCreature);
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

    private Mage cloneMage(Mage mage){ //clone archer to send
        Mage clonedMage = null;
        if(mage instanceof Warlock){
            clonedMage = new Warlock();
            clonedMage.copy(mage);
        } else if(mage instanceof Illusionist){
            clonedMage = new Illusionist();
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

    private Knight cloneKnight(Knight knight){ //clone archer to send
        Knight clonedKnight = null;
        if(knight instanceof Squire){
            clonedKnight = new Squire();
            clonedKnight.copy(knight);
        } else if(knight instanceof Cavalier){
            clonedKnight = new Cavalier();
            clonedKnight.copy(knight);
        }else if(knight instanceof Templar){
            clonedKnight = new Templar();
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

    private void cloneCharacter(Vector<Character> list, Character character){
        if (character == null){
            return;
        } else if (character instanceof Archer){
            list.add(cloneArcher((Archer) character));
        } else if(character instanceof Knight){
            list.add(cloneKnight((Knight) character));
        } else if (character instanceof Mage){
            list.add(cloneMage((Mage) character));
        } else if (character instanceof Healer){
            list.add(cloneHealer((Healer) character));
        } else if (character instanceof MythicalCreature){
            list.add(cloneCreature((MythicalCreature) character));
        }
    }
}
