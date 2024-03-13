package MysticMayhem.Characters;


import MysticMayhem.RoundSummery;

import java.util.Vector;

public class Basilisk extends Marshlanders implements  MythicalCreature {
    public final static int price = 165;
    public final static float initialHealth = 10;
    public final static float initialSpeed = 12;
    public final static float initialAttack = 15;
    public final static float initialDefence = 11;
    public final static String type = "Marshlander";

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

