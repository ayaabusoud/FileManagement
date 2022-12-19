package readDB;

import encryption.EncryptionFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckUsernameExistences {
    public static boolean isExists(Connection connection, String username) throws SQLException {
        String query ="SELECT * FROM user WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString (1, username);
        ResultSet result =  preparedStatement.executeQuery();
        if(result.next()){
            return true;
        }
        return false;

    }
}