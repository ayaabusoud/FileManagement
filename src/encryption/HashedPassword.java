package encryption;

import org.mindrot.jbcrypt.BCrypt;
public class HashedPassword implements IEncryptionAndDecryption {
    @Override
    public String encryptAndDecrypt(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

    }
}