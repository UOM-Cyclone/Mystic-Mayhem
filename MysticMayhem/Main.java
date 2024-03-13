package MysticMayhem;

import java.util.Scanner;

import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.Enchanter;
import MysticMayhem.Characters.Highlander;
import MysticMayhem.Characters.Shooter;
import MysticMayhem.Characters.Squire;
import MysticMayhem.Characters.Zoro;
import MysticMayhem.Controllers.GameController;
import MysticMayhem.Grounds.Arcane;
import MysticMayhem.Grounds.Hillcrest;


public class Main {
    public static void main(String[] args) {
       Scanner stdin = new Scanner(System.in);
       GameController.setInput(stdin);
       GameController.start();

    // System.out.println(String.valueOf(Shooter.class).substring(30));


        // System.out.println("test");

        // Shooter obj= new Shooter();

        // System.out.println(obj.defence(3));
//        stdin.close();
        // System.out.println(obj.defence(3));


        // Profile shanil = new Profile("shanil123");
        // Profile thumul = new Profile("thumul456");
        // Profile devinda = new Profile("devinda789");
        // Profile dinara = new Profile("dinara000");

        // System.out.println(Profile.getRandomProfile().username);

        //
//        System.out.println(army1.toBattle(new Arcane()));
    }


}
