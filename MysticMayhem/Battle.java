package MysticMayhem;

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
    private Character findHighestSpeed(Vector<Character> aliveList){
        int size = aliveList.size();
        Character highestSpeed = null;
        if(size>0){
            highestSpeed = aliveList.get(0);
            for (int i = 0; i < size; i++){
                if(highestSpeed.getSpeed() < aliveList.get(i).getSpeed()){
                    highestSpeed = aliveList.get(i);
                }
            }
        }
        return highestSpeed;
    }
    public void start(Player attacker, Player defender){
        RoundSummery roundSummery;
        Character attackingChar;
        setBattleGround(defender.getHomeGround());
        attackerAlive = attacker.getArmy().toBattle(battleGround);
        defenderAlive = defender.getArmy().toBattle(battleGround);

        for (int i = 0; i < ROUNDS; i++){
            //(i+1) th round attacker attacking to defender
            roundSummery = new RoundSummery(i+1,attacker);
            attackingChar = findHighestSpeed(attackerAlive);
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
            attackingChar =findHighestSpeed(defenderAlive);
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
