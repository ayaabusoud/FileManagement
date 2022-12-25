package writeDB;

import encryption.HashedPassword;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import users.UserInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserAddition {
    public static void addNewUser(Connection connection, UserInformation newReader) throws SqlQueryException {
        String query = "INSERT INTO user (name,password) values (?,?)";
        IEncryptionAndDecryption HashedPassword = new HashedPassword() ;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  newReader.getName());
            preparedStmt.setString (2, HashedPassword.encryptAndDecrypt(newReader.getPassword()));
            preparedStmt.execute();
            System.out.println("successfully added");
        }
        catch (SQLException e) {
            throw new SqlQueryException("Add New User Query Failed");
        }
    }
}