package encryption;

public class DecryptionFile implements IEncryptionAndDecryption {
    @Override
    public String encryptAndDecrypt(String fileName) {
        return fileName.substring(0,fileName.length()-1);
    }
}
