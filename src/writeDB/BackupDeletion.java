package writeDB;

import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BackupDeletion {

    public static void deleteFile(Connection connection, FileInformation file ) throws SqlQueryException {
        String QUERY = "DELETE FROM backup WHERE name = ? AND type = ? AND version = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptFile(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.setInt(3, file.getVersion());
            preparedStmt.execute();
            System.out.println("AAAAAAA");
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Backup Query Failed");
        }

    }
}