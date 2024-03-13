package MysticMayhem;

import MysticMayhem.Characters.*;
import MysticMayhem.Characters.Character;
import MysticMayhem.Grounds.Ground;

import java.util.Vector;

public class Battle {
    private Player attacker;
    private Player defender;
    private Player winner;
    private Player looser;
    private final static int ROUNDS = 10;
    private Ground battleGround;
    private Vector<Character> attackerAlive;
    private Vector<Character> attackerDead;
    private Vector<Character> defenderAlive;
    private Vector<Character> defenderDead;

    private void setAttacker(Player attacker){
        this.attacker = attacker;
    }
    private void setWinner(Player winner){this.winner = winner;}
    private void setDefender(Player defender){
        this.defender = defender;
    }
    private void setBattleGround(Ground battleGround){
        this.battleGround = battleGround;
    }
    private Vector<Character> generateAttackerQue(Vector<Character> aliveList){

        Vector<Character> result = new Vector<>();
        float highestSpeed ;
        int start, end;
        while (!aliveList.isEmpty()){
            start = result.size();
            highestSpeed = aliveList.getFirst().getSpeed();
            for (int i = 0; i < aliveList.size(); i++){
                if(highestSpeed < aliveList.get(i).getSpeed()){
                    highestSpeed = aliveList.get(i).getSpeed();
                }
            }


            for (int i = 0; i < aliveList.size(); i++){
                if(highestSpeed == aliveList.get(i).getSpeed()){
                    result.add(aliveList.get(i));
//                    aliveList.remove(aliveList.get(i));
                }
            }

            for(int i = start; i < result.size(); i++){
                aliveList.remove(result.get(i));
            }
            end = result.size() -1;

            sortByPriority(result,start,end);
        }

        return result;
    }

    private void sortByPriority(Vector<Character> arr,int start, int end){
        Vector<Character> temp = new Vector<>();

        for (int i = start; i <= end; i++){
            if(arr.get(i) instanceof Archer){
                temp.add(arr.get(i));
            }
        }

        for (int i = start; i <= end; i++){
            if(arr.get(i) instanceof Knight){
                temp.add(arr.get(i));
            }
        }
        for (int i = start; i <= end; i++){
            if(arr.get(i) instanceof MythicalCreature){
                temp.add(arr.get(i));
            }
        }
        for (int i = start; i <= end; i++){
            if(arr.get(i) instanceof Mage){
                temp.add(arr.get(i));
            }
        }
        for (int i = start; i <= end; i++){
            if(arr.get(i) instanceof Healer){
                temp.add(arr.get(i));
            }
        }
        for (int i = start; i <= end; i++){
            arr.set(i,temp.get(i-start));
        }

    }

    public void start(Player attacker, Player defender){
        RoundSummery roundSummery;
        Character attackingChar;
        setBattleGround(defender.getHomeGround());
        attackerAlive = attacker.getArmy().toBattle(battleGround);
        defenderAlive = defender.getArmy().toBattle(battleGround);
        attackerAlive = generateAttackerQue(attackerAlive);
        defenderAlive = generateAttackerQue(defenderAlive);

        for (int i = 0; i < ROUNDS; i++){
            //(i+1) th round attacker attacking to defender
            roundSummery = new RoundSummery(i+1,attacker);
            attackingChar = attackerAlive.get(0);
            attackerAlive.remove(attackingChar);
            attackerAlive.add(attackingChar);
            attackingChar.attack(attackerAlive, defenderAlive, roundSummery);
            roundSummery.setAttackChar(attackingChar);
            roundSummery.setAttackerHealth(attackingChar.getHealth());
            if(defenderAlive.size() == 0){
                setWinner(attacker);
                break;
            }

            roundSummery.printSummery();

            //(i+1) th round defender attacking to attacker
            roundSummery = new RoundSummery(i+1,defender);
            attackingChar = defenderAlive.get(0);
            defenderAlive.remove(attackingChar);
            defenderAlive.add(attackingChar);
            attackingChar.attack(defenderAlive,attackerAlive, roundSummery);
            roundSummery.setAttackChar(attackingChar);
            roundSummery.setAttackerHealth(attackingChar.getHealth());
            roundSummery.printSummery();
            if(attackerAlive.size() == 0){
                setWinner(defender);
                break;
            }
        }

        if (winner != null){
            if(winner == attacker){ //do battle final things if attacker win
                System.out.println("attacker");
            } else {//do battle final things if defender win
                System.out.println("defender");
            }
        } else {//do battle final things if battle draw
            System.out.println("draw");
        }

    }

}
