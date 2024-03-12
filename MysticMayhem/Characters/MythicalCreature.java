package MysticMayhem.Characters;

import java.util.Vector;

public interface MythicalCreature extends Catogery {
    default Character chooseAndAttack(Vector<Character> attacker, Vector<Character> defender, float attack) {
        Character lowestDefenceHolder = defender.getFirst();

        for (Character i : defender) {
            if (lowestDefenceHolder.getDefense() > i.getDefense()) {
                lowestDefenceHolder = i;
            }
        }

        lowestDefenceHolder.defence(attack);
        if(lowestDefenceHolder.getHealth()==0){
            defender.remove(lowestDefenceHolder);
        }
        return lowestDefenceHolder;
    }

}