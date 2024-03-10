package MysticMayhem.UIs;

import java.util.Scanner;
import java.io.*;

public class CLI {
    public static void print(String line){
        System.out.println(line);
    }

    public static String display(Scanner stdin, String UI_id){
        print(System.getProperty("user.dir"));
        try{
            Scanner inputFile = new Scanner(new File(System.getProperty("user.dir")+"\\MysticMayhem\\UIs\\"+UI_id+".txt"));
            while(inputFile.hasNextLine()){
                print(inputFile.nextLine());
            }
            inputFile.close();
            
        }catch(FileNotFoundException err){
            print(err.getMessage());
        }
        String phrase = stdin.nextLine();
        return phrase;
    }
}
