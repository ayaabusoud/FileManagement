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
public class OneLastVersion {
    private static final Logger logger = LogManager.getLogger(OneLastVersion.class);

    public static void updateToOne(Connection connection, String tableName
            , FileInformation newFile) throws SqlQueryException {
        logger.debug("enter updateToOne function");
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            String QUERY ="Update "+tableName +" SET lastVersion = 1 WHERE name = ? AND type = ? AND version = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            preparedStmt.setInt(3, newFile.getVersion()-1);
            int result = preparedStmt.executeUpdate();
            logger.debug("update "+newFile.getName()+"."+newFile.getType()+"version: "
                    + (newFile.getVersion() - 1)+" last version to zero");
        } catch (SQLException e) {
            throw new SqlQueryException("Update Latest Version To One Query Failed");
        }
        logger.debug("exit updateToOne function");



    }
}
