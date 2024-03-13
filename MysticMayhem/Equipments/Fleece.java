package MysticMayhem.Equipments;

public class Fleece implements Armour{

    public final static float PRICE = 150;
    public final static float ATTACK = 0;
    public final static float DEFENCE = 2;
    public final static float HEALTH = 1;
    public final static float SPEED = -1;

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
