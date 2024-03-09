package MysticMayhem.Equipments;

public class Excalibur implements Artefacts{

    final static float PRICE = 150;
    final static float ATTACK = 2;
    final static float DEFENCE = 0;
    final static float HEALTH = 0;
    final static float SPEED = 0;

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
