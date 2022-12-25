package authnetication;

import exceptions.SqlQueryException;
import factory.OperationFactory;
import factory.IFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import users.IUser;
import readDB.UserPassword;
import users.UserTypes;

import java.sql.Connection;
import java.util.Scanner;

import static authnetication.CheckingPassword.checkUserPassword;
import static variables.Variables.readerUser;

public abstract class ReaderLogin{
    private static final Logger logger = LogManager.getLogger(ReaderLogin.class);

    public static IUser authUser(Connection connection){
        logger.debug(" Call the authUser function in class ReaderLogin");
        IFactory factory =new OperationFactory();
        IUser userAccess = null;
        Scanner sc = new Scanner(System.in);
        boolean notValid = true;
        do {
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter you password: ");
            String password = sc.next();
            String hashedPassword = null;
            logger.debug("User Enter his name and password" + username);
            try {
                hashedPassword = UserPassword.getPassword(connection,username);
                logger.debug("Call the function getPassword, to retrieve the password from the data base");
            } catch (SqlQueryException e) {
                System.err.println(e.getMessage());
            }
            boolean passwordIsValid = checkUserPassword(password, hashedPassword);
            logger.debug("Verify that the password matches");

            if (passwordIsValid) {
                userAccess = factory.create(UserTypes.Reader);
                readerUser = true;
                notValid =false;
                logger.debug("Successfully logged in as an Reader");
            }else {
                logger.debug("Wrong entering username or password");
                System.out.println("Invalid name or password, try again..");
            }
        }while (notValid);
        logger.debug("Close the authUser function");
        return userAccess;
    }
}
