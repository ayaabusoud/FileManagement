package authnetication;

import exceptions.SqlQueryException;
import factory.OperationFactory;
import factory.IFactory;
import users.IUser;
import readDB.UserPassword;
import users.UserTypes;

import java.sql.Connection;
import java.util.Scanner;

import static authnetication.CheckingPassword.checkUserPassword;
import static variables.Variables.readerUser;

public abstract class ReaderLogin{
    public static IUser authUser(Connection connection){
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
            try {
                hashedPassword = UserPassword.getPassword(connection,username);
            } catch (SqlQueryException e) {
                System.err.println(e.getMessage());
            }
            boolean passwordIsValid = checkUserPassword(password, hashedPassword);
            if (passwordIsValid) {
                userAccess = factory.create(UserTypes.Reader);
                readerUser = true;
                notValid =false;
            }else {
                System.out.println("Invalid name or password, try again..");
            }
        }while (notValid);
        return userAccess;
    }
}
