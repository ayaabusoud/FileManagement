package readDB;

import encryption.EncryptionFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckClassificationExistences {
    public static boolean isExist(Connection connection , String fileName) throws SQLException {
        String query ="SELECT name FROM classification WHERE name = ? ";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,fileName);
        ResultSet result = preparedStmt.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }
}
