package login;

import factory.Factory;
import menu.AuthenticationMenu;
import menu.OperationMenu;
import operations.operation.IOperation;
import readDB.GetUserPassword;
import signup.Signup;
import users.UserTypes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static login.CheckPassword.checkPass;
import static variables.Variables.*;

public class Login {

    private static final int AdminKey = 111;
    private static final int StaffKey = 123;
    public static IOperation loginUser(Connection connection) throws SQLException {
        IOperation userAccess = null;
        int key;
        Scanner sc = new Scanner(System.in);
        AuthenticationMenu.loginMenu();
        int userType = sc.nextInt();
        switch (userType){
            case 1:
                CheckLoginKey.checkKey(AdminKey);
                AdminUser = true;
                userAccess = Factory.createUserFunctionality(UserTypes.Admin);
                break;
            case 2:
                CheckLoginKey.checkKey(StaffKey);
                StaffUser = true;
                userAccess = Factory.createUserFunctionality(UserTypes.Staff);
                break;
            case 3:
                boolean notValid = true;
                do {
                    System.out.print("Enter username: ");
                    String username = sc.next();
                    System.out.print("Enter you password: ");
                    String password = sc.next();
                    String hashedPassword = GetUserPassword.getPassword(connection,username);
                    boolean passwordIsValid = checkPass(password, hashedPassword);
                    if (passwordIsValid) {
                        userAccess = Factory.createUserFunctionality(UserTypes.Reader);
                        readerUser = true;
                        notValid =false;
                    }else {
                        System.out.println("Invalid name or password, try again..");
                    }
                }while (notValid);
                break;
            case 4:
                try {
                    Signup.signupUser(connection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                readerUser = true;
                break;
            default:
                System.out.println("invalid number, please re-enter");
        }
        return userAccess;
    }




}