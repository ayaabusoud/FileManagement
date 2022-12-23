package writeDB;

import encryption.EncryptPassword;
import exceptions.SqlQueryException;
import users.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {//
    public static void addNewUser(Connection connection, User newReader) throws SqlQueryException {
        String query = "INSERT INTO user (name,password) values (?,?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  newReader.getName());
            preparedStmt.setString (2, EncryptPassword.hashPassword(newReader.getPassword()));
            preparedStmt.execute();
            System.out.println("successfully added");
        }
        catch (SQLException e) {
            throw new SqlQueryException("Add New User Query Failed");
        }
    }
}