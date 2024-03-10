package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;

public interface Character {
    public boolean addArmour(Armour armour);
    public Armour removeArmour();
    public boolean addArtefacts(Artefacts artefacts);
    public Artefacts removeArtefacts();
    public void increaseHealth(float value);
    public void decreaseHealth(float value);
    public boolean attack(Character defender);
    public float defence(float value);
    public float getHealth();
    public float getSpeed();
    public float getDefense();
    public float getCurrentValue();
}
