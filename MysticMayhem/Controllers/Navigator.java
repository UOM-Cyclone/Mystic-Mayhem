package MysticMayhem.Controllers;

import MysticMayhem.Characters.*;
import MysticMayhem.Grounds.*;
import MysticMayhem.Player;

import java.util.Scanner;

public class Navigator {

    Player currentPlayer;
    Scanner scanner = new Scanner(System.in);

    public void start(){
        Player test = new Player("dev","dev", new Marshland());

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
        }while (true);
    }

    public void login(){

        do {
            System.out.print("If you want go back enter '-1' \nEnter your username : ");
            String userInput = scanner.nextLine();

            if(userInput.equals("-1")){
                return;
            } else {
                if (Player.players.containsKey(userInput)) {
                    currentPlayer = Player.players.get(userInput);
                    Menu();
                    return;
                }
                System.out.println("\nEnter valid username. ");
            }
        }while (true);
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
    public void Menu(){
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
                    System.out.println("ddd");

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
        }while (true);
    }
    public void showProfile(){


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
                                    "Enter Y/N :", currentPlayer.getName(), newName)
                    );
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
        }while (true);
    }


    public void armyMenu(){
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
                    System.out.println("ddd");
                    break;
                case "2":
                    buyCharacter();
                    break;
                case "3":
                    System.out.println("eee");
                    break;
                case "4":
                    System.out.println("eee");
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }while (true);
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

        while (true){
            System.out.println(details);
            System.out.print("Select the option : ");
            String userInput = scanner.nextLine();

            switch (userInput){
                case "1":
                    buyArcher();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "98":
                    return;
                default:
                    System.out.println("Please enter the valid input.");
                    break;
            }
        }
    }

    public void buyArcher(){
        System.out.println("which one do you wanna buy ?");

        showCharDetails(1,"Shooter", Shooter.initialAttack, Shooter.initialDefence,
                Shooter.initialHealth,Shooter.initialSpeed,Shooter.price);
        showCharDetails(2,"Ranger", Ranger.initialAttack, Ranger.initialDefence,
                Ranger.initialHealth, Ranger.initialSpeed,Ranger.price);
        showCharDetails(3,"Sunfire", Sunfire.initialAttack, Sunfire.initialDefence,
                Sunfire.initialHealth,Sunfire.initialSpeed,Sunfire.price);
        showCharDetails(4,"Zing", Zing.initialAttack, Zing.initialDefence,
                Zing.initialHealth,Zing.initialSpeed,Zing.price);
        showCharDetails(5,"Saggitarius", Saggitarius.initialAttack, Saggitarius.initialDefence,
                Saggitarius.initialHealth,Saggitarius.initialSpeed,Saggitarius.price);
        System.out.println("98) Back");

        Archer archer ;

        while (true){
            archer = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput){
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
            if (archer != null){
                if(currentPlayer.getGC() >= archer.getCurrentValue()){
                    currentPlayer.getArchers().add(archer);
                    currentPlayer.changeGC((int) (0-archer.getCurrentValue()));
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
        currentPlayer = new Player("dev", "dev", new Arcane());
        System.out.println("which one do you wanna buy ?");

        showCharDetails(1,"Squire", Squire.initialAttack, Squire.initialDefence,
                Squire.initialHealth,Squire.initialSpeed,Squire.price);
        showCharDetails(2,"Cavalier", Cavalier.initialAttack, Cavalier.initialDefence,
                Cavalier.initialHealth, Cavalier.initialSpeed,Cavalier.price);
        showCharDetails(3,"Templar", Templar.initialAttack, Templar.initialDefence,
                Templar.initialHealth,Templar.initialSpeed,Templar.price);
        showCharDetails(4,"Zoro", Zoro.initialAttack, Zoro.initialDefence,
                Zoro.initialHealth,Zoro.initialSpeed,Zoro.price);
        showCharDetails(5,"Swiftblade", Swiftblade.initialAttack, Swiftblade.initialDefence,
                Swiftblade.initialHealth,Swiftblade.initialSpeed,Swiftblade.price);
        System.out.println("98) Back");

        Knight knight ;

        while (true){
            knight = null;
            System.out.print("Enter the number : ");
            String userInput = scanner.nextLine();

            switch (userInput){
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
            if (knight != null){
                if(currentPlayer.getGC() >= knight.getCurrentValue()){
                    currentPlayer.getKnights().add(knight);
                    currentPlayer.changeGC((int) (0-knight.getCurrentValue()));
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

    public void showCharDetails(int index, String name, float attack, float defence,
                                float health, float speed, int price){
        String details = String.format("""
                index)  name : name
                        attack : %s
                """, attack);
        System.out.println(details);
    }


}
