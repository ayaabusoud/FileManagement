package encryption;

import org.mindrot.jbcrypt.BCrypt;
public class HashedPassword implements IEncrAndDecrption{
    @Override
    public String IncAndDec(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

    }
}