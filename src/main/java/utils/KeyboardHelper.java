package utils;

import java.util.Scanner;

public class KeyboardHelper {
    public static String saisi(String message){
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine();
    }
}
