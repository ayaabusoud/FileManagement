package readDB;

import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.*;

public abstract class GetFileInfo {
    private final static String QUERY = "SELECT * FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static FileInformation getInfo(Connection connection, FileInformation file) throws SqlQueryException {
        ResultSet result;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptFile(file.getName()));
            preparedStmt.setString(2, file.getType());
            result = preparedStmt.executeQuery();
            return InfoAboutFile.getInfo(file,result);
        } catch (SQLException e) {
            throw new SqlQueryException("Get File Info Query Failed ");
        }

    }
}