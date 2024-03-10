package MysticMayhem.Controllers;

import java.util.*;

import MysticMayhem.Player;
import MysticMayhem.UIs.CLI;

public class GameController {
    static int UI_id_num;
    static Scanner stdin;

    private static ArrayList<String> plyrs = new ArrayList<String>();
    private static ArrayList<String> pwds = new ArrayList<String>();
    
    private static String inputStr="";
    private static Player currentPlayer;

    static Player tempPlayer;

    private static void print(String phrase) {
        System.out.println(phrase);
    }

    public static void setInput(Scanner scnr){
        stdin = scnr;
    }

    public static void setPlayers(){
        plyrs.add("player-1");
        plyrs.add("player-2");
        pwds.add("player-1");
        pwds.add("player-2");
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

    // start the console
    public static void start(){
        setPlayers();
        inputStr = CLI.display(stdin, "UI00");
        switch (inputStr) {
            case "1":
                LogIn();
                break;
            case "2":
                createAccount();
                break;
            case "Q":
            case "q":
                quitGame();
                break;
            default:
                System.out.println("Nothing special");

        }
    }

    public static void quitGame(){
        print("Are you sure, You want to quit the game? (Y/N)");
        inputStr = stdin.nextLine();
        if(inputStr.equals("Y") || inputStr.equals("y")){
            return;
        }else{
            start();
        }
    }

    public static void LogIn(){
        String plyr;
        int userIndex;
        while(true){
            print("Enter your User Name");
            inputStr = stdin.nextLine();
            if(isTakenUserName(inputStr)){
                tempPlayer = Player.getPlayers().get(inputStr);
                break;
            }else{
                print("Invalid User Name. Try it again.\n");
            }
        }
        
        while(true){
            print("Enter your Password");
            inputStr = stdin.nextLine();
            

            if(inputStr.equals(tempPlayer.getPwd())){
                currentPlayer = tempPlayer;
                break;
            }else{
                print("Incorrect Password. Try it again.");
            }
        }
        System.out.print("Loading player data [     ]");
        try{
            Thread.sleep(500);
        }catch(Exception e){
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [#    ]");
        try{
            Thread.sleep(500);
        }catch(Exception e){
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [##   ]");
        try{
            Thread.sleep(500);
        }catch(Exception e){
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [###  ]");
        try{
            Thread.sleep(500);
        }catch(Exception e){
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [#### ]");
        try{
            Thread.sleep(500);
        }catch(Exception e){
            print(e.getMessage());
        }
        System.out.print("\rLoading player data [#####]");
        try{
            Thread.sleep(500);
        }catch(Exception e){
            print(e.getMessage());
        }
        
    }

    public static void createAccount(){
        int userIndex;
        String name, uName, pwd;
        print("Enter your name:");
        name = stdin.nextLine();
        while(true){
            print("Enter your username:");
            inputStr = stdin.nextLine();
            if(!isTakenUserName(inputStr)){
                uName = inputStr;
                break;
            }else{
                print("User Name already exists. Please use another username.\n");
            }
        }

        print("Enter a password for your account:");
        pwd = stdin.nextLine();
        new Player(name, uName, pwd);
        print("Successfully created a new account..");
        start();
    }

    
}
