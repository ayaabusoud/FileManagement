package writeDB;

import encryption.EncryptionFile;
import exceptions.RunTimeException;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBackup {
    final static String QUERY = "DELETE FROM backup WHERE name = ? AND type = ? AND version = ?";

    public static void deleteFile(Connection connection, FileInfo file) throws RunTimeException {

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.setInt(3, file.getVersion());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RunTimeException("Delete Backup Query Failed");
        }

    }
}