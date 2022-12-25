package encryption;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EncryptionFile implements IEncryptionAndDecryption {
    private static final Logger logger = LogManager.getLogger(EncryptionFile.class);
    @Override
    public String encryptAndDecrypt(String name) {
        logger.info("enter encryptAndDecrypt function, fileName before encryption: "+name);
        return name+"_";
    }
}
