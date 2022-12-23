package signup;

import exceptions.RunTimeException;
import factory.Factory;
import operations.operation.IOperation;
import readDB.CheckUsernameExistences;
import users.User;
import users.UserTypes;
import writeDB.AddUser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Signup {
    public static IOperation signupUser(Connection connection) throws RunTimeException, IOException {
        User reader = new User();
        String username="";
        String password="";
        Scanner sc = new Scanner(System.in);
        boolean usernameIsValid = false;
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

        if(username == null || password == null){
            //throw exception
        }
        reader.setName(username);
        reader.setPassword(password);

        AddUser.addNewUser(connection,reader);
        return  Factory.createUserFunctionality(UserTypes.Reader);
    }

}
