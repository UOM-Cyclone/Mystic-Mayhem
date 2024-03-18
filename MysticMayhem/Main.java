package MysticMayhem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.Enchanter;
import MysticMayhem.Characters.Highlander;
import MysticMayhem.Characters.Shooter;
import MysticMayhem.Characters.Squire;
import MysticMayhem.Characters.Zoro;

import MysticMayhem.Characters.*;


import MysticMayhem.Controllers.Navigator;
import MysticMayhem.Equipments.*;
import MysticMayhem.Grounds.Arcane;
import MysticMayhem.Grounds.Desert;
import MysticMayhem.Grounds.Hillcrest;
import MysticMayhem.Grounds.Marshland;


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



        Navigator nav = new Navigator();
        nav.start();

    }


}
