package MysticMayhem.Equipments;

import java.io.Serializable;

public interface Equipment extends Serializable {
    public float getPrice();
    public float getAttack();
    public float getDefence();
    public float getHealth();
    public float getSpeed();
}
