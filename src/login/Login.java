package login;


import menue.OperationMenu;
import org.mindrot.jbcrypt.BCrypt;
import readDB.CheckUsernameExistences;
import signup.Signup;
import variables.Variables;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static variables.Variables.*;

public class Login {
    private final static String QUERY = "SELECT password FROM user WHERE name = ?";
    private static boolean checkPass(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    public static int loginUser(Connection connection) throws SQLException, IOException {
        String username="";
        String password="";
        int key;
        Scanner sc = new Scanner(System.in);
        boolean usernameIsValid = false;
        boolean passwordIsValid = true;
        int accountType;
        OperationMenu.loginMenu();
        accountType = sc.nextInt();
        switch (accountType)
        {
            case 1:
                System.out.println("enter your key, please.");
                key = sc.nextInt();
                while (key != AdminKey)
                {
                    System.out.println("The key you entered is incorrect, re-enter.");
                }
                AdminUser = true;
                return accountType;
            case 2:
                System.out.println("enter your key, please.");
                key = sc.nextInt();
                while (key != StaffKey)
                {
                    System.out.println("The key you entered is incorrect, re-enter.");
                    key = sc.nextInt();
                }
                return accountType;
            case 3:
                while (!usernameIsValid){
                    System.out.print("Enter username: ");
                    username = sc.next();
                    if(CheckUsernameExistences.isExists(connection,username)){
                        usernameIsValid = true;
                    }else {
                        System.out.println("The username is not exists, please enter the correct name ");
                    }
                }
                while (passwordIsValid)
                {
                    System.out.print("Enter you password: ");
                    password = sc.next();

                    PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
                    preparedStmt.setString (1, username);
                    ResultSet result = preparedStmt.executeQuery();
                    String hashedPassword = result.getString("password");
                    passwordIsValid= checkPass(password,hashedPassword);

                    if (passwordIsValid)
                        System.out.println("The password matches.");
                    else
                        System.out.println("The password does not match. Enter the password again");
                }
                return accountType;
            case 4:
                Signup.signupUser(connection);
                break;
            default:
                System.out.println("Error, re-enter");
        }
        if(username == null || password == null){
            //throw exception
        }
        return accountType;
    }
}
