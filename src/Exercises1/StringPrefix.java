package Exercises1;

import java.util.stream.IntStream;

/**
 * Write a method which returns "TRUE" if String str1 is prefix of String str2
 * It's not allowed to use the built-in methods of String class for iterating, searching or etc.
 * The only method you can use is charAt()
 */

public class StringPrefix {

    public static void main(String[] args) {
        String str1 = "Arg";
        String str2 = "ArgDev";

        //Testing isPrefix() method without streams
        System.out.println(str1 + " is prefix of " + str2 + " -> " + isPrefix(str1, str2));

        //Testing isPrefixStream() method with streams
        System.out.println(str1 + " is prefix of " + str2 + " -> " + isPrefixStream(str1, str2));
    }

    public static boolean isPrefix(String str1, String str2){
        if(str1.length() > str2.length()){
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)){
                return false;
            }
        }
        return true;
    }

    //Using streams
    public static boolean isPrefixStream(String str1, String str2){
        if(str1.length() > str2.length()){
            return false;
        }
        return IntStream.range(0, str1.length())
                .allMatch(i -> str1.charAt(i) == str2.charAt(i));
    }

}
