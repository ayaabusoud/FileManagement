package encryption;

public class EncryptionFile implements IEncrAndDecrption {
    @Override
    public String IncAndDec(String name) {
        return name+"_";
    }
}
