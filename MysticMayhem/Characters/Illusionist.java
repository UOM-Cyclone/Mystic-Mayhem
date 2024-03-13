package MysticMayhem.Characters;


import MysticMayhem.RoundSummery;

import java.util.Vector;

public class Illusionist extends Mystics implements  Mage {
    public final static int price = 120;
    public final static float initialHealth = 12;
    public final static float initialSpeed = 14;
    public final static float initialAttack = 13;
    public final static float initialDefence = 8;
    public final static String type = "Mystic";

    {
        attack += initialAttack;
        defence += initialDefence;
        health += initialHealth;
        speed += initialSpeed;
        current_value += price;
    }

    @Override
    public void attack(Vector<Character> attacker, Vector<Character> defender, RoundSummery roundSummery){
        Character defendChar = chooseAndAttack(attacker,defender,attack);
        roundSummery.setDefendChar(defendChar);
        roundSummery.setDefenderHealth(defendChar.getHealth());

        if(isBonusRound && !defender.isEmpty()) {
            roundSummery.setBonusRound(true);
            float bonusAttack = (float) (attack * 0.2);
            defendChar = chooseAndAttack(attacker,defender,bonusAttack);
            roundSummery.setBonusDefendChar(defendChar);
            roundSummery.setBonusDefenderHealth(defendChar.getHealth());
        }
        addBonusHealth();
    }
}

