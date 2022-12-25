package application;

import authnetication.IAuthentication;
import database.IDatabase;
import database.SqlDatabase;
import exceptions.*;
import authnetication.Login;
import menu.AuthenticationMenu;
import menu.NotIntegerInput;
import menu.OperationMenu;
import users.IUser;
import authnetication.Signup;
import variables.Variables;

import java.sql.Connection;
import java.util.Scanner;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args){

        IDatabase sqlDatabase = SqlDatabase.createInstance();
        Connection connection = null;
        Scanner sc = new Scanner(System.in);
        boolean displayMenu = true;
        boolean displayOperationMenu = true;
        int userMenuChoice;
        IUser functionality = null;
        int authChoice = 0;
        IAuthentication authUser = null;

        try {
             connection = sqlDatabase.connectDB();
        }catch (ConnectionMySqlException e){
            System.err.println(e.getMessage());
            System.exit(0);
        }

        System.out.println("Welcome to our File Management Application");
        do {
            try {
                AuthenticationMenu.AuthMenu();
               authChoice = NotIntegerInput.scanInteger(authChoice);
            }catch (NotIntegerException e) {
            System.err.println(e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                continue;
            }
            switch (authChoice){
                case 1:
                    authUser = new Signup();
                    functionality = authUser.authUser(connection);
                    Variables.readerUser = true;
                    displayMenu =false;
                    break;
                case 2:
                    displayMenu =false;
                    authUser = new Login();
                    functionality = authUser.authUser(connection);
                    break;
                default:
                    System.out.println("invalid input, try again");
                    break;
            }
        }while (displayMenu);

        do {
            if(Variables.adminUser){
                OperationMenu. adminMenu();
            }else if(Variables.staffUser){
                OperationMenu.staffMenu();
            }else if(Variables.readerUser) {
                OperationMenu.readerMenu();
            }

            try {
                System.out.print("choose Operation number: ");
                userMenuChoice = NotIntegerInput.scanInteger(authChoice);
            }catch (NotIntegerException e) {
                System.err.println(e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                continue;
            }
            System.out.println("----------------------");
            switch (userMenuChoice){
                case 0:
                    try {
                        sqlDatabase.closeDB(connection);
                    } catch (ConnectionMySqlException e) {
                       System.err.println(e.getMessage());
                    }
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
                        functionality.rollBack(connection);
                    }catch (NotAllowedOperationException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        functionality.exportFile(connection);
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