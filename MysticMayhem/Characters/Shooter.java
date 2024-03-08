package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Grounds.Ground;

public class Shooter implements Highlander, Archer {

    float health = 6;
    int speed = 9;
    int attack = 11;
    int defence = 4;
    int price = 80;
    Armour armour = null;
    Artefacts artefacts = null;
    Ground battleGround;


    @Override
    public boolean addArmour(Armour armour) {
        if(this.armour == null){
            this.armour = armour;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeArmour() {
        if(armour == null){
            return false;
        } else {
            armour = null;
            return true;
        }
    }

    @Override
    public boolean addArtefacts(Artefacts artefacts) {
        if(this.artefacts == null){
            this.artefacts = artefacts;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeArtefacts() {
        if(artefacts == null){
            return false;
        } else {
            artefacts = null;
            return true;
        }
    }

    @Override
    public void increaseHealth(float value) {
        health += value;
    }

    @Override
    public void decreaseHealth(float value) {
        health = (value >= health) ? 0 : (health - value);
    }

    @Override
    public boolean attack(Character defender) {
        return defender.defence(attack) == 0;
    }

    @Override
    public float defence(int value) {
        float lostHealth = (float) (0.5 * value - 0.1 * defence);
        health = (lostHealth >= health) ? 0: (health-lostHealth);
        return health;
    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getDefense() {
        return defence;
    }

    @Override
    public boolean addBattleGround(Ground battleGround) {
        this.battleGround = battleGround;
        return false;
    }
}
