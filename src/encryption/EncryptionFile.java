package encryption;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EncryptionFile implements IEncryptionAndDecryption {
    private static final Logger logger = LogManager.getLogger(EncryptionFile.class);
    @Override
    public String encryptAndDecrypt(String name) {
        logger.debug("enter encryptAndDecrypt function,fileName before encryption: "+name);
        String encryption = name+"_";
        logger.info("fileName after encryption: "+encryption);
        return encryption;
    }
}
