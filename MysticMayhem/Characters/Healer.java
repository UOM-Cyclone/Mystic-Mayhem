package MysticMayhem.Characters;

import java.util.Vector;

public interface Healer extends Catogery{
    default Character chooseAndAttack(Vector<Character> attacker, Vector<Character> defender, float attack) {
        Character lowestDefenceHolder = attacker.getFirst();

        for (Character i : attacker) {
            if (lowestDefenceHolder.getDefense() > i.getDefense()) {
                lowestDefenceHolder = i;
            }
        }

        lowestDefenceHolder.increaseHealth((float) 0.1 * attack);
        if(lowestDefenceHolder.getHealth()==0){
            defender.remove(lowestDefenceHolder);
        }
        return lowestDefenceHolder;
    }
}
