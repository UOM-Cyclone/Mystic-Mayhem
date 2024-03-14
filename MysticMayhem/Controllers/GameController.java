package MysticMayhem.Controllers;

import java.util.*;

import MysticMayhem.Player;
import MysticMayhem.Characters.Character;
import MysticMayhem.Characters.*;
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

    private static void viewArchersToBuy() {
        print("Select Archer to buy");
        print("--------------------\n");

        print("1. Shooter");
        print("\tType : " + Shooter.type);
        print("\tAttack : " + Shooter.initialAttack);
        print("\tDefence : " + Shooter.initialDefence);
        print("\tHealth : " + Shooter.initialHealth);
        print("\tSpeed : " + Shooter.initialSpeed);
        print("\tPrice : " + Shooter.price + "\n");

        print("2. Ranger");
        print("\tType : " + Ranger.type);
        print("\tAttack : " + Ranger.initialAttack);
        print("\tDefence : " + Ranger.initialDefence);
        print("\tHealth : " + Ranger.initialHealth);
        print("\tSpeed : " + Ranger.initialSpeed);
        print("\tPrice : " + Ranger.price + "\n");

        print("3. Sunfire");
        print("\tType : " + Sunfire.type);
        print("\tAttack : " + Sunfire.initialAttack);
        print("\tDefence : " + Sunfire.initialDefence);
        print("\tHealth : " + Sunfire.initialHealth);
        print("\tSpeed : " + Sunfire.initialSpeed);
        print("\tPrice : " + Sunfire.price + "\n");

        print("4. Zing");
        print("\tType : " + Zing.type);
        print("\tAttack : " + Zing.initialAttack);
        print("\tDefence : " + Zing.initialDefence);
        print("\tHealth : " + Zing.initialHealth);
        print("\tSpeed : " + Zing.initialSpeed);
        print("\tPrice : " + Zing.price + "\n");

        print("5. Saggitarius");
        print("\tType : " + Saggitarius.type);
        print("\tAttack : " + Saggitarius.initialAttack);
        print("\tDefence : " + Saggitarius.initialDefence);
        print("\tHealth : " + Saggitarius.initialHealth);
        print("\tSpeed : " + Saggitarius.initialSpeed);
        print("\tPrice : " + Saggitarius.price + "\n");

        print("-------------------");
        switch (stdin.nextLine()) {
            case "1":
                currentPlayer.addArcher(new Shooter());
                print("Successfully added a Shooter to the Barrack");
                break;
            case "2":
                currentPlayer.addArcher(new Ranger());
                print("Successfully added a Ranger to the Barrack");
                break;
            case "3":
                currentPlayer.addArcher(new Sunfire());
                print("Successfully added a Sunfire to the Barrack");
                break;
            case "4":
                currentPlayer.addArcher(new Zing());
                print("Successfully added a Zing to the Barrack");
                break;
            case "5":
                currentPlayer.addArcher(new Saggitarius());
                print("Successfully added a Saggitarius to the Barrack");
                break;
            case "98":
                charactersShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
                viewArchersToBuy();
        }
    }

    private static void viewKnightsToBuy() {
        print("Select Knight to buy");
        print("--------------------\n");

        print("1. Squire");
        print("\tType    : " + Squire.type);
        print("\tAttack  : " + Squire.initialAttack);
        print("\tDefence : " + Squire.initialDefence);
        print("\tHealth  : " + Squire.initialHealth);
        print("\tSpeed   : " + Squire.initialSpeed);
        print("\tPrice   : " + Squire.price + "\n");

        print("2. Cavalier");
        print("\tType    : " + Cavalier.type);
        print("\tAttack  : " + Cavalier.initialAttack);
        print("\tDefence : " + Cavalier.initialDefence);
        print("\tHealth  : " + Cavalier.initialHealth);
        print("\tSpeed   : " + Cavalier.initialSpeed);
        print("\tPrice   : " + Cavalier.price + "\n");

        print("3. Templar");
        print("\tType    : " + Templar.type);
        print("\tAttack  : " + Templar.initialAttack);
        print("\tDefence : " + Templar.initialDefence);
        print("\tHealth  : " + Templar.initialHealth);
        print("\tSpeed   : " + Templar.initialSpeed);
        print("\tPrice   : " + Templar.price + "\n");

        print("4. Zoro");
        print("\tType    : " + Zoro.type);
        print("\tAttack  : " + Zoro.initialAttack);
        print("\tDefence : " + Zoro.initialDefence);
        print("\tHealth  : " + Zoro.initialHealth);
        print("\tSpeed   : " + Zoro.initialSpeed);
        print("\tPrice   : " + Zoro.price + "\n");

        print("5. Swiftblade");
        print("\tType    : " + Swiftblade.type);
        print("\tAttack  : " + Swiftblade.initialAttack);
        print("\tDefence : " + Swiftblade.initialDefence);
        print("\tHealth  : " + Swiftblade.initialHealth);
        print("\tSpeed   : " + Swiftblade.initialSpeed);
        print("\tPrice   : " + Swiftblade.price + "\n");
        print("-------------------");
        switch (stdin.nextLine()) {
            case "1":
                currentPlayer.addKnight(new Squire());
                print("Successfully added a Squire to the Barrack");
                break;
            case "2":
                currentPlayer.addKnight(new Cavalier());
                print("Successfully added a Cavalier to the Barrack");
                break;
            case "3":
                currentPlayer.addKnight(new Templar());
                print("Successfully added a Templar to the Barrack");
                break;
            case "4":
                currentPlayer.addKnight(new Zoro());
                print("Successfully added a Zoro to the Barrack");
                break;
            case "5":
                currentPlayer.addKnight(new Swiftblade());
                print("Successfully added a Swiftblade to the Barrack");
                break;
            case "98":
                charactersShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
                viewKnightsToBuy();
        }
    }

    private static void viewMagesToBuy() {
        print("Select Mage to buy");
        print("--------------------\n");

        print("1. Warlock");
        print("\tType    : " + Warlock.type);
        print("\tAttack  : " + Warlock.initialAttack);
        print("\tDefence : " + Warlock.initialDefence);
        print("\tHealth  : " + Warlock.initialHealth);
        print("\tSpeed   : " + Warlock.initialSpeed);
        print("\tPrice   : " + Warlock.price + "\n");

        print("2. Illusionist");
        print("\tType    : " + Illusionist.type);
        print("\tAttack  : " + Illusionist.initialAttack);
        print("\tDefence : " + Illusionist.initialDefence);
        print("\tHealth  : " + Illusionist.initialHealth);
        print("\tSpeed   : " + Illusionist.initialSpeed);
        print("\tPrice   : " + Illusionist.price + "\n");

        print("3. Enchanter");
        print("\tType    : " + Enchanter.type);
        print("\tAttack  : " + Enchanter.initialAttack);
        print("\tDefence : " + Enchanter.initialDefence);
        print("\tHealth  : " + Enchanter.initialHealth);
        print("\tSpeed   : " + Enchanter.initialSpeed);
        print("\tPrice   : " + Enchanter.price + "\n");

        print("4. Conjurer");
        print("\tType    : " + Conjurer.type);
        print("\tAttack  : " + Conjurer.initialAttack);
        print("\tDefence : " + Conjurer.initialDefence);
        print("\tHealth  : " + Conjurer.initialHealth);
        print("\tSpeed   : " + Conjurer.initialSpeed);
        print("\tPrice   : " + Conjurer.price + "\n");

        print("5. Eldritch");
        print("\tType    : " + Eldritch.type);
        print("\tAttack  : " + Eldritch.initialAttack);
        print("\tDefence : " + Eldritch.initialDefence);
        print("\tHealth  : " + Eldritch.initialHealth);
        print("\tSpeed   : " + Eldritch.initialSpeed);
        print("\tPrice   : " + Eldritch.price + "\n");
        print("-------------------");
        switch (stdin.nextLine()) {
            case "1":
                currentPlayer.addMage(new Warlock());
                print("Successfully added a Warlock to the Barrack");
                break;
            case "2":
                currentPlayer.addMage(new Illusionist());
                print("Successfully added a Illusionist to the Barrack");
                break;
            case "3":
                currentPlayer.addMage(new Enchanter());
                print("Successfully added a Enchanter to the Barrack");
                break;
            case "4":
                currentPlayer.addMage(new Conjurer());
                print("Successfully added a Conjurer to the Barrack");
                break;
            case "5":
                currentPlayer.addMage(new Eldritch());
                print("Successfully added a Eldritch to the Barrack");
                break;
            case "98":
                charactersShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
                viewMagesToBuy();
        }
    }

    private static void viewHealersToBuy() {
        print("Select Healer to buy");
        print("--------------------\n");

        print("1. Soother");
        print("\tType    : " + Soother.type);
        print("\tAttack  : " + Soother.initialAttack);
        print("\tDefence : " + Soother.initialDefence);
        print("\tHealth  : " + Soother.initialHealth);
        print("\tSpeed   : " + Soother.initialSpeed);
        print("\tPrice   : " + Soother.price + "\n");

        print("2. Medic");
        print("\tType    : " + Medic.type);
        print("\tAttack  : " + Medic.initialAttack);
        print("\tDefence : " + Medic.initialDefence);
        print("\tHealth  : " + Medic.initialHealth);
        print("\tSpeed   : " + Medic.initialSpeed);
        print("\tPrice   : " + Medic.price + "\n");

        print("3. Alchemist");
        print("\tType    : " + Alchemist.type);
        print("\tAttack  : " + Alchemist.initialAttack);
        print("\tDefence : " + Alchemist.initialDefence);
        print("\tHealth  : " + Alchemist.initialHealth);
        print("\tSpeed   : " + Alchemist.initialSpeed);
        print("\tPrice   : " + Alchemist.price + "\n");

        print("4. Saint");
        print("\tType    : " + Saint.type);
        print("\tAttack  : " + Saint.initialAttack);
        print("\tDefence : " + Saint.initialDefence);
        print("\tHealth  : " + Saint.initialHealth);
        print("\tSpeed   : " + Saint.initialSpeed);
        print("\tPrice   : " + Saint.price + "\n");

        print("5. Lightbringer");
        print("\tType    : " + Lightbringer.type);
        print("\tAttack  : " + Lightbringer.initialAttack);
        print("\tDefence : " + Lightbringer.initialDefence);
        print("\tHealth  : " + Lightbringer.initialHealth);
        print("\tSpeed   : " + Lightbringer.initialSpeed);
        print("\tPrice   : " + Lightbringer.price + "\n");
        print("-------------------");
        switch (stdin.nextLine()) {
            case "1":
                currentPlayer.addHealer(new Soother());
                print("Successfully added a Soother to the Barrack");
                break;
            case "2":
                currentPlayer.addHealer(new Medic());
                print("Successfully added a Medic to the Barrack");
                break;
            case "3":
                currentPlayer.addHealer(new Alchemist());
                print("Successfully added a Alchemist to the Barrack");
                break;
            case "4":
                currentPlayer.addHealer(new Saint());
                print("Successfully added a Saint to the Barrack");
                break;
            case "5":
                currentPlayer.addHealer(new Lightbringer());
                print("Successfully added a Lightbringer to the Barrack");
                break;
            case "98":
                charactersShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
                viewHealersToBuy();
        }
    }

    private static void viewMythicalCreaturesToBuy() {
        print("Select MythicalCreature to buy");
        print("--------------------\n");

        print("1. Dragon");
        print("\tType    : " + Dragon.type);
        print("\tAttack  : " + Dragon.initialAttack);
        print("\tDefence : " + Dragon.initialDefence);
        print("\tHealth  : " + Dragon.initialHealth);
        print("\tSpeed   : " + Dragon.initialSpeed);
        print("\tPrice   : " + Dragon.price + "\n");

        print("2. Basilisk");
        print("\tType    : " + Basilisk.type);
        print("\tAttack  : " + Basilisk.initialAttack);
        print("\tDefence : " + Basilisk.initialDefence);
        print("\tHealth  : " + Basilisk.initialHealth);
        print("\tSpeed   : " + Basilisk.initialSpeed);
        print("\tPrice   : " + Basilisk.price + "\n");

        print("3. Hydra");
        print("\tType    : " + Hydra.type);
        print("\tAttack  : " + Hydra.initialAttack);
        print("\tDefence : " + Hydra.initialDefence);
        print("\tHealth  : " + Hydra.initialHealth);
        print("\tSpeed   : " + Hydra.initialSpeed);
        print("\tPrice   : " + Hydra.price + "\n");

        print("4. Phoenix");
        print("\tType    : " + Phoenix.type);
        print("\tAttack  : " + Phoenix.initialAttack);
        print("\tDefence : " + Phoenix.initialDefence);
        print("\tHealth  : " + Phoenix.initialHealth);
        print("\tSpeed   : " + Phoenix.initialSpeed);
        print("\tPrice   : " + Phoenix.price + "\n");

        print("5. Pegasus");
        print("\tType    : " + Pegasus.type);
        print("\tAttack  : " + Pegasus.initialAttack);
        print("\tDefence : " + Pegasus.initialDefence);
        print("\tHealth  : " + Pegasus.initialHealth);
        print("\tSpeed   : " + Pegasus.initialSpeed);
        print("\tPrice   : " + Pegasus.price + "\n");
        print("-------------------");
        switch (stdin.nextLine()) {
            case "1":
                currentPlayer.addMythicalCreature(new Dragon());
                print("Successfully added a Dragon to the Barrack");
                break;
            case "2":
                currentPlayer.addMythicalCreature(new Basilisk());
                print("Successfully added a Basilisk to the Barrack");
                break;
            case "3":
                currentPlayer.addMythicalCreature(new Hydra());
                print("Successfully added a Hydra to the Barrack");
                break;
            case "4":
                currentPlayer.addMythicalCreature(new Phoenix());
                print("Successfully added a Phoenix to the Barrack");
                break;
            case "5":
                currentPlayer.addMythicalCreature(new Pegasus());
                print("Successfully added a Pegasus to the Barrack");
                break;
            case "98":
                charactersShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
                viewMythicalCreaturesToBuy();
        }
    }

    private static void viewCharacterStats(Character chrtr) {
        print("**" + String.valueOf(chrtr.getClass()).substring(30) + "**");
        print("\tType : " + chrtr.getClass().type);
        print("\tAttack : " + chrtr.getAttack());
        print("\tDefence : " + chrtr.getDefense());
        print("\tHealth : " + chrtr.getHealth());
        print("\tSpeed : " + chrtr.getSpeed());
        print("\tCurrent Value : " + chrtr.getCurrentValue());
        print("\tArmor : " + String.valueOf(chrtr.getArmour().getClass()).substring(30));
        print("\tArtifact : " + String.valueOf(chrtr.getArtefact().getClass()).substring(30) + "\n");
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
                deckUI();
                break;
            case "3":
                equipmentsUI();
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
                barrackUI();
                break;
            case "2":
                charactersShopUI();
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
        switch (selectCategoryTo("view and modify")) {
            case "1":
                viewCharacterStats(currentPlayer.getArmy().getArcher());
                break;

            case "2":
                viewCharacterStats(currentPlayer.getArmy().getKnight());
                break;

            case "3":
                viewCharacterStats(currentPlayer.getArmy().getMage());
                break;

            case "4":
                viewCharacterStats(currentPlayer.getArmy().getHealer());
                break;

            case "5":
                viewCharacterStats(currentPlayer.getArmy().getMythicalCreature());
                break;

            case "98":
                armyUI();
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

    private static void charactersShopUI() {
        switch (selectCategoryTo("view")) {
            case "1":
                viewArchersToBuy();
                break;

            case "2":
                viewKnightsToBuy();
                ;
                break;

            case "3":
                viewMagesToBuy();
                break;

            case "4":
                viewHealersToBuy();
                break;

            case "5":
                viewMythicalCreaturesToBuy();
                break;

            case "98":
                soldiersUI();
                break;
            default:
                break;
        }
    }

    private static void equipmentsUI() {

    }

}
