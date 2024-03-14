package MysticMayhem.Controllers;

import java.util.*;

import MysticMayhem.Army;
import MysticMayhem.Player;
import MysticMayhem.Characters.Character;
import MysticMayhem.Equipments.Amulet;
import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Equipments.Chainmail;
import MysticMayhem.Equipments.Crystal;
import MysticMayhem.Equipments.Equipment;
import MysticMayhem.Equipments.Excalibur;
import MysticMayhem.Equipments.Fleece;
import MysticMayhem.Equipments.Regalia;
import MysticMayhem.Grounds.Arcane;
import MysticMayhem.Grounds.Desert;
import MysticMayhem.Grounds.Ground;
import MysticMayhem.Grounds.Hillcrest;
import MysticMayhem.Grounds.Marshland;
import MysticMayhem.Characters.*;
import MysticMayhem.UIs.CLIConsole;

public class GameController {
    static int UI_id_num;
    static Scanner stdin;

    private static ArrayList<String> plyrs = new ArrayList<String>();
    private static ArrayList<String> pwds = new ArrayList<String>();

    private static String inputStr = "";
    private static Player currentPlayer, opponentPlayer;

    private static Character[][] charArr = {
            { new Shooter(), new Ranger(), new Sunfire(), new Zing(), new Saggitarius() },
            { new Squire(), new Cavalier(), new Templar(), new Zoro(), new Swiftblade() },
            { new Warlock(), new Illusionist(), new Enchanter(), new Conjurer(), new Eldritch() },
            { new Soother(), new Medic(), new Alchemist(), new Saint(), new Lightbringer() },
            { new Dragon(), new Basilisk(), new Hydra(), new Phoenix(), new Pegasus() }
    };

    private static Equipment[][] eqArr = {
            { new Chainmail(), new Regalia(), new Fleece() },
            { new Excalibur(), new Amulet(), new Crystal() }
    };

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
        new Player("Thumul Dasun", "thumul", new Arcane());
        new Player("Devinda Dilshan", "devinda", new Desert());
        new Player("Shanil Praveen", "shanil", new Hillcrest());
        new Player("Dinara de. Silva", "dinara", new Desert());
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
                currentPlayer = tempPlayer;
                break;
            } else {
                print("Invalid User Name. Try it again.\n");
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

        print("SElect a Homeground");
        print("1. Marshland\n2. Hillcrest\n3. Desert\n4. Arcane");
        Ground hg = new Arcane();
        inputStr = stdin.nextLine();
        if (inputStr == "1")
            hg = new Marshland();
        if (inputStr == "2")
            hg = new Hillcrest();
        if (inputStr == "3")
            hg = new Desert();
        if (inputStr == "4")
            hg = new Arcane();
        new Player(name, uName, hg);
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
                if (currentPlayer.getArmy() == null || !currentPlayer.getArmy().isReadyToBattle()) {
                    print("First, you should create a deck to combat.");
                    playerUI();
                } else {
                    selectOpponentUI();
                }

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
        System.out.print(
                "\n\tHomeground : " + String.valueOf(currentPlayer.getHomeGround().getClass()).substring(27) + "\n");
        print("1. Change Name");
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
        switch (tempName) {
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
        }
        charactersShopUI();
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
        }
        charactersShopUI();
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
        }
        charactersShopUI();
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

        }
        charactersShopUI();
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
                if (currentPlayer.getGC() > Dragon.price) {
                    currentPlayer.addMythicalCreature(new Dragon());
                    currentPlayer.changeGC(0 - Dragon.price);
                    print("Successfully added a Dragon to the Barrack. \nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                    viewMythicalCreaturesToBuy();
                }
                break;
            case "2":
                if (currentPlayer.getGC() > Basilisk.price) {
                    currentPlayer.addMythicalCreature(new Basilisk());
                    currentPlayer.changeGC(0 - Basilisk.price);
                    print("Successfully added a Basilisk to the Barrack\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                    viewMythicalCreaturesToBuy();
                }
                break;
            case "3":
                if (currentPlayer.getGC() > Hydra.price) {
                    currentPlayer.addMythicalCreature(new Hydra());
                    currentPlayer.changeGC(0 - Hydra.price);
                    print("Successfully added a Hydra to the Barrack\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                    viewMythicalCreaturesToBuy();
                }
                break;
            case "4":
                if (currentPlayer.getGC() > Phoenix.price) {
                    currentPlayer.addMythicalCreature(new Phoenix());
                    currentPlayer.changeGC(0 - Phoenix.price);
                    print("Successfully added a Phoenix to the Barrack\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                    viewMythicalCreaturesToBuy();
                }
                break;
            case "5":
                if (currentPlayer.getGC() > Pegasus.price) {
                    currentPlayer.addMythicalCreature(new Pegasus());
                    currentPlayer.changeGC(0 - Pegasus.price);
                    print("Successfully added a Pegasus to the Barrack\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                    viewMythicalCreaturesToBuy();
                }
                break;
            case "98":
                charactersShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
                viewMythicalCreaturesToBuy();
        }
        charactersShopUI();
    }

    private static String getType(Character chrtr) {
        String type = "";
        if (chrtr instanceof Highlander)
            type = "Highlander";
        if (chrtr instanceof Marshlanders)
            type = "Marshlander";
        if (chrtr instanceof Sunchildren)
            type = "Sunchildren";
        if (chrtr instanceof Mystics)
            type = "Mystic";
        return type;
    }

    private static void viewCharacterStats(Character chrtr, String num) {
        String armour = chrtr.getArmour() != null ? String.valueOf(chrtr.getArmour().getClass()).substring(30) : "none";
        String artefact = chrtr.getArtefact() != null ? String.valueOf(chrtr.getArtefact().getClass()).substring(30)
                : "none";
        print(num + String.valueOf(chrtr.getClass()).substring(30));
        print("   Type : " + getType(chrtr));
        print("   Attack : " + chrtr.getAttack());
        print("   Defence : " + chrtr.getDefense());
        print("   ealth : " + chrtr.getHealth());
        print("   Speed : " + chrtr.getSpeed());
        print("   Current Value : " + chrtr.getCurrentValue());
        print("   Armor : " + armour);
        print("   Artifact : " + artefact + "\n");
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
                playerUI();
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

    // completed
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
                sellSoldiersUI();
                ;
                break;
            case "4":
                upgradeSoldiersUI();
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
        print("Select soldier category to " + msg);
        print("1. Archers\n2. Knights\n3. Mages\n4. Healers\n5. Mythical Creatures\n98. Back");
        return stdin.nextLine();
    }

    private static void viewCharacters(ArrayList<Character> list, String title) {
        print("-----" + title + "-----");
        if (list.isEmpty())
            print("No characters\n");
        for (int i = 0; i < list.size(); i++) {
            Character character = list.get(i);
            viewCharacterStats(character, (i + 1) + ". ");
        }
    }

    private static ArrayList<Character> viewBarrack(String msg) {
        ArrayList<Character> tempCharacters = null;
        switch (selectCategoryTo(msg)) {
            case "1":
                tempCharacters = currentPlayer.getArchers();
                if (tempCharacters.isEmpty()) {
                    print("There is no any archers in the barrack.");
                    viewBarrack(msg);
                } else {
                    viewCharacters(tempCharacters, "Archers");
                }
                break;

            case "2":
                tempCharacters = currentPlayer.getKnights();
                if (tempCharacters.isEmpty()) {
                    print("There is no any knights in the barrack.");
                    viewBarrack(msg);
                } else {
                    viewCharacters(tempCharacters, "Knights");
                }
                break;

            case "3":
                tempCharacters = currentPlayer.getMages();
                if (tempCharacters.isEmpty()) {
                    print("There is no any mages in the barrack.");
                    viewBarrack(msg);
                } else {
                    viewCharacters(tempCharacters, "Mages");
                }
                break;

            case "4":
                tempCharacters = currentPlayer.getHealers();
                if (tempCharacters.isEmpty()) {
                    print("There is no any healers in the barrack.");
                    viewBarrack(msg);
                } else {
                    viewCharacters(tempCharacters, "Healers");
                }
                break;

            case "5":
                tempCharacters = currentPlayer.getMythicalCreatures();
                if (tempCharacters.isEmpty()) {
                    print("There is no any mythical creaturess in the barrack.");
                    viewBarrack(msg);
                } else {
                    viewCharacters(tempCharacters, "Mythical Creatures");
                }
                break;

            case "98":
                soldiersUI();
                break;
            default:
                print("Invalid input. Try again");
                viewBarrack(msg);
                break;
        }

        return tempCharacters;
    }

    private static void barrackUI() {
        ArrayList<Character> tempCharacters = null;

        tempCharacters = currentPlayer.getArchers();
        if (tempCharacters.isEmpty()) {
            print("There is no any archers in the barrack.\n");
        } else {
            viewCharacters(tempCharacters, "Archers");
        }

        tempCharacters = currentPlayer.getKnights();
        if (tempCharacters.isEmpty()) {
            print("There is no any knights in the barrack.\n");
        } else {
            viewCharacters(tempCharacters, "Knights");
        }

        tempCharacters = currentPlayer.getMages();
        if (tempCharacters.isEmpty()) {
            print("There is no any mages in the barrack.\n");
        } else {
            viewCharacters(tempCharacters, "Mages");
        }

        tempCharacters = currentPlayer.getHealers();
        if (tempCharacters.isEmpty()) {
            print("There is no any healers in the barrack.\n");
        } else {
            viewCharacters(tempCharacters, "Healers");
        }

        tempCharacters = currentPlayer.getMythicalCreatures();
        if (tempCharacters.isEmpty()) {
            print("There is no any mythical cretauress in the barrack.\n");
        } else {
            viewCharacters(tempCharacters, "Mythical Creatures");
        }

        soldiersUI();
    }

    private static Character getSoldierFromBarrack(String msg) {
        ArrayList<Character> chrtrs;
        int out;
        while (true) {
            chrtrs = viewBarrack(msg);
            inputStr = stdin.nextLine();
            out = 0;
            try {
                out = Integer.valueOf(inputStr);
            } catch (NumberFormatException e) {
                print("Invalid input. Try again.");
                return getSoldierFromBarrack(msg);
            }
            if (out == 98) {
                continue;
            } else if (out <= chrtrs.size() + 1) {
                break;
            }
        }
        return chrtrs.get(out - 1);
    }

    private static void deckUI() {
        // select the category
        String tempStr = "";
        Army tempArmy;
        Character tempCharacter = null;
        inputStr = tempStr = selectCategoryTo("view and modify");
        tempArmy = currentPlayer.getArmy();
        switch (inputStr) {
            case "1":
                if (tempArmy == null || tempArmy.getArcher() == null) {
                    print("No one is assigned as the Archer");
                } else {
                    tempCharacter = tempArmy.getArcher();
                    viewCharacterStats(tempCharacter, "");
                }
                break;

            case "2":
                if (tempArmy == null || tempArmy.getKnight() == null) {
                    print("No one is assigned as the Knight");
                } else {
                    tempCharacter = tempArmy.getKnight();
                    viewCharacterStats(tempCharacter, "");
                }
                break;

            case "3":
                if (tempArmy == null || tempArmy.getMage() == null) {
                    print("No one is assigned as the Mage");
                } else {
                    tempCharacter = tempArmy.getMage();
                    viewCharacterStats(tempCharacter, "");
                }
                break;

            case "4":
                if (tempArmy == null || tempArmy.getHealer() == null) {
                    print("No one is assigned as the Healer");
                } else {
                    tempCharacter = tempArmy.getHealer();
                    viewCharacterStats(tempCharacter, "");
                }
                break;

            case "5":
                if (tempArmy == null || tempArmy.getMythicalCreature() == null) {
                    print("No one is assigned as the Mythical Creature");
                } else {
                    tempCharacter = tempArmy.getMythicalCreature();
                    viewCharacterStats(tempCharacter, "");
                }
                break;

            case "98":
                armyUI();
                break;
            default:
                print("Invalid input. Try it again.");
                break;
        }
        if (tempStr != "98") {
            if (tempCharacter == null) {
                print("1. Add Character\n98. Back");
                if (stdin.nextLine().equals("1")) {
                    addDeckFromBarrack(tempStr);
                    deckUI();
                } else {
                    deckUI();
                }
            } else {
                print("1. Remove Character from deck\n98. Back");
                if (stdin.nextLine().equals("1")) {
                    removeCharacterFromDeck(tempStr);
                }
                deckUI();
            }
        }

        // have to complete
    }

    private static void removeCharacterFromDeck(String id) {
        Character tempCharacter;
        String name = "";
        Army tempArmy = currentPlayer.getArmy() == null ? new Army() : currentPlayer.getArmy();
        switch (id) {
            case "1":
                tempCharacter = tempArmy.getArcher();
                currentPlayer.addArcher(tempCharacter);
                name = String.valueOf(tempCharacter.getClass()).substring(30);
                tempArmy.addArcher(null);
                print("Successfully removed " + name + " from the deck\n");
                break;

            case "2":
                tempCharacter = tempArmy.getKnight();
                currentPlayer.addKnight(tempCharacter);
                name = String.valueOf(tempCharacter.getClass()).substring(30);
                tempArmy.addKnight(null);
                print("Successfully removed " + name + " from the deck\n");
                break;

            case "3":
                tempCharacter = tempArmy.getMage();
                currentPlayer.addMage(tempCharacter);
                name = String.valueOf(tempCharacter.getClass()).substring(30);
                tempArmy.addMage(null);
                print("Successfully removed " + name + " from the deck\n");
                break;

            case "4":
                tempCharacter = tempArmy.getHealer();
                currentPlayer.addHealer(tempCharacter);
                name = String.valueOf(tempCharacter.getClass()).substring(30);
                tempArmy.addHealer(null);
                print("Successfully removed " + name + " from the deck\n");
                break;

            case "5":
                tempCharacter = tempArmy.getMythicalCreature();
                currentPlayer.addMythicalCreature(tempCharacter);
                name = String.valueOf(tempCharacter.getClass()).substring(30);
                tempArmy.addMythicalCreature(null);
                print("Successfully removed " + name + " from the deck\n");
                break;
            default:
                break;
        }
    }

    private static void addDeckFromBarrack(String id) {
        Character tempCharacter;
        String name = "";
        Army tempArmy = currentPlayer.getArmy() == null ? new Army() : currentPlayer.getArmy();
        switch (id) {
            case "1":
                print("Select Archer");
                viewCharacters(currentPlayer.getArchers(), "Archers");
                int out;
                inputStr = stdin.nextLine();
                out = 0;
                try {
                    out = Integer.valueOf(inputStr);
                } catch (NumberFormatException e) {
                    print("Invalid input. Try again.");
                    return;
                }
                if (out >= 0 && out <= currentPlayer.getArchers().size()) {
                    tempCharacter = currentPlayer.getArchers().get(out - 1);
                    name = String.valueOf(tempCharacter.getClass()).substring(30);
                    tempArmy.addArcher((Archer) tempCharacter);
                    currentPlayer.setArmy(tempArmy);
                    print("Successfully added " + name + " to the deck");
                }
                break;
            case "2":
                print("Select Knight");
                viewCharacters(currentPlayer.getKnights(), "Knights");
                inputStr = stdin.nextLine();
                out = 0;
                try {
                    out = Integer.valueOf(inputStr);
                } catch (NumberFormatException e) {
                    print("Invalid input. Try again.");
                    return;
                }
                if (out >= 0 && out <= currentPlayer.getKnights().size()) {
                    tempCharacter = currentPlayer.getKnights().get(out - 1);
                    name = String.valueOf(tempCharacter.getClass()).substring(30);
                    tempArmy.addKnight((Knight) tempCharacter);
                    currentPlayer.setArmy(tempArmy);
                    print("Successfully added " + name + " to the deck");
                }
                break;

            case "3":
                print("Select Mage");
                viewCharacters(currentPlayer.getMages(), "Mages");
                inputStr = stdin.nextLine();
                out = 0;
                try {
                    out = Integer.valueOf(inputStr);
                } catch (NumberFormatException e) {
                    print("Invalid input. Try again.");
                    return;
                }
                if (out >= 0 && out <= currentPlayer.getMages().size()) {
                    tempCharacter = currentPlayer.getMages().get(out - 1);
                    name = String.valueOf(tempCharacter.getClass()).substring(30);
                    tempArmy.addMage((Mage) tempCharacter);
                    currentPlayer.setArmy(tempArmy);
                    print("Successfully added " + name + " to the deck");
                }
                break;

            case "4":
                print("Select Healer");
                viewCharacters(currentPlayer.getHealers(), "Healers");
                inputStr = stdin.nextLine();
                out = 0;
                try {
                    out = Integer.valueOf(inputStr);
                } catch (NumberFormatException e) {
                    print("Invalid input. Try again.");
                    return;
                }
                if (out >= 0 && out <= currentPlayer.getHealers().size()) {
                    tempCharacter = currentPlayer.getHealers().get(out - 1);
                    name = String.valueOf(tempCharacter.getClass()).substring(30);
                    tempArmy.addHealer((Healer) tempCharacter);
                    currentPlayer.setArmy(tempArmy);
                    print("Successfully added " + name + " to the deck");
                }
                break;

            case "5":
                print("Select Mythical Creature");
                viewCharacters(currentPlayer.getMythicalCreatures(), "Mythical Creature");

                inputStr = stdin.nextLine();
                out = 0;
                try {
                    out = Integer.valueOf(inputStr);
                } catch (NumberFormatException e) {
                    print("Invalid input. Try again.");
                    return;
                }
                if (out >= 0 && out <= currentPlayer.getMythicalCreatures().size()) {
                    tempCharacter = currentPlayer.getMythicalCreatures().get(out - 1);
                    name = String.valueOf(tempCharacter.getClass()).substring(30);
                    tempArmy.addMythicalCreature((MythicalCreature) tempCharacter);
                    currentPlayer.setArmy(tempArmy);
                    print("Successfully added " + name + " to the deck");
                }

                break;
            default:
                break;
        }

    }

    private static void charactersShopUI() {
        String category = selectCategoryTo("buy");
        switch (category) {
            case "1":
                viewArchersToBuy();
                break;

            case "2":
                viewKnightsToBuy();
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

    private static void viewArmours(ArrayList<Armour> list) {
        print("-----Armours-----");
        for (int i = 0; i < list.size(); i++) {
            Armour armr = list.get(i);
            print((i + 1) + ". " + String.valueOf(armr.getClass()).substring(30));
            print("   Attack : " + armr.getAttack());
            print("   Defence : " + armr.getDefence());
            print("   Health : " + armr.getHealth());
            print("   Speed : " + armr.getSpeed());
            print("   Price : " + armr.getPrice() + "\n");
        }
    }

    private static void viewArtefacts(ArrayList<Artefacts> list) {
        print("-----Artefacts-----");
        for (int i = 0; i < list.size(); i++) {
            Artefacts artfct = list.get(i);
            print((i + 1) + ". " + String.valueOf(artfct.getClass()).substring(30));
            print("   Attack : " + artfct.getAttack());
            print("   Defence : " + artfct.getDefence());
            print("   Health : " + artfct.getHealth());
            print("   Speed : " + artfct.getSpeed());
            print("   Price : " + artfct.getPrice() + "\n");
        }
    }

    private static Armour getArmourFromInventory() {
        viewArmours(currentPlayer.getArmors());
        int out;
        while (true) {
            inputStr = stdin.nextLine();
            out = 0;
            try {
                out = Integer.valueOf(inputStr);
            } catch (NumberFormatException e) {
                print("Invalid input. Try again.");
                continue;
            }
            if (out == 98) {
                continue;
            } else if (out <= currentPlayer.getArmors().size()) {
                break;
            }
        }
        return currentPlayer.getArmors().get(out - 1);
    }

    private static Artefacts getArtefactFromInventory() {
        viewArtefacts(currentPlayer.getArtefacts());
        int out;
        while (true) {
            inputStr = stdin.nextLine();
            out = 0;
            try {
                out = Integer.valueOf(inputStr);
            } catch (NumberFormatException e) {
                print("Invalid input. Try again.");
                return getArtefactFromInventory();
            }
            if (out == 98) {
                continue;
            } else if (out <= currentPlayer.getArtefacts().size()) {
                break;
            }
        }
        return currentPlayer.getArtefacts().get(out - 1);
    }

    private static void upgradeSoldiersUI() {
        Character chrtr = getSoldierFromBarrack("upgrade");
        switch (selectEqCategoryTo("upgrade")) {
            case "1":
                Armour armour = getArmourFromInventory();
                if (chrtr.getArmour() != null) {
                    currentPlayer.addArmor(chrtr.getArmour());
                }
                chrtr.addArmour(armour);
                currentPlayer.removeArmor(armour);
                print("Succesfully upgraded the Armour");
                break;

            case "2":
                Artefacts artefact = getArtefactFromInventory();
                if (chrtr.getArtefact() != null) {
                    currentPlayer.addArtefact(chrtr.getArtefact());
                }
                chrtr.addArtefacts(artefact);
                currentPlayer.removeArtefact(artefact);
                print("Succesfully upgraded the Artefact");
                break;

            case "98":
                upgradeSoldiersUI();
                break;
            default:
                print("Invalid input. Try it again.");
                upgradeSoldiersUI();
                break;
        }
    }

    private static void sellSoldiersUI() {
        Character chrtr = getSoldierFromBarrack("sell");
        String name = String.valueOf(chrtr.getClass()).substring(30);
        int sellPrice = Math.round(chrtr.getCurrentValue() * 9 / 10);
        if (areYouSure("sell " + name + "\nYou will only gain " + sellPrice + "gc")) {
            if (chrtr instanceof Archer) {
                currentPlayer.removeArcher(chrtr);
                print("Succesfully removed " + name + " from the barrack");
            } else if (chrtr instanceof Knight) {
                currentPlayer.removeKnight(chrtr);
                print("Succesfully removed " + name + " from the barrack");
            } else if (chrtr instanceof Mage) {
                currentPlayer.removeMage(chrtr);
                print("Succesfully removed " + name + " from the barrack");
            } else if (chrtr instanceof Healer) {
                currentPlayer.removeHealer(chrtr);
                print("Succesfully removed " + name + " from the barrack");
            } else if (chrtr instanceof MythicalCreature) {
                currentPlayer.removeMythicalCreature(chrtr);
                print("Succesfully removed " + name + " from the barrack");
            }
        }
        print("");
        sellSoldiersUI();
    }

    private static void equipmentsUI() {
        print("1. View Available Equipments\n2. Buy Equipments\n98. Back");
        switch (stdin.nextLine()) {
            case "1":
                viewInventory("view");
                break;
            case "2":
                equipmentsShopUI();
                break;
            case "98":
                armyUI();
                break;
            default:
                print("Invalid input. Try it again.");
                soldiersUI();
        }
    }

    private static String selectEqCategoryTo(String msg) {
        print("Select equipment category to " + msg);
        print("1. Armours\n2. Artefacts\n98. Back");
        return stdin.nextLine();
    }

    private static void viewInventory(String msg) {
        inputStr = selectEqCategoryTo(msg);
        switch (inputStr) {
            case "1":
                ArrayList<Armour> tempEq = currentPlayer.getArmors();
                if (tempEq.isEmpty()) {
                    print("There is no any available armours in the inventory.");
                } else {
                    viewArmours(tempEq);
                }
                break;
            case "2":
                ArrayList<Artefacts> tempArtfcts = currentPlayer.getArtefacts();
                if (tempArtfcts.isEmpty()) {
                    print("There is no any available artefacts in the inventory.");
                } else {
                    viewArtefacts(tempArtfcts);
                }
                break;
            case "98":
                equipmentsUI();
                break;
            default:
                print("Invalid input. Try again");
                equipmentsUI();
                break;
        }
        if (inputStr != "98")
            equipmentsUI();
    }

    private static void viewArmorsToBuy() {
        print("Select armour to buy");
        print("--------------------\n");

        print("1. Chainmail");

        print("\tAttack  : " + Chainmail.ATTACK);
        print("\tDefence : " + Chainmail.DEFENCE);
        print("\tHealth  : " + Chainmail.HEALTH);
        print("\tSpeed   : " + Chainmail.SPEED);
        print("\tPrice   : " + Chainmail.PRICE + "\n");

        print("2. Regalia");
        print("\tAttack  : " + Regalia.ATTACK);
        print("\tDefence : " + Regalia.DEFENCE);
        print("\tHealth  : " + Regalia.HEALTH);
        print("\tSpeed   : " + Regalia.SPEED);
        print("\tPrice   : " + Regalia.PRICE + "\n");

        print("3. Fleece");
        print("\tAttack  : " + Fleece.ATTACK);
        print("\tDefence : " + Fleece.DEFENCE);
        print("\tHealth  : " + Fleece.HEALTH);
        print("\tSpeed   : " + Fleece.SPEED);
        print("\tPrice   : " + Fleece.PRICE + "\n");

        print("-------------------");
        inputStr = stdin.nextLine();
        switch (inputStr) {
            case "1":
                if (currentPlayer.getGC() > Chainmail.PRICE) {
                    currentPlayer.addArmor(new Chainmail());
                    currentPlayer.changeGC(0 - (int) Chainmail.PRICE);
                    print("Successfully added a Chainmail to the Inventory. \nAvailable balance:"
                            + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");

                }
                break;
            case "2":
                if (currentPlayer.getGC() > Regalia.PRICE) {
                    currentPlayer.addArmor(new Regalia());
                    currentPlayer.changeGC(0 - (int) Regalia.PRICE);
                    print("Successfully added a Regalia to the Inventory\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                }
                break;
            case "3":
                if (currentPlayer.getGC() > Fleece.PRICE) {
                    currentPlayer.addArmor(new Fleece());
                    currentPlayer.changeGC(0 - (int) Fleece.PRICE);
                    print("Successfully added a Fleece to the Inventory\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                }
                break;
            case "98":
                equipmentsShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
        }
        if (inputStr != "98")
            equipmentsUI();
    }

    private static void viewArtefactsToBuy() {
        print("Select artefact to buy");
        print("--------------------\n");

        print("1. Excalibur");

        print("\tAttack  : " + Excalibur.ATTACK);
        print("\tDefence : " + Excalibur.DEFENCE);
        print("\tHealth  : " + Excalibur.HEALTH);
        print("\tSpeed   : " + Excalibur.SPEED);
        print("\tPrice   : " + Excalibur.PRICE + "\n");

        print("2. Amulet");
        print("\tAttack  : " + Amulet.ATTACK);
        print("\tDefence : " + Amulet.DEFENCE);
        print("\tHealth  : " + Amulet.HEALTH);
        print("\tSpeed   : " + Amulet.SPEED);
        print("\tPrice   : " + Amulet.PRICE + "\n");

        print("3. Crystal");
        print("\tAttack  : " + Crystal.ATTACK);
        print("\tDefence : " + Crystal.DEFENCE);
        print("\tHealth  : " + Crystal.HEALTH);
        print("\tSpeed   : " + Crystal.SPEED);
        print("\tPrice   : " + Crystal.PRICE + "\n");

        print("-------------------");
        inputStr = stdin.nextLine();
        switch (inputStr) {
            case "1":
                if (currentPlayer.getGC() > Excalibur.PRICE) {
                    currentPlayer.addArtefact(new Excalibur());
                    currentPlayer.changeGC(0 - (int) Excalibur.PRICE);
                    print("Successfully added a Excalibur to the Inventory. \nAvailable balance:"
                            + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                }
                break;
            case "2":
                if (currentPlayer.getGC() > Amulet.PRICE) {
                    currentPlayer.addArtefact(new Amulet());
                    currentPlayer.changeGC(0 - (int) Amulet.PRICE);
                    print("Successfully added a Amulet to the Inventory\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                }
                break;
            case "3":
                if (currentPlayer.getGC() > Crystal.PRICE) {
                    currentPlayer.addArtefact(new Crystal());
                    currentPlayer.changeGC(0 - (int) Crystal.PRICE);
                    print("Successfully added a Crystal to the Inventory\nAvailable balance:" + currentPlayer.getGC()
                            + " gc");
                } else {
                    print("Your gold coin balance is insufficient to buy this character.\nTry to buy another character.");
                }
                break;
            case "98":
                equipmentsShopUI();
                break;
            default:
                print("Invalid input. Try it again.");
        }

        if (inputStr != "98")
            equipmentsUI();
    }

    private static void equipmentsShopUI() {
        inputStr = selectEqCategoryTo("buy");
        switch (inputStr) {
            case "1":
                viewArmorsToBuy();
                break;

            case "2":
                viewArtefactsToBuy();
                break;

            case "98":
                equipmentsUI();
                break;
            default:
                print("Invalid input. Try it again.");
                break;
        }
    }

}
