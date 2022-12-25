package readDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class PreviousFileExistence {
    private static final Logger logger = LogManager.getLogger(PreviousFileExistence.class);

    public static boolean isExists(Connection connection, FileInformation file) throws SqlQueryException {
        logger.debug("Enter to isExist function");
        String query = "SELECT name FROM file WHERE name = ? and type = ? and version = ?";
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        ResultSet result;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.setInt(3, file.getVersion() - 1);
            result = preparedStmt.executeQuery();
            logger.debug("getInfo file query executed for version: "+(file.getVersion() - 1)
                    +"of "+file.getName()+"."+file.getType());
            logger.debug("PreviousFileExistence Exited");
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new SqlQueryException("Check previous Version Is Exist Query Failed");
        }
    }
}
