package writeDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;
import operations.rollback.Rollback;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BackupDeletion {
    private static final Logger logger = LogManager.getLogger(BackupDeletion.class);
    public static void deleteFile(Connection connection, FileInformation file ) throws SqlQueryException {
        logger.debug("enter deleteFile function");
        String QUERY = "DELETE FROM backup WHERE name = ? AND type = ? AND version = ?";
        PreparedStatement preparedStmt = null;
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.setInt(3, file.getVersion());
            preparedStmt.execute();
            logger.debug("delete file with name: "+file.getName()+"type: "+file.getType()+"version: "+file.getVersion());
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Backup Query Failed");
        }
        logger.debug("exit deleteFile function");
    }
}