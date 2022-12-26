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

public class BackupFileAddition {
    private static final Logger logger = LogManager.getLogger(FileAddition.class);
    public static void addNewFile(Connection connection, String tableName
            , FileInformation newFile) throws SqlQueryException {
        logger.debug("enter addNewFile function");
        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString (2, newFile.getType());
            preparedStmt.setString(3, newFile.getSize());
            preparedStmt.setBlob(4,newFile.getContext());
            preparedStmt.setInt(5, newFile.getVersion());
            preparedStmt.setInt(6, newFile.getLastVersion());
            preparedStmt.setInt(7, newFile.getVersionType());
            preparedStmt.execute();
            logger.debug("insert file query "+newFile.getName()+"."+newFile.getType()+"version: "
                    + newFile.getVersion()+", size: "+newFile.getSize());
        }catch (SQLException e) {
            throw new SqlQueryException(" Add New File Query Failed... ");
        }
        logger.debug("exit addNewFile function");
    }
}
