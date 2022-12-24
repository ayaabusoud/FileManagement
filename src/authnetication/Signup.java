package authnetication;

import exceptions.SqlQueryException;
import factory.Factory;
import factory.IFactory;
import operations.operation.IOperation;
import readDB.CheckExistence;
import users.User;
import users.UserTypes;
import variables.Variables;
import writeDB.UserAddition;

import java.sql.Connection;
import java.util.Scanner;

public class Signup implements IAuthentication{
    public IOperation authUser(Connection connection) {
        IFactory factory = new Factory();
        User reader = new User();
        String username="";
        String password="";
        Scanner sc = new Scanner(System.in);
        boolean usernameIsValid = false;
        try {
        while (!usernameIsValid){
        System.out.print("Enter username: ");
        username = sc.next();
        if(CheckExistence.isExists(connection, Variables.USER_TABLE,username)){
            System.out.println("The username is exists, please choose another one: ");
        }else {
            usernameIsValid = true;
        }
        }
        System.out.print("Enter you password: ");
        password = sc.next();
        reader.setName(username);
        reader.setPassword(password);
        UserAddition.addNewUser(connection,reader);

        } catch (SqlQueryException e) {
        System.out.println(e.getMessage());
        }
        return  factory.createUserFunctionality(UserTypes.Reader);
    }

}
