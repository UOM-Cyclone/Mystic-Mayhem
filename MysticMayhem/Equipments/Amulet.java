package MysticMayhem.Equipments;

public class Amulet implements Artefacts{

    public final static float PRICE = 200;
    public final static float ATTACK = 1;
    public final static float DEFENCE = -1;
    public final static float HEALTH = 1;
    public final static float SPEED = 1;

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
