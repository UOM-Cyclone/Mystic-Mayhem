package MysticMayhem.Characters;

import MysticMayhem.RoundSummery;

import java.util.Vector;

public  interface Archer extends Catogery{
    default Character chooseAndAttack(Vector<Character> attacker, Vector<Character> defender, float attack) {
        Character lowestDefence = defender.getFirst();

        for (Character i : defender) {
            if (lowestDefence.getDefense() > i.getDefense()) {
                lowestDefence = i;
            }
        }

        lowestDefence.defence(attack);
        return lowestDefence;
    }
}
