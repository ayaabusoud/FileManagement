package authnetication;

import org.mindrot.jbcrypt.BCrypt;

public abstract class CheckingPassword {
    public static boolean checkUserPassword(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }

}