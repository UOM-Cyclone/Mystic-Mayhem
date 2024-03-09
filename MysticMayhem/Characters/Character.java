package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;

public interface Character {
    public boolean addArmour(Armour armour);
    public boolean removeArmour();
    public boolean addArtefacts(Artefacts artefacts);
    public boolean removeArtefacts();
    public void increaseHealth(float value);
    public void decreaseHealth(float value);
    public boolean attack(Character defender);
    public float defence(int value);
    public float getHealth();
    public float getSpeed();
    public float getDefense();
}
