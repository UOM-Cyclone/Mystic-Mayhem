package MysticMayhem.Equipments;

public class Chainmail implements Armour{

    final static float PRICE = 70;
    final static float ATTACK = 0;
    final static float DEFENCE = 1;
    final static float HEALTH = 0;
    final static float SPEED = -1;

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
