
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class NewAlphabet {


    static HashMap<String,String> dictionary =  new HashMap<>();

    public static void main(String[] args){
        //Loading file with rules into a hashmap
        makeDictionary();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        translateString(input);
    }

    public static void translateString(String input){
        String output ="";
        for(char c: input.toCharArray()){

            String toAdd = null;
            String current = ""+c;
            if( (toAdd = dictionary.get(current.toLowerCase()) )!= null ){
                output += toAdd;
            }else{
                output += c;
            }
        }
        System.out.println(output);

    }


    private static void makeDictionary(){

        dictionary.put("a","@");
        dictionary.put("b","8");
        dictionary.put("c","(");
        dictionary.put("d","|)");
        dictionary.put("e","3");
        dictionary.put("f","#");
        dictionary.put("g","6");
        dictionary.put("h","[-]");
        dictionary.put("i","|");
        dictionary.put("j","_|");
        dictionary.put("k","|<");
        dictionary.put("l","1");
        dictionary.put("m","[]\\/[]");
        dictionary.put("n","[]\\[]");
        dictionary.put("o","0");
        dictionary.put("p","|D");
        dictionary.put("q","(,)");
        dictionary.put("r","|Z");
        dictionary.put("s","$");
        dictionary.put("t","']['");
        dictionary.put("u","|_|");
        dictionary.put("v","\\/");
        dictionary.put("w","\\/\\/");
        dictionary.put("x","}{");
        dictionary.put("y","`/");
        dictionary.put("z","2");

    }


   // Tried to use  a file but could only upload one class file!
    /*
    private static void makeDictionary(){


        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "DataFiles/NewAlphabetDict/data.tsv"));
            String line = reader.readLine();
            while (line != null) {
                storeInDict(line);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        /*

    }

    /*
    private static void storeInDict(String line){


        String[] tokens = line.split("\t");
        System.out.println("Putting in:" + tokens[0]+ "    =    "  +tokens[1]);
        dictionary.put(tokens[0],tokens[1]);
        System.out.printf(dictionary.get(tokens[0]));


    }
    */

}
