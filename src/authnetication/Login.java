package authnetication;

import factory.Factory;
import factory.IFactory;
import menu.AuthenticationMenu;
import operations.operation.IOperation;
import users.UserTypes;

import java.sql.Connection;
import java.util.Scanner;

import static variables.Variables.*;

public class Login implements IAuthentication{

    private static final int AdminKey = 111;
    private static final int StaffKey = 123;
    public IOperation authUser(Connection connection){
        IFactory factory = new Factory();
        IOperation userAccess = null;
        int key;
        Scanner sc = new Scanner(System.in);
        AuthenticationMenu.loginMenu();
        int userType = sc.nextInt();
        switch (userType){
            case 1:
                CheckingLoginKey.checkUserKey(AdminKey);
                AdminUser = true;
                userAccess = factory.createUserFunctionality(UserTypes.Admin);
                break;
            case 2:
                CheckingLoginKey.checkUserKey(StaffKey);
                StaffUser = true;
                userAccess = factory.createUserFunctionality(UserTypes.Staff);
                break;
            case 3:
                userAccess = ReaderLogin.authUser(connection);
                break;
            default:
                System.out.println("invalid number, please re-enter");
        }
        return userAccess;
    }




}