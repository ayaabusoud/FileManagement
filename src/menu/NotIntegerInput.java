package menu;

import exceptions.NotIntegerException;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class NotIntegerInput {
    public static int scanInteger(int authChoice)throws NotIntegerException {
        Scanner sc = new Scanner(System.in);
        try {
            authChoice = sc.nextInt();
            return authChoice;
        }catch (InputMismatchException e){
            throw new NotIntegerException("The input is not an integer");
        }
    }
}
