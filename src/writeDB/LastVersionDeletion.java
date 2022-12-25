package writeDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class LastVersionDeletion {
    private static final Logger logger = LogManager.getLogger(LastVersionDeletion.class);
    final static String QUERY = "DELETE FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static void deleteFile(Connection connection, FileInformation file) throws SqlQueryException {
        logger.debug("enter deleteFile function");
        PreparedStatement preparedStmt = null;
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.execute();
            logger.debug("delete "+file.getName()+"."+file.getType()+" last version");
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Latest Version Query Failed");
        }
        logger.debug("exit deleteFile function");
    }
}