package application;

import database.IDatabase;
import database.SqlDatabase;
import exceptions.NotInteger;
import exceptions.NotIntegerException;
import login.Login;
import menue.OperationMenu;
import operations.operation.IOperation;
import org.omg.CORBA.TIMEOUT;
import signup.Signup;
import variables.Variables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        IDatabase sqlDatabase = SqlDatabase.createInstance();
        Connection connection = sqlDatabase.connectDB();


        Scanner sc = new Scanner(System.in);
        boolean displayMenu = true;
        boolean displayOperationMenu = true;
        int userMenuChoice;
        IOperation functionality = null;
        int authChoice = 0;

        System.out.println("Welcome to our File Management Application");
        do {

            try {
            System.out.println("1.Signup");
            System.out.println("2.Login");
            System.out.print("choose Operation number: ");
               authChoice = NotInteger.scanInteger(authChoice);
            }catch (NotIntegerException e) {
            System.err.println(e.getMessage());
            Thread.sleep(100);
                continue;
            }


            switch (authChoice){


                case 1:
                    Signup.signupUser(connection);
                    Variables.readerUser = true;
                    displayMenu =false;
                    break;
                case 2:
                    displayMenu =false;
                    functionality= Login.loginUser(connection);
                    break;
                default:
                    System.out.println("invalid input, try again");
                    break;
            }

        }while (displayMenu);




        do {
            if(Variables.AdminUser){
                OperationMenu.adminMenu();
            }else if(Variables.StaffUser){
                OperationMenu.staffMenu();
            }else if(Variables.readerUser) {
                OperationMenu.readerMenu();
            }

            try {
                System.out.print("choose Operation number: ");
                userMenuChoice = NotInteger.scanInteger(authChoice);
            }catch (NotIntegerException e) {
                System.err.println(e.getMessage());
                Thread.sleep(100);
                continue;
            }

            System.out.println("----------------------");
            switch (userMenuChoice){
                case 0:
                    sqlDatabase.closeDB(connection);
                    displayOperationMenu =false;
                    break;
                case 1:
                    functionality.readFiles(connection);
                    break;
                case 2:
                    System.out.print("Enter file path: ");
                    String filePath = sc.next();
                    functionality.importFiles(connection,filePath);
                    break;
                case 3:
                    System.out.print("Enter file.type: ");
                    String fileAndType = sc.next();
                    functionality.rollBack(connection,fileAndType);
                    break;
                case 4:
                   System.out.print("Enter file: ");
                    String fileName = sc.next();
                   functionality.exportFile(connection,fileName);
                   break;
                case 5:
                    functionality.createClassification(connection);
                    break;
                case 6:
                    functionality.deleteFiles(connection);
                    break;
                default:
                    //exception
                    System.out.println("Not Implemented yet");
                    break;
            }


        }while (displayOperationMenu);
    }
}