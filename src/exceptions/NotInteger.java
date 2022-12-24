package exceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NotInteger {
    public static int scanInteger(int authChoice){
        Scanner sc = new Scanner(System.in);
        try {
            authChoice = sc.nextInt();
            return authChoice;
        }catch (InputMismatchException e){
            throw new NotIntegerException("The input is not an integer");
        }
    }
}
