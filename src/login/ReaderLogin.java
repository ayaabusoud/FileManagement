package login;

import exceptions.SqlQueryException;
import factory.Factory;
import operations.operation.IOperation;
import readDB.GetUserPassword;
import users.UserTypes;

import javax.naming.CompositeName;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static login.CheckPassword.checkPass;
import static variables.Variables.readerUser;

public class ReaderLogin {
    public static IOperation login(Connection connection){
        IOperation userAccess = null;
        Scanner sc = new Scanner(System.in);
        boolean notValid = true;
        do {
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter you password: ");
            String password = sc.next();
            String hashedPassword = null;
            try {
                hashedPassword = GetUserPassword.getPassword(connection,username);
            } catch (SqlQueryException e) {
                System.err.println(e.getMessage());
            }
            boolean passwordIsValid = checkPass(password, hashedPassword);
            if (passwordIsValid) {
                userAccess = Factory.createUserFunctionality(UserTypes.Reader);
                readerUser = true;
                notValid =false;
            }else {
                System.out.println("Invalid name or password, try again..");
            }
        }while (notValid);
        return userAccess;
    }
}
