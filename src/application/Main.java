package application;

import database.IDatabase;
import database.SqlDatabase;
import factory.Factory;
import login.Login;
import menue.OperationMenu;
import operations.operation.IOperation;
import signup.Signup;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static users.UserTypes.*;
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        IDatabase sqlDatabase =  SqlDatabase.createInstance();
        Connection connection= sqlDatabase.connectDB();

        Scanner sc = new Scanner(System.in);
        boolean displayMenu = true;
        int userMenuChoice;
        IOperation functionality = null;
        int userType = 0;

       OperationMenu.MainMenu();
        int authChoice = sc.nextInt();
        do {
            switch (authChoice){
                case 1:
                    Signup.signupUser(connection);
                    displayMenu = false;
                    userType = 3;
                    break;
                case 2:
                    userType = Login.loginUser(connection);
                    displayMenu =false;
                    break;
                default:
                    System.out.println("Please enter only 1 to log in, or 2 to sing up.");
                    displayMenu =false;
                    break;
            }
        }while (displayMenu);


        switch (userType)
        {
            case 1:
                functionality = Factory.createUserFunctionality(Admin);
                break;
            case 2:
                functionality = Factory.createUserFunctionality(Staff);
                break;
            case 3:
                functionality = Factory.createUserFunctionality(Reader);
                break;
        }

        displayMenu = true;
        System.out.println("Enter your type");
        do{

            switch (userType)
            {
                case 1:
                    OperationMenu.adminMenu();
                    break;
                case 2:
                    OperationMenu.staffMenu();
                    break;
                case 3:
                    OperationMenu.readerMenu();
                    break;
            }
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
                    System.out.println("Enter file name.type");
                    String fileNameAndType = sc.next();
                    functionality.rollBack(connection,fileNameAndType);
                    break;
                default:
                    //exception
                    System.out.println("Not Implemented yet");
                    break;
            }
        }while (displayMenu);
    }
}
