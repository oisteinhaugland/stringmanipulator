package com.logiq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;


/**************************
 * METADATA               *
 **************************
 * Expected file dataformat
 * COMMAND:STRING\n
 *************************/


/**************************
 * CONTRIBUTION           *
 **************************
 * Add command to enum
 * Create functions
 * Update String manipulator
 *************************/

public class Main {

    public static void main(String[] args) {
        File file = new File("src/actiontexts.txt");
        stringManipulator(file);
    }

    public  enum COMMANDS {
        PALINDROME,
        WHITESPACE,
        REVERSE,
        UNIQUE_COUNT,
        CAPITALIZE
    }


    public static void stringManipulator(File file){

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {

                String command = extractCommand(line);
                String str = extractString(line);

                switch(COMMANDS.valueOf(command)) {
                    case PALINDROME:
                        Boolean bool = checkPalindrome(str);
                        System.out.println(outputBuilder(str,COMMANDS.PALINDROME.toString(),bool.toString()));
                        break;
                    case WHITESPACE:
                        String white = removeWhiteSpace(str);
                        System.out.println(outputBuilder(str,COMMANDS.WHITESPACE.toString(),white));
                        break;
                    case REVERSE:
                        String rev = reverseString(str);
                        System.out.println(outputBuilder(str,COMMANDS.REVERSE.toString(),rev));
                        break;
                    case UNIQUE_COUNT:
                        String num = countUniqueChars(str).toString();
                        System.out.println(outputBuilder(str,COMMANDS.UNIQUE_COUNT.toString(),num));
                        break;
                    case CAPITALIZE:
                        String cap = capitalizeStr(str);
                        System.out.println(outputBuilder(str,COMMANDS.CAPITALIZE.toString(),cap));
                        break;
                    default:
                        System.out.println(-1);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /*************************
     * Helper methods        *
     ************************/

    public static String extractCommand(String str){
        return str.split(":")[0];
    }


    public static String extractString(String str){
        return str.split(":")[1];
    }


    public static String outputBuilder(String text, String action, String result){
        String output = "Text: [{0}] ran Action: [{1}] with result: [{2}]";
        return MessageFormat.format(output, text, action, result);
    }


    /*************************
     * Manipulations         *
     ************************/

    public static boolean checkPalindrome(String str){
        return str == reverseString(str);
    }


    public static Long countUniqueChars(String str){
        return str.chars().distinct().count();
    }


    public static String capitalizeStr(String str){
        return str.toUpperCase();
    }


    public static String reverseString(String str){
        ArrayList<String> reversedSplit = new ArrayList<>();
        String[] split = str.split("");

        for (int i = split.length - 1; i >= 0; i--){
            reversedSplit.add(split[i]);
        }

        String reversedStr = String.join("",reversedSplit);
        return reversedStr;
    }


    public static String removeWhiteSpace(String str){
        return str.replaceAll("\\s","");
    }
}