package MysticMayhem.Characters;


import MysticMayhem.RoundSummery;

import java.util.Vector;

public class Zing extends Sunchildren implements  Archer {

    public final static int price = 200;
    public final static float initialHealth = 11;
    public final static float initialSpeed = 14;
    public final static float initialAttack = 16;
    public final static float initialDefence = 9;
    public final static String type = "Sunchildren";

    {
        health += initialHealth;
        speed += initialSpeed;
        attack += initialAttack;
        defence += initialDefence;
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
