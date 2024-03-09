package MysticMayhem.Equipments;

public class Amulet implements Artefacts{

    final static float PRICE = 200;
    final static float ATTACK = 1;
    final static float DEFENCE = -1;
    final static float HEALTH = 1;
    final static float SPEED = 1;

    @Override
    public float getPrice() {
        return this.PRICE;
    }
    @Override
    public float getAttack(){
        return this.ATTACK;
    }
    @Override
    public float getDefence(){
        return this.DEFENCE;
    }
    @Override
    public float getHealth(){
        return this.HEALTH;
    }
    @Override
    public float getSpeed(){
        return this.SPEED;
    }
}
