package MysticMayhem.Equipments;

public class Excalibur implements Artefacts{

    public final static float PRICE = 150;
    public final static float ATTACK = 2;
    public final static float DEFENCE = 0;
    public final static float HEALTH = 0;
    public final static float SPEED = 0;

    @Override
    public float getPrice() {
        return PRICE;
    }
    @Override
    public float getAttack(){
        return ATTACK;
    }
    @Override
    public float getDefence(){
        return DEFENCE;
    }
    @Override
    public float getHealth(){
        return HEALTH;
    }
    @Override
    public float getSpeed(){
        return SPEED;
    }
}
