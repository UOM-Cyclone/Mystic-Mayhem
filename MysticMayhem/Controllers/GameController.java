package MysticMayhem.Controllers;

import java.util.*;

import MysticMayhem.Player;
import MysticMayhem.Characters.Character;
import MysticMayhem.UIs.CLIConsole;
import javafx.collections.ListChangeListener.Change;

public class GameController {
    static int UI_id_num;
    static Scanner stdin;

    private static ArrayList<String> plyrs = new ArrayList<String>();
    private static ArrayList<String> pwds = new ArrayList<String>();

    private static String inputStr = "";
    private static Player currentPlayer, opponentPlayer;

    static Player tempPlayer;

    private static void print(String phrase) {
        System.out.println(phrase);
    }

    public static void setInput(Scanner scnr) {
        stdin = scnr;
    }

    public static void setPlayers() {
        plyrs.add("player-1");
        plyrs.add("player-2");
        pwds.add("player-1");
        pwds.add("player-2");
        new Player("Thumul Dasun", "thumul", "1111");
        new Player("Devinda Dilshan", "devinda", "2111");
        new Player("Shanil Praveen", "shanil", "3111");
        new Player("Dinara de. Silva", "dinara", "4111");
    }

    // to check and get the index of user
    private static boolean isTakenUserName(String plyrName) {
        boolean taken = false;
        for (int i = 0; i < Player.getPlayerCount(); i++) {
            if (Player.getUserNames().get(i).equals(plyrName)) {
                taken = true;
                break;
            }
        }
        return taken;
    }

    public static boolean areYouSure(String msg) {
        boolean answer;
        print("Are you sure you want to " + msg + "? (Y/N)");
        inputStr = stdin.nextLine();
        answer = (inputStr.equals("Y") || inputStr.equals("y"));
        return answer;
    }

    // start the console
    public static void start() {
        setPlayers();
        inputStr = CLIConsole.display(stdin, "UI00");
        switch (inputStr) {
            case "1":
                LogIn();
                break;
            case "2":
                createAccount();
                break;
            case "99":
                quitGame();
                break;
            default:
                print("Nothing special");

        }
    }

    public static void quitGame() {
        if (areYouSure("quit the game")) {
            return;
        }
        start();
    }

    public static void LogIn() {
        while (true) {
            print("Enter your User Name");
            inputStr = stdin.nextLine();
            if (isTakenUserName(inputStr)) {
                tempPlayer = Player.getPlayers().get(inputStr);
                break;
            } else {
                print("Invalid User Name. Try it again.\n");
            }
        }

        while (true) {
            print("Enter your Password");
            inputStr = stdin.nextLine();

            if (inputStr.equals(tempPlayer.getPwd())) {
                currentPlayer = tempPlayer;
                break;
            } else {
                print("Incorrect Password. Try it again.");
            }
        }
        System.out.print("Loading player data [     ]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [#    ]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [##   ]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [###  ]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [#### ]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [#####]");
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            print(e.getMessage());
        }
        playerUI();
    }

    public static void createAccount() {
        String name, uName, pwd;
        print("Enter your name:");
        name = stdin.nextLine();
        while (true) {
            print("Enter your username:");
            inputStr = stdin.nextLine();
            if (!isTakenUserName(inputStr)) {
                uName = inputStr;
                break;
            } else {
                print("User Name already exists. Please use another username.\n");
            }
        }

        print("Enter a password for your account:");
        pwd = stdin.nextLine();
        new Player(name, uName, pwd);
        print("Successfully created a new account..");
        start();
    }

    public static void playerUI() {
        if (currentPlayer == null) {
            print("You should login first.");
            return;
        }

        print("\n" + currentPlayer.getName() + " @" + currentPlayer.getUserName());
        inputStr = CLIConsole.display(stdin, "UI10");

        switch (inputStr) {
            case "1":
                selectOpponentUI();
                break;
            case "2":
                viewProfile();
                break;
            case "3":
                armyUI();
                break;
            case "98":
                logOut();
                break;
            default:
                print("Invalid input. Try it again.");
                playerUI();

        }
    }

    public static void logOut() {
        if (areYouSure("logout")) {
            print("Saving data...");
            currentPlayer = null;
            start();
        } else {
            playerUI();
        }
    }

    private static void viewProfile() {
        print("-----My Profile-----");
        System.out.print("\tName : " + currentPlayer.getName());
        System.out.print("\n\tUsername : " + currentPlayer.getUserName());
        System.out.print("\n\tXP : " + currentPlayer.getXP());
        System.out.print("\n\tGold coins : " + currentPlayer.getGC());
        System.out.print("\n\tHomeground : " + currentPlayer.getHomeGround() + "\n");
        print("1. Change Name");
        print("2. Change Password");
        print("98. Back to Menu");
        print("--------------------");

        switch (stdin.nextLine()) {
            case "1":
                changeNameUI();
                break;
            case "98":
                playerUI();
                break;
            default:
                print("Invalid input. Try it again.");
                playerUI();
        }
    }

    private static void changeNameUI() {
        print("-----Change Name-----");
        print("Enter new name.\n(Enter 98 to go back)\n");
        String tempName = stdin.nextLine();
        switch (inputStr) {
            case "98":
                viewProfile();
                break;
            default:
                if (areYouSure("change name to \"" + tempName + "\"")) {
                    currentPlayer.changeName(tempName);
                    currentPlayer.updateHashMap();
                    print("Changed name successfully...");
                }
                viewProfile();
        }
    }

    public static void selectOpponentUI() {
        inputStr = "2";
        while (inputStr.equals("2")) {
            tempPlayer = Player.getRandomPlayer();
            if (tempPlayer == currentPlayer) {
                continue;
            }
            System.out.print("Select opponent to combat\n\t" + tempPlayer.getName() + "\n\t  -XP Level : "
                    + tempPlayer.getXP() + "\n1. Challenge\n2.Skip\n98.Back to menu\n");
            inputStr = stdin.nextLine();
        }

        switch (inputStr) {
            case "1":
                opponentPlayer = tempPlayer;
                // combat();
                break;
            case "98":
                playerUI();
                break;
            default:
                print("Invalid input. Try it again.");
                playerUI();
        }

    }

    public static void armyUI() {
        print("1. Soldiers");
        print("2. Battle Deck");
        print("3. Equipments");
        print("98. Back");

        switch (stdin.nextLine()) {
            case "1":
                soldiersUI();
                break;
            case "2":
                viewProfile();
                break;
            case "3":
                createAccount();
                break;
            case "98":
                playerUI();
                break;
            default:
                print("Invalid input. Try it again.");
                armyUI();
        }
    }

    public static void soldiersUI() {
        print("1. View Occupied Soldiers\n2. Buy Characters\n3. Sell Characters\n4. Upgrade Characters\n98. Back");
        switch (stdin.nextLine()) {
            case "1":
                soldiersUI();
                break;
            case "2":
                viewProfile();
                break;
            case "3":
                createAccount();
                break;
            case "4":
                createAccount();
                break;
            case "98":
                armyUI();
                break;
            default:
                print("Invalid input. Try it again.");
                soldiersUI();
        }
    }

    private static String selectCategoryTo(String msg) {
        print("Select category to " + msg);
        print("1. Archers\n2. Knights\n3. Mages\n4. Healers\n5. Mythical Creatures\n98. Back");
        return stdin.nextLine();
    }

    private static void viewCharacters(ArrayList<Character> list, String title) {
        print("-----" + title + "-----");
        for (int i = 0; i < list.size(); i++) {
            Character character = list.get(i);
        }
    }

    private static void barrackUI() {
        switch (selectCategoryTo("view")) {
            case "1":
                viewCharacters(currentPlayer.getArchers(), "Archers");
                break;

            case "2":
                viewCharacters(currentPlayer.getKnights(), "Knights");
                break;

            case "3":
                viewCharacters(currentPlayer.getMages(), "Mages");
                break;

            case "4":
                viewCharacters(currentPlayer.getHealers(), "Healers");
                break;

            case "5":
                viewCharacters(currentPlayer.getMythicalCreatures(), "Mythical creatures");
                break;

            case "98":
                soldiersUI();
                break;
            default:
                break;
        }
    }

    private static void deckUI() {
        // select the category
        Character tempCharacter;
        switch (selectCategoryTo("view")) {
            case "1":
            // tempCharacter = currentPlayer.getArmy
                viewCharacters(currentPlayer.getArchers(), "Archers");
                break;

            case "2":
                viewCharacters(currentPlayer.getKnights(), "Knights");
                break;

            case "3":
                viewCharacters(currentPlayer.getMages(), "Mages");
                break;

            case "4":
                viewCharacters(currentPlayer.getHealers(), "Healers");
                break;

            case "5":
                viewCharacters(currentPlayer.getMythicalCreatures(), "Mythical creatures");
                break;

            case "98":
                soldiersUI();
                break;
            default:
                break;
        }

        

        // display options
        // 1. Add/ Change
        // 2. Remove
        // 3. upgrade(Eq)
        // 98. Back
    }

    private static void EquipmentsUI(){
        
    }

}
