package encryption;

public class EncryptionFile implements IEncryptionAndDecryption {
    @Override
    public String encryptAndDecrypt(String name) {
        return name+"_";
    }
}
