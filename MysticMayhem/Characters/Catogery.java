package MysticMayhem.Characters;

import java.util.Vector;

public interface Catogery extends Character{
    default Character findDefender(Vector<Character> defenders){
        Vector<Character> lowestDefenders = new Vector<>();

        float lowestValue = defenders.getFirst().getDefense();

        for (Character i : defenders){
            if(lowestValue > i.getDefense()){
                lowestValue = i.getDefense();
                lowestDefenders.clear();
                lowestDefenders.add(i);
            } else if(lowestValue == i.getDefense()){
                lowestDefenders.add(i);
            }
        }

        for (Character i : lowestDefenders){
            if (i instanceof Healer) return i;
        }
        for (Character i : lowestDefenders){
            if (i instanceof MythicalCreature) return i;
        }
        for (Character i : lowestDefenders){
            if (i instanceof Archer) return i;
        }
        for (Character i : lowestDefenders){
            if (i instanceof Knight) return i;
        }
        for (Character i : lowestDefenders){
            if (i instanceof Mage) return i;
        }
        return null;
    }
}
