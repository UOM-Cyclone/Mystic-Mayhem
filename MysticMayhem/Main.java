package MysticMayhem;

import java.util.Scanner;

import MysticMayhem.Characters.Shooter;
import MysticMayhem.UIs.CLI;
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
    }
}
