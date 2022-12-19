package application;

import factory.Factory;
import operations.operation.IOperation;
import signup.Signup;
import users.UserTypes;
import database.IDatabase;
import database.SqlDatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        IDatabase sqlDatabase =  SqlDatabase.createInstance();
        Connection connection= sqlDatabase.connectDB();

        Scanner sc = new Scanner(System.in);
        boolean displayMenu = true;
        int userMenuChoice;

        System.out.println("Welcome to our File Management Application");
        System.out.println("1.Signup");
        System.out.println("2.Login");
        System.out.print("choose Operation number: ");
        int authChoice = sc.nextInt();
        switch (authChoice){
            case 1:
                Signup.signupUser(connection);
                break;
            case 2:
//                login
                break;
            default:
//                invalid
                break;
        }

        //login
        IOperation functionality = Factory.createUserFunctionality(UserTypes.Admin);

        do{
        //login
        Menu.adminMenu();

        System.out.print("choose Operation number: ");
        userMenuChoice = sc.nextInt();
        System.out.println("----------------------");
            switch (userMenuChoice){
                case 0:
                    sqlDatabase.closeDB(connection);
                    displayMenu =false;
                    break;
                case 2:
                    System.out.print("Enter file path: ");
                    String filePath = sc.next();
                    functionality.importFiles(connection,filePath);
                    break;
                case 3:
                    System.out.print("Enter name.type: ");
                    String fileNameAndType = sc.next();
                    functionality.rollBack(connection,fileNameAndType);
                    break;
                case 5:
                    functionality.createClassification(connection);
                    break;
                default:
                    //exception
                    System.out.println("Not Implemented yet");
                    break;
            }

    }while (displayMenu);
    }
}
