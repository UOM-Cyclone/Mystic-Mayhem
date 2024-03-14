package MysticMayhem;

import java.util.Scanner;

import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.Enchanter;
import MysticMayhem.Characters.Highlander;
import MysticMayhem.Characters.Shooter;
import MysticMayhem.Characters.Squire;
import MysticMayhem.Characters.Zoro;

import MysticMayhem.Characters.*;


import MysticMayhem.Controllers.GameController;
import MysticMayhem.Grounds.Arcane;
import MysticMayhem.Grounds.Hillcrest;


public class Main {
    public static void main(String[] args) {
        System.out.println(ColoredText.RED+"\n" +
                "  __  __           _   _        __  __             _                    \n" +
                " |  \\/  |         | | (_)      |  \\/  |           | |                   \n" +
                " | \\  / |_   _ ___| |_ _  ___  | \\  / | __ _ _   _| |__   ___ _ __ ___  \n" +
                " | |\\/| | | | / __| __| |/ __| | |\\/| |/ _` | | | | '_ \\ / _ \\ '_ ` _ \\ \n" +
                " | |  | | |_| \\__ \\ |_| | (__  | |  | | (_| | |_| | | | |  __/ | | | | |\n" +
                " |_|  |_|\\__, |___/\\__|_|\\___| |_|  |_|\\__,_|\\__, |_| |_|\\___|_| |_| |_|\n" +
                "          __/ |                               __/ |                     \n" +
                "         |___/                               |___/                      \n" + ColoredText.RESET);
        Scanner stdin = new Scanner(System.in);
        GameController.setInput(stdin);
        GameController.start();

        // System.out.println("test");

        // Shooter obj= new Shooter();

        // System.out.println(obj.defence(3));
        stdin.close();
        // System.out.println(obj.defence(3));


        // Profile shanil = new Profile("shanil123");
        // Profile thumul = new Profile("thumul456");
        // Profile devinda = new Profile("devinda789");
        // Profile dinara = new Profile("dinara000");

        // System.out.println(Profile.getRandomProfile().username);

//        Army army1 = new Army();
//        Army army2 = new Army();
//
//        army1.addArcher(new Shooter());
//        army1.addKnight(new Zoro());
//        army1.addMage(new Enchanter());
//        army1.addHealer(new Medic());
//        army1.addMythicalCreature(new Pegasus());
//
//        army2.addKnight(new Cavalier());
//        army2.addMage(new Warlock());
//        army2.addArcher(new Sunfire());
//        army2.addHealer(new Lightbringer());
//        army2.addMythicalCreature(new Basilisk());
//
//        Player player1 = new Player("dev","dev","dev");
//        Player player2 = new Player("shan","shan","shan");
//
//        player1.setArmy(army1);
//        player2.setArmy(army2);
//
//        player1.setHomeGround(new Hillcrest());
//        player2.setHomeGround(new Hillcrest());
//
//        Battle battle1 = new Battle();
//
//        battle1.start(player1, player2);
//        System.out.println((new Lightbringer()) instanceof Healer);


        //
//        System.out.println(army1.toBattle(new Arcane()));
//
//        Army army1 = new Army();
//        Army army2 = new Army();
//
//        army1.addArcher(new Shooter());
//        army1.addKnight(new Zoro());
//        army1.addMage(new Enchanter());
//        army1.addHealer(new Medic());
//        army1.addMythicalCreature(new Pegasus());
//
//        army2.addKnight(new Cavalier());
//        army2.addMage(new Warlock());
//        army2.addArcher(new Sunfire());
//        army2.addHealer(new Lightbringer());
//        army2.addMythicalCreature(new Basilisk());

//        Player player1 = new Player("dev","dev","dev");
////        Player player2 = new Player("shan","shan","shan");
//
//        player1.setArmy(army1);
//        player2.setArmy(army2);
//
//        player1.setHomeGround(new Hillcrest());
//        player2.setHomeGround(new Hillcrest());
//
//        Battle battle1 = new Battle();
//
//        battle1.start(player1, player2);
//        System.out.println((new Lightbringer()) instanceof Healer);

    }


}
