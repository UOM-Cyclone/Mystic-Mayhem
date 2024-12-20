package MysticMayhem;

import java.util.Scanner;

import MysticMayhem.Controllers.GameController;


public class Main {
    public static void main(String[] args) {
                System.out.println(ColoredText.RED+"\n" +
                "  __  __           _   _         __  __             _                    \n" +
                " |  \\/  |         | | (_)       |  \\/  |           | |                   \n" +
                " | \\  / |_   _ ___| |_ _  ___   | \\  / | __ _ _   _| |__   ___ _ __ ___  \n" +
                " | |\\/| | | | / __| __| |/ __|  | |\\/| |/ _` | | | | '_ \\ / _ \\ '_ ` _ \\ \n" +
                " | |  | | |_| \\__ \\ |_| | (__   | |  | | (_| | |_| | | | |  __/ | | | | |\n" +
                " |_|  |_|\\__, |___/\\__|_|\\___|  |_|  |_|\\__,_|\\__, |_| |_|\\___|_| |_| |_|\n" +
                "          __/ |                               __/ |                     \n" +
                "         |___/                               |___/                      \n" + ColoredText.RESET);

        Scanner stdin = new Scanner(System.in);
        GameController.setInput(stdin);
        GameController.start();
        stdin.close();

    }


}
