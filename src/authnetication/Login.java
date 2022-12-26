package authnetication;

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
        logger.debug(" Call the authUser function in class Login");
        IFactory factory = new OperationFactory();
        IUser userAccess = null;
        boolean userTypeLoop = false;
        AuthenticationMenu.loginMenu();
        int userType = 0;
        do {
            try {
                userType = NotIntegerInput.scanInteger(userType);
                logger.debug("User Enter its type "+ userType);
                userTypeLoop =false;
            }catch (NotIntegerException e){
                userTypeLoop = true;
                System.err.println(e.getMessage());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                logger.debug("Wrong entry, must be entered int, and user enter >> "+ userType);
            }
            switch (userType){
                case 1:
                    CheckingLoginKey.checkUserKey(ADMIN_KEY);
                    adminUser = true;
                    userAccess = factory.create(UserTypes.Admin);
                    logger.debug("Successfully logged in as an ADMIN");
                    break;
                case 2:
                    CheckingLoginKey.checkUserKey(STAFF_KEY);
                    staffUser = true;
                    userAccess = factory.create(UserTypes.Staff);
                    logger.debug("Successfully logged in as an Staff");
                    break;
                case 3:
                    userAccess = ReaderLogin.authUser(connection);
                    logger.debug("Successfully logged in as an Reader");
                    break;
                default:
                    System.out.print("invalid number, please re-enter: ");
                    userTypeLoop = true;
            }
        }while (userTypeLoop);
        logger.debug("Close the authUser function");
        return userAccess;
    }




}