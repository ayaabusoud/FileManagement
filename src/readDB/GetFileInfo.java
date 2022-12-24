package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInfo;

import java.sql.*;

public class GetFileInfo {
    private final static String QUERY = "SELECT * FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static FileInfo getInfo(Connection connection, FileInfo file) throws SqlQueryException {
        ResultSet result;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString(2, file.getType());
            result = preparedStmt.executeQuery();
            return GetInfo.getInfo(file,result);
        } catch (SQLException e) {
            throw new SqlQueryException("Get File Info Query Failed ");
        }

    }
}