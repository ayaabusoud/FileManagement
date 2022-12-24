package encryption;

public class DecryptionFile implements IEncrAndDecrption {
    @Override
    public String IncAndDec(String fileName) {
        return fileName.substring(0,fileName.length()-1);
    }
}
