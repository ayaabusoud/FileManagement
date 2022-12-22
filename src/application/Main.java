package application;

import database.IDatabase;
import database.SqlDatabase;
import exceptions.NotAllowedOperationException;
import exceptions.NotInteger;
import exceptions.NotIntegerException;
import exceptions.connectionMySqlException;
import factory.Factory;
import login.Login;
import menu.AuthenticationMenu;
import menu.OperationMenu;
import operations.operation.IOperation;
import signup.Signup;
import users.UserTypes;
import variables.Variables;

import java.sql.Connection;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {
        IDatabase sqlDatabase = SqlDatabase.createInstance();
        Connection connection = null;
        Scanner sc = new Scanner(System.in);
        boolean displayMenu = true;
        boolean displayOperationMenu = true;
        int userMenuChoice;
        IOperation functionality = null;
        int authChoice = 0;

        try {
             connection = sqlDatabase.connectDB();
        }catch (connectionMySqlException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }

        System.out.println("Welcome to our File Management Application");
        do {
            try {
                AuthenticationMenu.AuthMenu();
               authChoice = NotInteger.scanInteger(authChoice);
            }catch (NotIntegerException e) {
            System.err.println(e.getMessage());
            Thread.sleep(100);
                continue;
            }
            switch (authChoice){
                case 1:
                    functionality = Signup.signupUser(connection);
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
                    try {
                        functionality.readFiles(connection);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        functionality.importFiles(connection);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter file.type: ");
                        String fileAndType = sc.next();
                        functionality.rollBack(connection,fileAndType);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Enter file: ");
                        String fileName = sc.next();
                        functionality.exportFile(connection,fileName);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                   break;
                case 5:
                    try {
                        functionality.createClassification(connection);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        functionality.deleteFiles(connection);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid input, choose another operation number");
                    break;
            }
        }while (displayOperationMenu);
    }
}