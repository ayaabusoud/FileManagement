package authnetication;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

public abstract class CheckingPassword {
    private static final Logger logger = LogManager.getLogger(Login.class);

    public static boolean checkUserPassword(String plainPassword, String hashedPassword) {
        logger.debug("Call the checkUserPassword function");

        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            logger.debug("the password is matching");
            return true;
        }
        else {
            logger.debug("not matching");
            return false;
        }
    }

}