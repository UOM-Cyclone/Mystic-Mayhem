package MysticMayhem.Controllers;

import MysticMayhem.Army;
import MysticMayhem.Characters.*;
import MysticMayhem.Equipments.Amulet;
import MysticMayhem.Equipments.Armour;
import MysticMayhem.Equipments.Artefacts;
import MysticMayhem.Equipments.Chainmail;
import MysticMayhem.Equipments.Crystal;
import MysticMayhem.Equipments.Equipment;
import MysticMayhem.Equipments.Excalibur;
import MysticMayhem.Equipments.Fleece;
import MysticMayhem.Equipments.Regalia;
import MysticMayhem.Grounds.*;
import MysticMayhem.Battle;
import MysticMayhem.Player;
import MysticMayhem.Characters.Character;

import java.util.Scanner;
import java.util.Vector;

public class Navigator {

    Player currentPlayer, opponentPlayer;
    Scanner scanner = new Scanner(System.in);

    public void start() {
        Player test = new Player("dev", "dev", new Marshland());

        String details = """
                1) Login
                2) Create a account
                99) Exit""";

        do {
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    login();
                    break;
                case "2":
                    createAcc();
                    break;
                case "99":
                    return;
                default:
                    System.out.println("Please enter the valid input.\n");
                    break;
            }
        } while (true);
    }

    public void login() {

        do {
            System.out.print("If you want go back enter '-1' \nEnter your username : ");
            String userInput = scanner.nextLine();

            if (userInput.equals("-1")) {
                return;
            } else {
                if (Player.players.containsKey(userInput)) {
                    currentPlayer = Player.players.get(userInput);
                    Menu();
                    return;
                }
                System.out.println("\nEnter valid username. ");
            }
        } while (true);
    }

    public void createAcc() {
        String name;
        String userName;
        String userInput;
        Ground homeGround;

        System.out.print("\nIf your want to cancel creating account type '-1'\nEnter your name : ");
        userInput = scanner.nextLine();
        if (userInput.equals("-1")) {
            return;
        } else {
            name = userInput;
        }

        while (true) {
            System.out.print("\nIf your want to cancel creating account type '-1'\nEnter your username : ");
            userInput = scanner.nextLine();
            if (userInput.equals("-1")) {
                return;
            } else {
                if (Player.players.containsKey(userInput)) {
                    System.out.println("Entered username is already taken. Please enter valid username");
                    continue;
                } else {
                    userName = userInput;
                    break;
                }
            }
        }

        while (true) {
            System.out.println("""
                    If you want to cancel creating account enter '-1'

                    New account needs a homeground,

                    1) Arcane
                    2) Desert
                    3) Hillcrest
                    4) Marshland

                    Select your home ground : """);

            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    homeGround = new Arcane();
                    new Player(name, userName, homeGround);
                    System.out.println("\nyou just created new profile. please login.");
                    return;
                case "2":
                    homeGround = new Desert();
                    new Player(name, userName, homeGround);
                    return;
                case "3":
                    homeGround = new Hillcrest();
                    new Player(name, userName, homeGround);
                    return;
                case "4":
                    homeGround = new Marshland();
                    new Player(name, userName, homeGround);
                    return;
                case "-1":
                    return;
                default:
                    System.out.println("Please enter the valid input.\n");
                    break;
            }
        }
    }

    public void Menu() {
        String details = """
                1) Battle
                2) Army
                3) Profile
                98) Back""";

        do {
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    if (currentPlayer.getArmy() == null){
                        System.out.println("First, you should buy characters and create a deck to battle");
                    }else if(!currentPlayer.getArmy().isReadyToBattle()) {
                        System.out.println("Your deck should be contain all 5 type of characters to battle");
                    } else {
                        combat();
                    }
                    break;
                case "2":
                    armyMenu();
                    break;
                case "3":
                    showProfile();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        } while (true);
    }

    public void combat() {
        Player tempPlayer;
        String userInput;
        while (true) {
            tempPlayer = Player.getRandomPlayer();
            if (tempPlayer == currentPlayer) {
                continue;
            }
            System.out.print("Select opponent to combat\n\t" + tempPlayer.getName() + "\n\t  -XP Level : "
                    + tempPlayer.getXP() + "\n1. Challenge\n2.Skip\n98.Back to menu\n");
            userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    opponentPlayer = tempPlayer;

                    Battle battle = new Battle();
                    battle.start(currentPlayer, opponentPlayer);
                    return;

                case "2":
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }

    public void showProfile() {

        do {
            String details = String.format("""
                    Your name : %s
                    username : %s
                    XP : %d
                    Coins : %d
                    Home Ground : %s

                    1) Change name
                    98) Back""",
                    currentPlayer.getName(),
                    currentPlayer.getUsername(),
                    currentPlayer.getXP(),
                    currentPlayer.getGC(),
                    String.valueOf(currentPlayer.getHomeGround().getClass()).substring(27));

            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    System.out.print("Enter your new name : ");
                    String newName = scanner.nextLine();
                    System.out.print(
                            String.format("change %s to %s ?\n" +
                                    "Enter Y/N :", currentPlayer.getName(), newName));
                    boolean isValid = false;
                    while (!isValid) {
                        userInput = scanner.nextLine();
                        switch (userInput) {
                            case "Y", "y":
                                currentPlayer.setName(newName);
                                isValid = true;
                                break;
                            case "N", "n":
                                isValid = true;
                                break;
                            default:
                                System.out.print("Enter valid input.\n");
                                break;
                        }
                    }
                    break;
                case "98":
                    return;
                default:
                    System.out.print("Please enter the valid input.\n");
                    break;
            }
        } while (true);
    }

    public void armyMenu() {
        String details = """
                1) Battle Deck
                2) Buy Character
                3) Sell Character
                4) Upgrade Character
                98) Back""";

        do {
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    battleDeck();
                    break;
                case "2":
                    buyCharacter();
                    break;
                case "3":
                    sellCharacter();
                    break;
                case "4":
                    upgradeCharacter();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        } while (true);
    }


    public void battleDeck(){

        while (true){
            String details = "Your battle deck\n\n";

            Army army = currentPlayer.getArmy();
            if (army.getArcher() == null){
                details += String.format(" Archer - %s\n\n", "None");
            } else {
                details += String.format(" Archer - %s\n\n",
                        String.valueOf(army.getArcher().getClass()).substring(30));
            }
            if (army.getKnight() == null){
                details += String.format(" Knight - %s\n\n", "None");
            } else {
                details += String.format(" Knight - %s\n\n",
                        String.valueOf(army.getKnight().getClass()).substring(30));
            }
            if (army.getMage() == null){
                details += String.format(" Mage - %s\n\n", "None");
            } else {
                details += String.format(" Mage - %s\n\n",
                        String.valueOf(army.getMage().getClass()).substring(30));
            }
            if (army.getHealer() == null){
                details += String.format(" Healer - %s\n\n", "None");
            } else {
                details += String.format(" Healer - %s\n\n",
                        String.valueOf(army.getHealer().getClass()).substring(30));
            }
            if (army.getMythicalCreature() == null){
                details += String.format(" Mythical Creature - %s\n\n", "None");
            } else {
                details += String.format(" Mythical Creature - %s\n\n",
                        String.valueOf(army.getMythicalCreature().getClass()).substring(30));
            }

            details += String.format("""
                1) Change Archer
                2) Change Knight
                3) Change Mage
                4) Change Healer
                5) Change Mythical Creature
                
                98) Back
                """);

            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();

            switch (userInput){
                case "1":
                    changeArcher();
                    break;
                case "2":
                    changeKnight();
                    break;
                case "3":
                    changeMage();
                    break;
                case "4":
                    changeHealer();
                    break;
                case "5":
                    changeCreature();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }

    }

    public void changeArcher(){
        String userInput;
        Vector<Archer> archers = currentPlayer.getArchers();
        if(archers.isEmpty()){
            System.out.println("You don't have any archer to change");
        } else {
            for (int i = 0; i < archers.size(); i++){
                System.out.println(String.format("""
                                    %d) %s
                                    """,(i+1),
                        String.valueOf(archers.get(i).getClass()).substring(30)));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= archers.size()) {
                        currentPlayer.getArmy().addArcher(archers.get(index - 1 ));
                        System.out.println(String.format("You have successfully changed your battle archer to %s",
                                String.valueOf(archers.get(index - 1).getClass()).substring(30)));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void changeKnight(){
        String userInput;
        Vector<Knight> knights = currentPlayer.getKnights();
        if(knights.isEmpty()){
            System.out.println("You don't have any knight to change");
        } else {
            for (int i = 0; i < knights.size(); i++){
                System.out.println(String.format("""
                                    %d) %s
                                    """,(i+1),
                        String.valueOf(knights.get(i).getClass()).substring(30)));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= knights.size()) {
                        currentPlayer.getArmy().addKnight(knights.get(index - 1));
                        System.out.println(String.format("You have successfully changed your battle archer to %s",
                                String.valueOf(knights.get(index - 1).getClass()).substring(30)));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void changeMage(){
        String userInput;
        Vector<Mage> mages = currentPlayer.getMages();
        if(mages.isEmpty()){
            System.out.println("You don't have any mage to change");
        } else {
            for (int i = 0; i < mages.size(); i++){
                System.out.println(String.format("""
                                    %d) %s
                                    """,(i+1),
                        String.valueOf(mages.get(i).getClass()).substring(30)));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= mages.size()) {
                        currentPlayer.getArmy().addMage(mages.get(index - 1));
                        System.out.println(String.format("You have successfully changed your battle archer to %s",
                                String.valueOf(mages.get(index - 1).getClass()).substring(30)));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void changeHealer(){
        String userInput;
        Vector<Healer> healers = currentPlayer.getHealers();
        if(healers.isEmpty()){
            System.out.println("You don't have any healer to change");
        } else {
            for (int i = 0; i < healers.size(); i++){
                System.out.println(String.format("""
                                    %d) %s
                                    """,(i+1),
                        String.valueOf(healers.get(i).getClass()).substring(30)));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= healers.size()) {
                        currentPlayer.getArmy().addHealer(healers.get(index - 1));
                        System.out.println(String.format("You have successfully changed your battle archer to %s",
                                String.valueOf(healers.get(index - 1).getClass()).substring(30)));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void changeCreature(){
        String userInput;
        Vector<MythicalCreature> creatures = currentPlayer.getCreatures();
        if(creatures.isEmpty()){
            System.out.println("You don't have any healer to change");
        } else {
            for (int i = 0; i < creatures.size(); i++){
                System.out.println(String.format("""
                                    %d) %s
                                    """,(i+1),
                        String.valueOf(creatures.get(i).getClass()).substring(30)));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= creatures.size()) {
                        currentPlayer.getArmy().addMythicalCreature(creatures.get(index - 1));
                        System.out.println(String.format("You have successfully changed your battle archer to %s",
                                String.valueOf(creatures.get(index - 1).getClass()).substring(30)));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }

    public void buyCharacter(){

        String details = """
                which type of character do you wanna buy?

                1) Archer
                2) Knight
                3) Mage
                4) Healer
                5) creature

                98) Back
                """;

        while (true) {
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    buyArcher();
                    break;
                case "2":
                    buyKnight();
                    break;
                case "3":
                    buyMage();
                    break;
                case "4":
                    buyHealer();
                    break;
                case "5":
                    buyCreature();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }

    public void sellCharacter(){
        String details = """
                which type of character do you wanna sell?
                
                1) Archer
                2) Knight
                3) Mage
                4) Healer
                5) creature
                
                98) Back
                """;

        while (true){
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();

            switch (userInput){
                case "1":
                    sellArcher();
                    return;
                case "2":
                    sellKnight();
                    return;
                case "3":
                    sellMage();
                    return;
                case "4":
                    sellHealer();
                    return;
                case "5":
                    sellCreature();
                    return;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }


    public void upgradeCharacter() {
        String details = """
                *You can upgrade characters by adding armours and artefacts.*

                which type of character do you wanna upgrade?

                1) Archer
                2) Knight
                3) Mage
                4) Healer
                5) creature

                98) Back
                """;

        while (true) {
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    upgradeArcher();
                    break;
                case "2":
                    upgradeKnight();
                    break;
                case "3":
                    upgradeMage();
                    break;
                case "4":
                    upgradeHealer();
                    break;
                case "5":
                    upgradeCreature();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }

    public void upgradeArcher() {
        String userInput;
        Vector<Archer> archers = currentPlayer.getArchers();
        String armour, artefact;
        if (archers.isEmpty()) {
            System.out.println("You don't have any archer to upgrade");
            return; //add-2
        } else {
            for (int i = 0; i < archers.size(); i++) {
                armour = archers.get(i).getArmour() == null ? "none"
                        : String.valueOf(archers.get(i).getArmour().getClass()).substring(30);
                artefact = archers.get(i).getArtefact() == null ? "none"
                        : String.valueOf(archers.get(i).getArtefact().getClass()).substring(30);
                System.out.println(String.format("""
                        %d) %s
                                Armour   : %s
                                Artefact : %s
                        """, (i + 1),
                        String.valueOf(archers.get(i).getClass()).substring(30),
                        armour, artefact));
            }

            System.out.println("98) Back");
        }
        while (true) {
            try {
                System.out.print("Enter number : ");
                userInput = scanner.nextLine();
                int index = Integer.valueOf(userInput);

                if (userInput.equals("98")) {
                    return;
                } else if (0 < index && index <= archers.size()) {
                    Archer upgradingChar = archers.get(index-1); //add-1
                    upgradeEquipment((Character) upgradingChar);
                    return;
                } else {
                    System.out.println("Enter valid input");
                }
            } catch (Exception e) {
                System.out.println("Enter valid input");
            }
        }
    }

    public void upgradeKnight() {
        String userInput;
        Vector<Knight> knights = currentPlayer.getKnights();
        String armour, artefact;
        if (knights.isEmpty()) {
            System.out.println("You don't have any knight to upgrade");
            return;
        } else {
            for (int i = 0; i < knights.size(); i++) {
                armour = knights.get(i).getArmour() == null ? "none"
                        : String.valueOf(knights.get(i).getArmour().getClass()).substring(30);
                artefact = knights.get(i).getArtefact() == null ? "none"
                        : String.valueOf(knights.get(i).getArtefact().getClass()).substring(30);
                System.out.println(String.format("""
                        %d) %s
                                Armour   : %s
                                Artefact : %s
                        """, (i + 1),
                        String.valueOf(knights.get(i).getClass()).substring(30),
                        armour, artefact));
            }

            System.out.println("98) Back");
        }
        while (true) {
            try {
                System.out.print("Enter number : ");
                userInput = scanner.nextLine();
                int index = Integer.valueOf(userInput);

                if (userInput.equals("98")) {
                    return;
                } else if (0 < index && index <= knights.size()) {
                    Knight upgradingChar = knights.get(index-1);
                    upgradeEquipment((Character) upgradingChar);
                    return;
                } else {
                    System.out.println("Enter valid input");
                }
            } catch (Exception e) {
                System.out.println("Enter valid input");
            }
        }
    }

    public void upgradeMage() {
        String userInput;
        Vector<Mage> mages = currentPlayer.getMages();
        String armour, artefact;
        if (mages.isEmpty()) {
            System.out.println("You don't have any mage to upgrade");
            return;
        } else {
            for (int i = 0; i < mages.size(); i++) {
                armour = mages.get(i).getArmour() == null ? "none"
                        : String.valueOf(mages.get(i).getArmour().getClass()).substring(30);
                artefact = mages.get(i).getArtefact() == null ? "none"
                        : String.valueOf(mages.get(i).getArtefact().getClass()).substring(30);
                System.out.println(String.format("""
                        %d) %s
                                Armour   : %s
                                Artefact : %s
                        """, (i + 1),
                        String.valueOf(mages.get(i).getClass()).substring(30),
                        armour, artefact));
            }

            System.out.println("98) Back");
        }
        while (true) {
            try {
                System.out.print("Enter number : ");
                userInput = scanner.nextLine();
                int index = Integer.valueOf(userInput);

                if (userInput.equals("98")) {
                    return;
                } else if (0 < index && index <= mages.size()) {
                    Mage upgradingChar = mages.get(index-1);
                    upgradeEquipment((Character) upgradingChar);
                    return;
                } else {
                    System.out.println("Enter valid input");
                }
            } catch (Exception e) {
                System.out.println("Enter valid input");
            }
        }
    }

    public void upgradeHealer() {
        String userInput;
        Vector<Healer> healers = currentPlayer.getHealers();
        String armour, artefact;
        if (healers.isEmpty()) {
            System.out.println("You don't have any healer to upgrade");
            return;
        } else {
            for (int i = 0; i < healers.size(); i++) {
                armour = healers.get(i).getArmour() == null ? "none"
                        : String.valueOf(healers.get(i).getArmour().getClass()).substring(30);
                artefact = healers.get(i).getArtefact() == null ? "none"
                        : String.valueOf(healers.get(i).getArtefact().getClass()).substring(30);
                System.out.println(String.format("""
                        %d) %s
                                Armour   : %s
                                Artefact : %s
                        """, (i + 1),
                        String.valueOf(healers.get(i).getClass()).substring(30),
                        armour, artefact));
            }

            System.out.println("98) Back");
        }
        while (true) {
            try {
                System.out.print("Enter number : ");
                userInput = scanner.nextLine();
                int index = Integer.valueOf(userInput);

                if (userInput.equals("98")) {
                    return;
                } else if (0 < index && index <= healers.size()) {
                    Healer upgradingChar = healers.get(index-1);
                    upgradeEquipment((Character) upgradingChar);
                    return;
                } else {
                    System.out.println("Enter valid input");
                }
            } catch (Exception e) {
                System.out.println("Enter valid input");
            }
        }
    }

    public void upgradeCreature() {
        String userInput;
        Vector<MythicalCreature> creatures = currentPlayer.getCreatures();
        String armour, artefact;
        if (creatures.isEmpty()) {
            System.out.println("You don't have any creature to upgrade");
            return;
        } else {
            for (int i = 0; i < creatures.size(); i++) {
                armour = creatures.get(i).getArmour() == null ? "none"
                        : String.valueOf(creatures.get(i).getArmour().getClass()).substring(30);
                artefact = creatures.get(i).getArtefact() == null ? "none"
                        : String.valueOf(creatures.get(i).getArtefact().getClass()).substring(30);
                System.out.println(String.format("""
                        %d) %s
                                Armour   : %s
                                Artefact : %s
                        """, (i + 1),
                        String.valueOf(creatures.get(i).getClass()).substring(30),
                        armour, artefact));
            }

            System.out.println("98) Back");
        }
        while (true) {
            try {
                System.out.print("Enter number : ");
                userInput = scanner.nextLine();
                int index = Integer.valueOf(userInput);

                if (userInput.equals("98")) {
                    return;
                } else if (0 < index && index <= creatures.size()) {
                    MythicalCreature upgradingChar = creatures.get(index-1);
                    upgradeEquipment((Character) upgradingChar);
                    return;
                } else {
                    System.out.println("Enter valid input");
                }
            } catch (Exception e) {
                System.out.println("Enter valid input");
            }
        }
    }

    public void showEqDetails(int index, String name, float price, float attack, float defence, float health,
            float speed) {
        String details = String.format("""
                %d) %s
                    Price   : %.0fgc
                    Attack  : %+.0f
                    Defence : %+.0f
                    Health  : %+.0f
                    Speed   : %+.0f\n
                """, index, name, price, attack, defence, health, speed);
        System.out.println(details);
    }

    public void upgradeEquipment(Character character) {
        Armour armour;
        Artefacts artefact;
        String options = """
                which type of equipment do you wanna add?

                1) Armour
                2) Artefact

                98) Back
                """;
        while (true) {
            System.out.println(options);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    armour = getArmour();
                    if(armour != null){
                        character.addArmour(armour);
                        currentPlayer.changeGC(0 - (int)armour.getPrice());
                    }
                    
                    break;
                case "2":
                    artefact = getArtefact();
                    if(artefact != null){
                        character.addArtefacts(artefact);
                        currentPlayer.changeGC( - (int)artefact.getPrice());
                    }
                    
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }

    public Armour getArmour() {
        while (true) {
            System.out.println("Select a armour to add\n(It costs the mentiond price to add)\n");
            showEqDetails(1, "Chainmail", Chainmail.PRICE, Chainmail.ATTACK, Chainmail.DEFENCE, Chainmail.HEALTH,
                    Chainmail.SPEED);
            showEqDetails(2, "Regalia", Regalia.PRICE, Regalia.ATTACK, Regalia.DEFENCE, Regalia.HEALTH, Regalia.SPEED);
            showEqDetails(3, "Fleece", Fleece.PRICE, Fleece.ATTACK, Fleece.DEFENCE, Fleece.HEALTH, Fleece.SPEED);
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    if (currentPlayer.getGC() >= Chainmail.PRICE) {
                        System.out.println("Successfully added a chainmail to the character.");
                        return new Chainmail();
                    } else {
                        System.out.println(
                                "Your gold coin balance is insufficient to buy this armour. \nTry another one.");
                    }
                    break;
                case "2":
                    if (currentPlayer.getGC() >= Chainmail.PRICE) {
                        System.out.println("Successfully added a regalia to the character.");
                        return new Regalia();
                    } else {
                        System.out.println(
                                "Your gold coin balance is insufficient to buy this armour. \nTry another one.");
                    }
                    break;

                case "3":
                    if (currentPlayer.getGC() >= Chainmail.PRICE) {
                        System.out.println("Successfully added a regalia to the character.");
                        return new Fleece();
                    } else {
                        System.out.println(
                                "Your gold coin balance is insufficient to buy this armour. \nTry another one.");
                    }
                    break;

                case "98":
                    return null;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }

    public Artefacts getArtefact() {
        while (true) {
            System.out.println("Select a artefact to add\n(It costs the mentiond price to add)\n");
            showEqDetails(1, "Excalibur", Excalibur.PRICE, Excalibur.ATTACK, Excalibur.DEFENCE,
                    Excalibur.HEALTH, Excalibur.SPEED);
            showEqDetails(2, "Amulet", Amulet.PRICE, Amulet.ATTACK, Amulet.DEFENCE, Amulet.HEALTH,
                    Amulet.SPEED);
            showEqDetails(3, "Crystal", Crystal.PRICE, Crystal.ATTACK, Crystal.DEFENCE, Crystal.HEALTH,
                    Crystal.SPEED);
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    if (currentPlayer.getGC() >= Chainmail.PRICE) {
                        System.out.println("Successfully added a chainmail to the character.");
                        return new Excalibur();
                    } else {
                        System.out.println(
                                "Your gold coin balance is insufficient to buy this artefact. \nTry another one.");
                    }
                    break;
                case "2":
                    if (currentPlayer.getGC() >= Chainmail.PRICE) {
                        System.out.println("Successfully added a regalia to the character.");
                        return new Amulet();
                    } else {
                        System.out.println(
                                "Your gold coin balance is insufficient to buy this artefact. \nTry another one.");
                    }
                    break;

                case "3":
                    if (currentPlayer.getGC() >= Chainmail.PRICE) {
                        System.out.println("Successfully added a regalia to the character.");
                        return new Crystal();
                    } else {
                        System.out.println(
                                "Your gold coin balance is insufficient to buy this artefact. \nTry another one.");
                    }
                    break;

                case "98":
                    return null;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }


    public void sellArcher(){
        String userInput;
        Vector<Archer> archers = currentPlayer.getArchers();
        if(archers.isEmpty()){
            System.out.println("You don't have any archer to sell");
        } else {
            for (int i = 0; i < archers.size(); i++){
                System.out.println(String.format("""
                                    %d) %s              %.2f
                                    """,(i+1),
                        String.valueOf(archers.get(i).getClass()).substring(30),
                        archers.get(i).getCurrentValue() * 0.9));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= archers.size()) {
                        Archer sellingChar = archers.get(index - 1);
                        currentPlayer.changeGC((int) (sellingChar.getCurrentValue() * 0.9));
                        if (currentPlayer.getArmy().getArcher() == sellingChar) {
                            currentPlayer.getArmy().removeArcher();
                        }
                        archers.remove(archers.get(index - 1));
                        System.out.println(String.format("You have successfully sold %s for %d",
                                String.valueOf(sellingChar.getClass()).substring(30),
                                (int) sellingChar.getCurrentValue()));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void sellKnight(){
        String userInput;
        Vector<Knight> knights = currentPlayer.getKnights();
        if(knights.isEmpty()){
            System.out.println("You don't have any knight to sell");
        } else {
            for (int i = 0; i < knights.size(); i++){
                System.out.println(String.format("""
                                    %d) %s              %.2f
                                    """,(i+1),
                        String.valueOf(knights.get(i).getClass()).substring(30),
                        knights.get(i).getCurrentValue() * 0.9));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= knights.size()) {
                        Knight sellingChar = knights.get(index - 1);
                        currentPlayer.changeGC((int) (sellingChar.getCurrentValue() * 0.9));
                        if (currentPlayer.getArmy().getKnight() == sellingChar) {
                            currentPlayer.getArmy().removeKnight();
                        }
                        knights.remove(knights.get(index - 1));
                        System.out.println(String.format("You have successfully sold %s for %d",
                                String.valueOf(sellingChar.getClass()).substring(30),
                                (int) sellingChar.getCurrentValue()));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void sellMage(){
        String userInput;
        Vector<Mage> mages = currentPlayer.getMages();
        if(mages.isEmpty()){
            System.out.println("You don't have any mage to sell");
        } else {
            for (int i = 0; i < mages.size(); i++){
                System.out.println(String.format("""
                                    %d) %s              %.2f
                                    """,(i+1),
                        String.valueOf(mages.get(i).getClass()).substring(30),
                        mages.get(i).getCurrentValue() * 0.9));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= mages.size()) {
                        Mage sellingChar = mages.get(index - 1);
                        currentPlayer.changeGC((int) (sellingChar.getCurrentValue() * 0.9));
                        if (currentPlayer.getArmy().getMage() == sellingChar) {
                            currentPlayer.getArmy().removeMage();
                        }
                        mages.remove(mages.get(index - 1));
                        System.out.println(String.format("You have successfully sold %s for %d",
                                String.valueOf(sellingChar.getClass()).substring(30),
                                (int) sellingChar.getCurrentValue()));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void sellHealer(){
        String userInput;
        Vector<Healer> healers = currentPlayer.getHealers();
        if(healers.isEmpty()){
            System.out.println("You don't have any healer to sell");
        } else {
            for (int i = 0; i < healers.size(); i++){
                System.out.println(String.format("""
                                    %d) %s              %.2f
                                    """,(i+1),
                        String.valueOf(healers.get(i).getClass()).substring(30),
                        healers.get(i).getCurrentValue() * 0.9));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= healers.size()) {
                        Healer sellingChar = healers.get(index - 1);
                        currentPlayer.changeGC((int) (sellingChar.getCurrentValue() * 0.9));
                        if (currentPlayer.getArmy().getHealer() == sellingChar) {
                            currentPlayer.getArmy().removeHealer();
                        }
                        healers.remove(healers.get(index - 1));
                        System.out.println(String.format("You have successfully sold %s for %d",
                                String.valueOf(sellingChar.getClass()).substring(30),
                                (int) sellingChar.getCurrentValue()));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }
    public void sellCreature(){
        String userInput;
        Vector<MythicalCreature> creatures = currentPlayer.getCreatures();
        if(creatures.isEmpty()){
            System.out.println("You don't have any mythical creature to sell");
        } else {
            for (int i = 0; i < creatures.size(); i++){
                System.out.println(String.format("""
                                    %d) %s              %.2f
                                    """,(i+1),
                        String.valueOf(creatures.get(i).getClass()).substring(30),
                        creatures.get(i).getCurrentValue() * 0.9));
            }

            System.out.println("98) Back");
            while (true) {
                try {
                    System.out.print("Enter number : ");
                    userInput = scanner.nextLine();
                    int index = Integer.valueOf(userInput);

                    if (userInput.equals("98")) {
                        return;
                    } else if (0 < index  && index <= creatures.size()) {
                        MythicalCreature sellingChar = creatures.get(index - 1);
                        currentPlayer.changeGC((int) (sellingChar.getCurrentValue() * 0.9));
                        if (currentPlayer.getArmy().getMythicalCreature() == sellingChar) {
                            currentPlayer.getArmy().removeCreature();
                        }
                        creatures.remove(creatures.get(index - 1));
                        System.out.println(String.format("You have successfully sold %s for %d",
                                String.valueOf(sellingChar.getClass()).substring(30),
                                (int) sellingChar.getCurrentValue()));
                        return;
                    } else {
                        System.out.println("Enter valid input");
                    }
                } catch (Exception e){
                    System.out.println("Enter valid input");
                }
            }
        }
    }

    public void buyArcher(){
        System.out.println("which one do you wanna buy ?");

        showCharDetails(1, "Shooter", Shooter.initialAttack, Shooter.initialDefence,
                Shooter.initialHealth, Shooter.initialSpeed, Shooter.price);
        showCharDetails(2, "Ranger", Ranger.initialAttack, Ranger.initialDefence,
                Ranger.initialHealth, Ranger.initialSpeed, Ranger.price);
        showCharDetails(3, "Sunfire", Sunfire.initialAttack, Sunfire.initialDefence,
                Sunfire.initialHealth, Sunfire.initialSpeed, Sunfire.price);
        showCharDetails(4, "Zing", Zing.initialAttack, Zing.initialDefence,
                Zing.initialHealth, Zing.initialSpeed, Zing.price);
        showCharDetails(5, "Saggitarius", Saggitarius.initialAttack, Saggitarius.initialDefence,
                Saggitarius.initialHealth, Saggitarius.initialSpeed, Saggitarius.price);
        System.out.println("98) Back");

        Archer archer;

        while (true) {
            archer = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    archer = new Shooter();
                    break;
                case "2":
                    archer = new Ranger();
                    break;
                case "3":
                    archer = new Sunfire();
                    break;
                case "4":
                    archer = new Zing();
                    break;
                case "5":
                    archer = new Saggitarius();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
            if (archer != null) {
                if (currentPlayer.getGC() >= archer.getCurrentValue()) {
                    currentPlayer.getArchers().add(archer);
                    currentPlayer.changeGC((int) (0 - archer.getCurrentValue()));
                    System.out.println(String.format("succussfully add %s to camp",
                            String.valueOf(archer.getClass()).substring(30)));
                    return;
                } else {
                    System.out.println("you don't have enough gc to buy selected Archer.");
                    return;
                }
            }
        }
    }

    public void buyKnight(){

        System.out.println("which one do you wanna buy ?");

        showCharDetails(1, "Squire", Squire.initialAttack, Squire.initialDefence,
                Squire.initialHealth, Squire.initialSpeed, Squire.price);
        showCharDetails(2, "Cavalier", Cavalier.initialAttack, Cavalier.initialDefence,
                Cavalier.initialHealth, Cavalier.initialSpeed, Cavalier.price);
        showCharDetails(3, "Templar", Templar.initialAttack, Templar.initialDefence,
                Templar.initialHealth, Templar.initialSpeed, Templar.price);
        showCharDetails(4, "Zoro", Zoro.initialAttack, Zoro.initialDefence,
                Zoro.initialHealth, Zoro.initialSpeed, Zoro.price);
        showCharDetails(5, "Swiftblade", Swiftblade.initialAttack, Swiftblade.initialDefence,
                Swiftblade.initialHealth, Swiftblade.initialSpeed, Swiftblade.price);
        System.out.println("98) Back");

        Knight knight;

        while (true) {
            knight = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    knight = new Squire();
                    break;
                case "2":
                    knight = new Cavalier();
                    break;
                case "3":
                    knight = new Templar();
                    break;
                case "4":
                    knight = new Zoro();
                    break;
                case "5":
                    knight = new Swiftblade();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
            if (knight != null) {
                if (currentPlayer.getGC() >= knight.getCurrentValue()) {
                    currentPlayer.getKnights().add(knight);
                    currentPlayer.changeGC((int) (0 - knight.getCurrentValue()));
                    System.out.println(String.format("succussfully add %s to camp",
                            String.valueOf(knight.getClass()).substring(30)));
                    return;
                } else {
                    System.out.println("you don't have enough gc to buy selected Archer.");
                    return;
                }
            }
        }
    }

    public void buyMage(){
        System.out.println("which one do you wanna buy ?");

        showCharDetails(1, "Warlock", Warlock.initialAttack, Warlock.initialDefence,
                Warlock.initialHealth, Warlock.initialSpeed, Warlock.price);
        showCharDetails(2, "Illusionist", Illusionist.initialAttack, Illusionist.initialDefence,
                Illusionist.initialHealth, Illusionist.initialSpeed, Illusionist.price);
        showCharDetails(3, "Enchanter", Enchanter.initialAttack, Enchanter.initialDefence,
                Enchanter.initialHealth, Enchanter.initialSpeed, Enchanter.price);
        showCharDetails(4, "Conjurer", Conjurer.initialAttack, Conjurer.initialDefence,
                Conjurer.initialHealth, Conjurer.initialSpeed, Conjurer.price);
        showCharDetails(5, "Eldritch", Eldritch.initialAttack, Eldritch.initialDefence,
                Eldritch.initialHealth, Eldritch.initialSpeed, Eldritch.price);
        System.out.println("98) Back");

        Mage mage;

        while (true) {
            mage = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    mage = new Warlock();
                    break;
                case "2":
                    mage = new Illusionist();
                    break;
                case "3":
                    mage = new Enchanter();
                    break;
                case "4":
                    mage = new Conjurer();
                    break;
                case "5":
                    mage = new Eldritch();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
            if (mage != null) {
                if (currentPlayer.getGC() >= mage.getCurrentValue()) {
                    currentPlayer.getMages().add(mage);
                    currentPlayer.changeGC((int) (0 - mage.getCurrentValue()));
                    System.out.println(String.format("succussfully add %s to camp",
                            String.valueOf(mage.getClass()).substring(30)));
                    return;
                } else {
                    System.out.println("you don't have enough gc to buy selected Archer.");
                    return;
                }
            }
        }
    }

    public void buyHealer(){
        System.out.println("which one do you wanna buy ?");

        showCharDetails(1, "Soother", Soother.initialAttack, Soother.initialDefence,
                Soother.initialHealth, Soother.initialSpeed, Soother.price);
        showCharDetails(2, "Medic", Medic.initialAttack, Medic.initialDefence,
                Medic.initialHealth, Medic.initialSpeed, Medic.price);
        showCharDetails(3, "Alchemist", Alchemist.initialAttack, Alchemist.initialDefence,
                Alchemist.initialHealth, Alchemist.initialSpeed, Alchemist.price);
        showCharDetails(4, "Saint", Saint.initialAttack, Saint.initialDefence,
                Saint.initialHealth, Saint.initialSpeed, Saint.price);
        showCharDetails(5, "Lightbringer", Lightbringer.initialAttack, Lightbringer.initialDefence,
                Lightbringer.initialHealth, Lightbringer.initialSpeed, Lightbringer.price);
        System.out.println("98) Back");

        Healer healer;

        while (true) {
            healer = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    healer = new Soother();
                    break;
                case "2":
                    healer = new Medic();
                    break;
                case "3":
                    healer = new Alchemist();
                    break;
                case "4":
                    healer = new Saint();
                    break;
                case "5":
                    healer = new Lightbringer();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
            if (healer != null) {
                if (currentPlayer.getGC() >= healer.getCurrentValue()) {
                    currentPlayer.getHealers().add(healer);
                    currentPlayer.changeGC((int) (0 - healer.getCurrentValue()));
                    System.out.println(String.format("succussfully add %s to camp",
                            String.valueOf(healer.getClass()).substring(30)));
                    return;
                } else {
                    System.out.println("you don't have enough gc to buy selected Archer.");
                    return;
                }
            }
        }
    }

    public void buyCreature(){
        System.out.println("which one do you wanna buy ?");

        showCharDetails(1, "Dragon", Dragon.initialAttack, Dragon.initialDefence,
                Dragon.initialHealth, Dragon.initialSpeed, Dragon.price);
        showCharDetails(2, "Basilisk", Basilisk.initialAttack, Basilisk.initialDefence,
                Basilisk.initialHealth, Basilisk.initialSpeed, Basilisk.price);
        showCharDetails(3, "Hydra", Hydra.initialAttack, Hydra.initialDefence,
                Hydra.initialHealth, Hydra.initialSpeed, Hydra.price);
        showCharDetails(4, "Phoenix", Phoenix.initialAttack, Phoenix.initialDefence,
                Phoenix.initialHealth, Phoenix.initialSpeed, Phoenix.price);
        showCharDetails(5, "Pegasus", Pegasus.initialAttack, Pegasus.initialDefence,
                Pegasus.initialHealth, Pegasus.initialSpeed, Pegasus.price);
        System.out.println("98) Back");

        MythicalCreature creature;

        while (true) {
            creature = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    creature = new Dragon();
                    break;
                case "2":
                    creature = new Basilisk();
                    break;
                case "3":
                    creature = new Hydra();
                    break;
                case "4":
                    creature = new Phoenix();
                    break;
                case "5":
                    creature = new Pegasus();
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
            if (creature != null) {
                if (currentPlayer.getGC() >= creature.getCurrentValue()) {
                    currentPlayer.getCreatures().add(creature);

                    currentPlayer.changeGC((int) (0-creature.getCurrentValue()));
                    System.out.println(String.format("now you have %d gc",currentPlayer.getGC()));

                    System.out.println(String.format("succussfully add %s to camp",
                            String.valueOf(creature.getClass()).substring(30)));
                    return;
                } else {
                    System.out.println("you don't have enough gc to buy selected Archer.");
                    return;
                }
            }
        }
    }

    public void showCharDetails(int index, String name, float attack, float defence,
            float health, float speed, int price) {
        String details = String.format("""
                index)  name : name
                        attack : %s
                """, attack);
        System.out.println(details);
    }
}
