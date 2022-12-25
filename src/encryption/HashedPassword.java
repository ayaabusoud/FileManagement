package encryption;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
public class HashedPassword implements IEncryptionAndDecryption {
    private static final Logger logger = LogManager.getLogger(HashedPassword.class);

    @Override
    public String encryptAndDecrypt(String plainTextPassword) {
        logger.info("enter encryptAndDecrypt function");
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}