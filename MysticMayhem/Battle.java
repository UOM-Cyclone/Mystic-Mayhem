package MysticMayhem;

import MysticMayhem.Characters.*;
import MysticMayhem.Characters.Character;
import MysticMayhem.Grounds.Ground;

import java.util.Vector;


class ColoredText {
    // Define ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
}

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
        System.out.println(ColoredText.GREEN + "||  " + attacker.getName() + " VS " + defender.getName() + "  ||" + ColoredText.RESET + "\n" + "BattleGround : " + (String.valueOf(defender.getHomeGround().getClass()).substring(27))+"\n");

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
            System.out.println("\n");

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
            System.out.println("====================================="+"\n");
        }

        if (winner != null){
            if(winner == attacker){//do battle final things if attacker win
                attacker.increaseXP(+1);
                attacker.changeGC((int) (0.1*defender.getGC()));
                defender.changeGC((int) (-0.1*defender.getGC()));
                System.out.println(ColoredText.PURPLE + "Congratulations "+ attacker.getName() + " !!\n" + "You Won The Battle " +"\n"+ "XP : " +attacker.getXP() +"\n"+ "Gold Coins : "+attacker.getGC() + ColoredText.RESET);
            } else {//do battle final things if defender win
                defender.increaseXP(+1);
                defender.changeGC((int) (0.1*attacker.getGC()));
                attacker.changeGC((int) (-0.1*attacker.getGC()));
                System.out.println(ColoredText.PURPLE + "Congratulations "+ defender.getName() + " !!\n" + "You Won The Battle " + "\n"+ "XP : " +defender.getXP() + "\n"+ "Gold Coins : "+defender.getGC() + ColoredText.RESET);
            }
        } else {//do battle final things if battle draw
            System.out.println(ColoredText.YELLOW + "Draw !!" +"\n"+"Great Battle " + ColoredText.RESET);
            for(Character f : attackerAlive){
                System.out.println(f);
            }
            for(Character f : defenderAlive){
                System.out.println(f);
            }
        }

    }

}
