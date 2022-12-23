package signup;

import exceptions.SqlQueryException;
import factory.Factory;
import operations.operation.IOperation;
import readDB.CheckUsernameExistences;
import users.User;
import users.UserTypes;
import writeDB.AddUser;

import java.io.IOException;
import java.sql.Connection;
import java.util.Scanner;

public class Signup {
    public static IOperation signupUser(Connection connection) {
        User reader = new User();
        String username="";
        String password="";
        Scanner sc = new Scanner(System.in);
        boolean usernameIsValid = false;
        try {
        while (!usernameIsValid){
        System.out.print("Enter username: ");
        username = sc.next();
        if(CheckUsernameExistences.isExists(connection,username)){
            System.out.println("The username is exists, please choose another one: ");
        }else {
            usernameIsValid = true;
        }
        }
        System.out.print("Enter you password: ");
        password = sc.next();
        reader.setName(username);
        reader.setPassword(password);
        AddUser.addNewUser(connection,reader);

        } catch (SqlQueryException e) {
        System.out.println(e.getMessage());
        }
        return  Factory.createUserFunctionality(UserTypes.Reader);
    }

}
