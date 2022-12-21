package login;

import org.mindrot.jbcrypt.BCrypt;

public class CheckPassword {
    public static boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword))
            return true;
        else
            return false;
    }

}