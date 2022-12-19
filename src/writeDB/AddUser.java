package writeDB;

import encryption.EncryptPassword;
import encryption.EncryptionFile;
import file.FileInfo;
import users.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {
    public static void addNewUser(Connection connection, User newReader) throws SQLException, IOException {
        String query = "INSERT INTO user (name,password) values (?,?)";

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  newReader.getName());
        preparedStmt.setString (2, EncryptPassword.hashPassword(newReader.getPassword()));
        preparedStmt.execute();
        System.out.println("successfully added");
    }
}