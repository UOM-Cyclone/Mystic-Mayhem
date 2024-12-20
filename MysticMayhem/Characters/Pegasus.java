package MysticMayhem.Characters;


import MysticMayhem.RoundSummery;

import java.util.Vector;

public class Pegasus extends Mystics implements  MythicalCreature {
    public final static int price = 340;
    public final static float initialHealth = 20;
    public final static float initialSpeed = 20;
    public final static float initialAttack = 14;
    public final static float initialDefence = 18;
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

