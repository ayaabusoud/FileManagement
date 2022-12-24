package readDB;

import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FileExistences {
    public static boolean isExist(Connection connection, String tableName, FileInformation newFile) throws SqlQueryException {
        String query = "SELECT name FROM " + tableName + " WHERE name = ? and type = ?";
        PreparedStatement preparedStmt = null;
        ResultSet result;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, EncryptionFile.encryptFile(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            result = preparedStmt.executeQuery();
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new SqlQueryException("Check File isExist Query Failed");
        }

    }

}
