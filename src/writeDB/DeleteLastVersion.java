package writeDB;

import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteLastVersion {

    final static String QUERY = "DELETE FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static void deleteFile(Connection connection, FileInfo file) throws SqlQueryException {

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Latest Version Query Failed");
        }

    }
}