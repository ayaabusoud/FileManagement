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


    public abstract class BackupInfo {
        private static final Logger logger = LogManager.getLogger(BackupInfo.class);

        private final static String QUERY = "SELECT * FROM backup WHERE name = ? AND type = ? AND version = ?";

        public static FileInformation getInfo(Connection connection, FileInformation file) throws SqlQueryException {
            logger.debug("Enter to getInfo function");
            PreparedStatement preparedStmt = null;
            IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
            ResultSet result;
            try {
                preparedStmt = connection.prepareStatement(QUERY);
                preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
                preparedStmt.setString(2, file.getType());
                preparedStmt.setInt(3, file.getVersion() - 1);
                result = preparedStmt.executeQuery();

                logger.debug("get info query executed for "+file.getName()+"."+file.getType()
                        +", version: "+file.getVersion());
                return InfoAboutFile.getInfo(file,result);
            } catch (SQLException e) {
                throw new SqlQueryException("Get Backup Info Query Failed");
            }

        }
}
