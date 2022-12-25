package writeDB;

import encryption.HashedPassword;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import users.UserInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserAddition {
    private static final Logger logger = LogManager.getLogger(UserAddition.class);
    public static void addNewUser(Connection connection, UserInformation newReader) throws SqlQueryException {
        String query = "INSERT INTO user (name,password) values (?,?)";
        logger.debug("enter addNewUser function");
        IEncryptionAndDecryption HashedPassword = new HashedPassword() ;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  newReader.getName());
            preparedStmt.setString (2, HashedPassword.encryptAndDecrypt(newReader.getPassword()));
            preparedStmt.execute();
        }
        catch (SQLException e) {
            throw new SqlQueryException("Add New User Query Failed");
        }
        logger.debug("enter "+newReader.getName()+" as new user in the database");
        System.out.println("successfully added");
        logger.debug("exit addNewUser function");
    }
}