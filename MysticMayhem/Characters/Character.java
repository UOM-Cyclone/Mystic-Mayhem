package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Grounds.Ground;

public interface Character {
    public boolean addArmour(Armour armour);
    public Armour removeArmour();
    public boolean addArtefacts(Artefacts artefacts);
    public Artefacts removeArtefacts();
    public void increaseHealth(float value);
    public void decreaseHealth(float value);
    public boolean attack(Character attacker[], Character defender[]);
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
