package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Grounds.Ground;

public class Shooter implements Highlander, Archer {

    private float health = 6;
    private float speed = 9;
    private float attack = 11;
    private float defence = 4;
    static int price = 80;
    private int current_value = 80;

    Armour armour = null;
    Artefacts artefact = null;
    Ground battleGround;


    @Override
    public boolean addArmour(Armour armour) {
        if(this.armour == null){
            this.armour = armour;
            health += armour.getHealth();
            speed += armour.getSpeed();
            attack += armour.getAttack();
            defence += armour.getDefence();
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
            health -= armour.getHealth();
            speed -= armour.getSpeed();
            attack -= armour.getAttack();
            defence -= armour.getDefence();
            return true;
        }
    }

    @Override
    public boolean addArtefacts(Artefacts artefact) {
        if(this.artefact == null){
            this.artefact = artefact;
            health += artefact.getHealth();
            speed += artefact.getSpeed();
            attack += artefact.getAttack();
            defence += artefact.getDefence();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeArtefacts() {
        if(artefact == null){
            return false;
        } else {
            artefact = null;
            this.artefact = artefact;
            health -= artefact.getHealth();
            speed -= artefact.getSpeed();
            attack -= artefact.getAttack();
            defence -= artefact.getDefence();
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
    public float defence(float value) {
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
    public float getCurrentValue() {
        return current_value;
    }

    @Override
    public boolean addBattleGround(Ground battleGround) {
        this.battleGround = battleGround;
        return false;
    }
}
