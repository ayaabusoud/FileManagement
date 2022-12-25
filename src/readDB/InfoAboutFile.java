package readDB;

import encryption.DecryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class InfoAboutFile {
    private static final Logger logger = LogManager.getLogger(InfoAboutFile.class);

    public static FileInformation getInfo(FileInformation file, ResultSet result) throws SqlQueryException {
        logger.debug("Enter to getInfo with args => " + file + result);
        IEncryptionAndDecryption decryption = new DecryptionFile() ;
        try {
        if (result.next()) {
           file.setName(decryption.encryptAndDecrypt(result.getString("name")));
            file.setType(result.getString("type"));
            file.setContext(result.getBlob("context"));
            file.setVersion(result.getInt("version"));
            file.setSize(result.getString("size"));
            file.setVersionType(result.getInt("versionType"));
        }
            logger.debug("InfoAboutFile Exited");
    }catch (SQLException e) {
            throw new SqlQueryException("failed to get file data");
        }
        return file;
    }
}
