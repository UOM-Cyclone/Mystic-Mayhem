package MysticMayhem.Characters;

import java.util.Vector;

public interface Healer extends Catogery{
    default Character chooseAndAttack(Vector<Character> attacker, Vector<Character> defender, float attack) {
        Character lowestHealthHolder = attacker.firstElement();

        for (Character i : attacker) {
            if (lowestHealthHolder.getHealth() > i.getHealth()) {
                lowestHealthHolder = i;
            }
        }

        lowestHealthHolder.increaseHealth( roundToFirstDecimal((float) (0.1 * attack)));

        return lowestHealthHolder;
    }
}