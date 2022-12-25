package authnetication;

import application.Main;
import exceptions.NotIntegerException;
import factory.OperationFactory;
import factory.IFactory;
import menu.AuthenticationMenu;
import menu.NotIntegerInput;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import users.IUser;
import users.UserTypes;

import java.sql.Connection;
import java.util.Scanner;

import static variables.Variables.*;

public class Login implements IAuthentication{
    private static final Logger logger = LogManager.getLogger(Login.class);

    private static final int ADMIN_KEY = 111;
    private static final int STAFF_KEY = 123;
    public IUser authUser(Connection connection){
        IFactory factory = new OperationFactory();
        IUser userAccess = null;
        int key;
        boolean userTypeLoop = false;
        Scanner sc = new Scanner(System.in);
        AuthenticationMenu.loginMenu();
        int userType = 0;
        do {
            try {
                userType = NotIntegerInput.scanInteger(userType);

            }catch (NotIntegerException e){
                userTypeLoop = true;
                System.err.println(e.getMessage());
            }
        switch (userType){
            case 1:
                CheckingLoginKey.checkUserKey(ADMIN_KEY);
                adminUser = true;
                userAccess = factory.create(UserTypes.Admin);
                break;
            case 2:
                CheckingLoginKey.checkUserKey(STAFF_KEY);
                staffUser = true;
                userAccess = factory.create(UserTypes.Staff);
                break;
            case 3:
                userAccess = ReaderLogin.authUser(connection);
                break;
            default:
                System.out.println("invalid number, please re-enter");
        }
        }while (userTypeLoop);
        return userAccess;
    }




}