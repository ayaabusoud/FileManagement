package authnetication;

import java.util.Scanner;

public abstract class CheckingLoginKey {
    public static void checkUserKey(int Userkey){
        Scanner sc = new Scanner(System.in);
        int key ;
        System.out.print("enter your key, please: ");
        key = sc.nextInt();
        while (key != Userkey)
        {
            System.out.print("The key you entered is incorrect,please re-enter: ");
            key = sc.nextInt();
        }
    }
}