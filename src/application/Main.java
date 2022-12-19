package application;

import factory.Factory;
import operations.operation.IOperation;
import users.UserTypes;
import writeDB.AddClassification;
import classification.aya.CreateClassification;
import readDB.DisplayClassifications;
import classification.aya.ReadByClass;
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
                    break;
                default:
                    //exception
                    System.out.println("Not Implemented yet");
                    break;
            }

    }while (displayMenu);
    }
}
