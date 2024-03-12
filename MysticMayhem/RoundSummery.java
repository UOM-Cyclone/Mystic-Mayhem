package MysticMayhem;

import MysticMayhem.Characters.Character;

public class RoundSummery {
    private final String attackPlayer;
    private final int roundNo;

    //normal round
    private String attackChar;
    private String defendChar;
    private float attackerHealth;
    private float defenderHealth;

    //bonus round
    private boolean isBonusRound = false;
    private String bonusAttackChar;
    private String bonusDefendChar;
    private float bonusAttackerHealth;
    private float bonusDefenderHealth;

    public RoundSummery(int roundNo, Player attacker){
        this.roundNo = roundNo;
        this.attackPlayer = String.valueOf(attacker.getClass());
    }

    public void setAttackChar(Character attacker){
        this.attackChar = String.valueOf(attacker.getClass());
    }
    public void setDefendChar(Character defender){
        this.defendChar = String.valueOf(defender.getClass());
    }

    public void setAttackerHealth(float health){
        this.attackerHealth = health;
    }
    public void setDefenderHealth(float health){
        this.defenderHealth = health;
    }


    public void setBonusRound(boolean bonusRound){
        this.isBonusRound = bonusRound;
    }
    public void setBonusAttackChar(Character attacker){
        this.bonusAttackChar = String.valueOf(attacker.getClass());
    }
    public void setBonusDefendChar(Character defender){
        this.bonusDefendChar = String.valueOf(defender.getClass());
    }

    public void setBonusAttackerHealth(float health){
        this.bonusAttackerHealth = health;
    }
    public void setBonusDefenderHealth(float health){
        this.bonusDefenderHealth = health;
    }
    public void printSummery() {
        if (isBonusRound) {
            String summery =
                    roundNo +  "   Bonus Round " + "\n" +
                            attackChar + " Attacks " + defendChar + "\n" +
                            defendChar + " new health : " + defenderHealth + "\n" +
                            attackChar + "new health : " + attackerHealth + "\n";

            if (defenderHealth == 0) {
                summery += defendChar + "Died !";
            }
        }
        else{
            String summery =
                    roundNo  + "\n" +
                            attackChar + " Attacks " + defendChar + "\n" +
                            defendChar + " new health : " + defenderHealth + "\n" +
                            attackChar + "new health : " + attackerHealth + "\n";

            if (defenderHealth == 0) {
                summery += defendChar + "Died !";
            }
        }
    }

}
