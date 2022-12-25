package readDB;

import application.Main;
import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class FileInfo {
    private static final Logger logger = LogManager.getLogger(FileInfo.class);
    private final static String QUERY = "SELECT * FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static FileInformation getInfo(Connection connection, FileInformation file) throws SqlQueryException {
        logger.debug("Enter into FileInformation function" );
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        ResultSet result;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
            preparedStmt.setString(2, file.getType());
            result = preparedStmt.executeQuery();
            logger.debug("getInfo file query executed for last version of "
                    +file.getName()+"."+file.getType());
            logger.debug("Exit to FileInfo");
            return InfoAboutFile.getInfo(file,result);
        } catch (SQLException e) {
            throw new SqlQueryException("Get File Info Query Failed ");
        }

    }
}