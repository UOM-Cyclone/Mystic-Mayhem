package MysticMayhem.Characters;

import MysticMayhem.RoundSummery;

import java.util.Vector;

public class Ranger extends Highlander implements Archer{
    public final static int price = 115;
    public final static float initialHealth = 8;
    public final static float initialSpeed = 10;
    public final static float initialAttack = 14;
    public final static float initialDefence = 5;
    public final static String type = "Highlander";

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
