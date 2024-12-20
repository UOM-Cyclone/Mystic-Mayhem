package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Grounds.Ground;
import MysticMayhem.RoundSummery;

import java.io.Serializable;
import java.util.Vector;

public interface Character extends Serializable {
    default float roundToFirstDecimal(float value){
        return (float) Math.round(value * 10) / 10;
    }

    public boolean addArmour(Armour armour);
    public Armour removeArmour();
    public boolean addArtefacts(Artefacts artefacts);
    public Artefacts removeArtefacts();
    public void increaseHealth(float value);
    public void decreaseHealth(float value);
    public void attack(Vector<Character> attacker, Vector<Character> defender, RoundSummery roundSummery);
    public float defence(float value);
    public float getHealth();
    public float getSpeed();
    public float getDefense();
    public float getAttack();
    public float getCurrentValue();
    public Armour getArmour();
    public Artefacts getArtefact();
    public Ground getBattleGround();
    public void addBonusHealth();
    public void copy(Character character);
    public boolean addBattleGround(Ground battleGround);

}
