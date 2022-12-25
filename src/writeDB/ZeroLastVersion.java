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

public abstract class ZeroLastVersion {
    private static final Logger logger = LogManager.getLogger(ZeroLastVersion.class);
    public static void updateToZero(Connection connection,String tableName
            , FileInformation newFile) throws SqlQueryException {
        logger.debug("enter updateToZero function");
        String QUERY ="Update "+ tableName +" SET lastVersion = 0 WHERE name = ? AND type = ?";
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            int result = preparedStmt.executeUpdate();
            logger.debug("update "+newFile.getName()+"."+newFile.getType()+" last version to zero");
        } catch (SQLException e) {
            throw new SqlQueryException("Update Latest Version To Zero Query Failed");
        }
        logger.debug("exit updateToZero function");
    }

}