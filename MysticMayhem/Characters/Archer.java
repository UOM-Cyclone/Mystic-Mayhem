package MysticMayhem.Characters;

public  interface Archer extends Catogery{
    default void chooseAndAttack(Character attacker[], Character defender[], float attack) {
        if (defender.length > 0) {
            Character lowestDefence = defender[0];

            for (Character i : defender) {
                if (lowestDefence.getDefense() > i.getDefense()) {
                    lowestDefence = i;
                }
            }

            lowestDefence.defence(attack);
        }
    }
}
