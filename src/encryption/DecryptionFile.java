package encryption;

import database.SqlDatabase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DecryptionFile implements IEncryptionAndDecryption {

    private static final Logger logger = LogManager.getLogger(DecryptionFile.class);

    @Override
    public String encryptAndDecrypt(String fileName) {
        logger.info("enter encryptAndDecrypt function for " + fileName);
        return fileName.substring(0,fileName.length()-1);
    }
}
