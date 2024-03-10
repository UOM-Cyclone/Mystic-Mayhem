package MysticMayhem;

import MysticMayhem.Characters.Archer;
import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.Shooter;
import MysticMayhem.Grounds.Ground;

public class Army {
    Archer archer = null;

    public void addArcher(Archer archer){

        this.archer = archer;
    }

//    public int toBattle(Ground ground){
//        Archer clonedArcher = cloneArcher(archer);
//        clonedArcher.addBattleGround(ground);
//        return 0;
//    }

//    private Archer cloneArcher(Archer archer){
//        Archer clonedArcher = null;
//        if(archer instanceof Shooter){
//            clonedArcher = new Shooter();
//            clonedArcher.copy(archer);
//        } else if(archer instanceof Ranger){
//            clonedArcher = new Ranger();
//            clonedArcher.copy(archer);
//        }else if(archer instanceof Sunfire){
//            clonedArcher = new Sunfire();
//            clonedArcher.copy(archer);
//        }else if(archer instanceof Zing){
//            clonedArcher = new Zing();
//            clonedArcher.copy(archer);
//        }else if(archer instanceof Saggitarius){
//            clonedArcher = new Saggitarius();
//            clonedArcher.copy(archer);
//        }
//        return clonedArcher;
//    }
}
