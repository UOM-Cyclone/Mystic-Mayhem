package MysticMayhem;

import java.util.Scanner;

import MysticMayhem.Characters.Shooter;
import MysticMayhem.Controllers.GameController;




public class Main {
    public static void main(String[] args) {
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
    }


}
