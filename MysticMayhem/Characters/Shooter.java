package MysticMayhem.Characters;


public class Shooter extends Highlander implements  Archer {
    static int price = 80;

    {
        health += 6;
        speed += 9;
        attack += 11;
        defence += 4;
        current_value += price;
    }

    @Override
    public boolean attack(Character attacker[], Character defender[]){
        chooseAndAttack(attacker,defender,attack);
        if(isBonusRound) {
            float bonusAttack = (float) (attack * 0.2);
            chooseAndAttack(attacker,defender,bonusAttack);
        }
        for (Character i : attacker) i.addBonusHealth();
        for (Character i : defender) i.addBonusHealth();
        return true;
    }
}
