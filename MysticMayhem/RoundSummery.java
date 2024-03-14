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
        this.attackPlayer = attacker.getName();
    }

    public void setAttackChar(Character attacker){
        this.attackChar = String.valueOf(attacker.getClass()).substring(30);
    }
    public void setDefendChar(Character defender){
        this.defendChar = String.valueOf(defender.getClass()).substring(30);
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
        this.bonusDefendChar = String.valueOf(defender.getClass()).substring(30);
    }

    public void setBonusAttackerHealth(float health){
        this.bonusAttackerHealth = health;
    }
    public void setBonusDefenderHealth(float health){
        this.bonusDefenderHealth = health;
    }
    public void printSummery(){
        String summery = String.format("""
                Turn %d : %s 
                %s attacks %s
                %s's health : %.1f
                %s's health : %.1f              
                """,
                roundNo, attackPlayer,
                attackChar, defendChar,
                defendChar, defenderHealth,
                attackChar, attackerHealth);
        if(defenderHealth == 0 ){
            summery += String.format("\n%s Died !\n\n",defendChar);
        }

//        System.out.println(isBonusRound);

        if(isBonusRound){
            summery += String.format("""
                Turn %d (bonus round) : %s 
                %s attacks %s
                %s's health : %.1f
                %s's health : %.1f
                """,
                    roundNo, attackPlayer,
                    attackChar, bonusDefendChar,
                    bonusDefendChar, bonusDefenderHealth,
                    attackChar, attackerHealth);
            if(bonusDefenderHealth == 0 ){
                summery += String.format("\n%s Died !\n\n",defendChar);
            }
        }
        System.out.print(summery);
    }

}
