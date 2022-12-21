package login;

import java.util.Scanner;

public class CheckLoginKey {
    public static void checkKey(int Userkey){
        Scanner sc = new Scanner(System.in);
        int key ;
        System.out.print("enter your key, please.");
        key = sc.nextInt();
        while (key != Userkey)
        {
            System.out.print("The key you entered is incorrect, re-enter.");
            key = sc.nextInt();
        }
    }
}