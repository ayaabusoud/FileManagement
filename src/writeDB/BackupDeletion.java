package writeDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BackupDeletion {

    public static void deleteFile(Connection connection, FileInformation file ) throws SqlQueryException {
        String QUERY = "DELETE FROM backup WHERE name = ? AND type = ? AND version = ?";
        PreparedStatement preparedStmt = null;
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.setInt(3, file.getVersion());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Backup Query Failed");
        }

    }
}