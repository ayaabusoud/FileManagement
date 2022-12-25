package encryption;

import database.SqlDatabase;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DecryptionFile implements IEncryptionAndDecryption {

    private static final Logger logger = LogManager.getLogger(DecryptionFile.class);

    @Override
    public String encryptAndDecrypt(String fileName) {
        logger.debug("enter encryptAndDecrypt function, fileName before decryption: "+fileName);
        String decryption = fileName.substring(0,fileName.length()-1);
        logger.debug("fileName after decryption: "+decryption);
        return decryption;
    }
}
