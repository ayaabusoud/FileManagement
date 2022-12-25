package readDB;

import application.Main;
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

public abstract class FileExistences {

    private static final Logger logger = LogManager.getLogger(FileExistences.class);

    public static boolean isExist(Connection connection, FileInformation newFile) throws SqlQueryException {
        logger.debug("Enter to isExist with args => "+ connection + newFile);
        String query = "SELECT name FROM file WHERE name = ? and type = ?";
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        PreparedStatement preparedStmt = null;
        ResultSet result;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1,EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            result = preparedStmt.executeQuery();
            logger.info("Query executed");
            logger.debug("Exit to FileExistences");
            if (result.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new SqlQueryException("Check File isExist Query Failed");
        }

    }

}
