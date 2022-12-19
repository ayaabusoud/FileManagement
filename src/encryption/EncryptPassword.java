package encryption;

import org.mindrot.jbcrypt.BCrypt;

public class EncryptPassword {
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}