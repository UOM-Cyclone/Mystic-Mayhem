package MysticMayhem.Characters;

import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Grounds.Arcane;
import MysticMayhem.Grounds.Ground;
import MysticMayhem.Grounds.Hillcrest;

public abstract class Highlander implements Type {
    protected float health = 0;
    protected float speed = 0;
    protected float attack = 0;
    protected float defence = 0;
    protected Artefacts artefact = null;
    protected Armour armour = null;
    protected Ground battleGround = null;
    protected boolean isBonusRound = false;
    protected float current_value = 0;
    private boolean isBonusHealth = false;


    @Override
    public boolean addArmour(Armour armour) {
        if(this.armour == null){
            this.armour = armour;
            health += armour.getHealth();
            speed += armour.getSpeed();
            attack += armour.getAttack();
            defence += armour.getDefence();

            current_value += roundToFirstDecimal((float) (0.2 * armour.getPrice()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Armour removeArmour() {
        if(armour == null){
            return null;
        } else {
            health -= armour.getHealth();
            speed -= armour.getSpeed();
            attack -= armour.getAttack();
            defence -= armour.getDefence();

            current_value -= roundToFirstDecimal((float) (0.2 * armour.getPrice()));
            Armour temp = armour;
            armour = null;
            return temp;
        }
    }

    public Armour replaceArmour(Armour armour){
        Armour temp = this.armour;
        removeArmour();
        addArmour(armour);
        return temp;
    }
    @Override
    public boolean addArtefacts(Artefacts artefact) {
        if(this.artefact == null){
            this.artefact = artefact;
            health += artefact.getHealth();
            speed += artefact.getSpeed();
            attack += artefact.getAttack();
            defence += artefact.getDefence();

            current_value += roundToFirstDecimal((float) (0.2 * artefact.getPrice()));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Artefacts removeArtefacts() {
        if(artefact == null){
            return null;
        } else {
            health -= artefact.getHealth();
            speed -= artefact.getSpeed();
            attack -= artefact.getAttack();
            defence -= artefact.getDefence();

            current_value -= roundToFirstDecimal((float) (0.2 * artefact.getPrice()));
            Artefacts temp = artefact;
            artefact = null;
            return temp;
        }
    }

    public Artefacts replaceArtefacts(Artefacts artefacts){
        Artefacts temp = this.artefact;
        removeArtefacts();
        addArtefacts(artefacts);
        return temp;
    }

    @Override
    public void increaseHealth(float value) {
        health += value;
    }

    @Override
    public void decreaseHealth(float value) {
        health = (value >= health) ? 0 : roundToFirstDecimal(health - value);
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
    public float getAttack() {
        return attack;
    }

    @Override
    public Armour getArmour() {
        return armour;
    }

    @Override
    public Artefacts getArtefact() {
        return artefact;
    }

    @Override
    public Ground getBattleGround() {
        return battleGround;
    }

    @Override
    public boolean addBattleGround(Ground battleGround) {

        if(battleGround instanceof Hillcrest){
            attack += 1;
            defence += 1;
            isBonusRound = true;
        } else if (battleGround instanceof Arcane){
            speed -= 1;
            defence -= 1;
        }

        this.battleGround = battleGround;
        return false;
    }


    @Override
    public float defence(float value) {
        float lostHealth = (float) (0.5 * value - 0.1 * defence);
        health = (lostHealth >= health) ? 0: roundToFirstDecimal(health-lostHealth);
        return health;
    }

    @Override
    public void addBonusHealth(){
        if(isBonusHealth){
            health += roundToFirstDecimal(roundToFirstDecimal((float) (0.1 * health)));
        }
    }

    @Override
    public float getCurrentValue() {
        return current_value;
    }

    @Override
    public void copy(Character character) {
        health = character.getHealth();
        speed = character.getSpeed();
        attack = character.getAttack();
        defence = character.getDefense();
        current_value = character.getCurrentValue();
        armour = getArmour();
        artefact = getArtefact();
        battleGround = getBattleGround();
    }
}