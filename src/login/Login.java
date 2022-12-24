package login;

import exceptions.SqlQueryException;
import factory.Factory;
import menu.AuthenticationMenu;
import operations.operation.IOperation;
import readDB.GetUserPassword;
import signup.Signup;
import users.UserTypes;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

import static login.CheckPassword.checkPass;
import static variables.Variables.*;

public class Login {

    private static final int AdminKey = 111;
    private static final int StaffKey = 123;
    public static IOperation loginUser(Connection connection){
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
                userAccess = ReaderLogin.login(connection);
                break;
            case 4:
                Signup.signupUser(connection);
                readerUser = true;
                break;
            default:
                System.out.println("invalid number, please re-enter");
        }
        return userAccess;
    }




}