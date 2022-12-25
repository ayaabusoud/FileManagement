package operations.read;

import encryption.DecryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import readDB.FilesAttributesMatching;
import readDB.FilesClassificationMatching;
import variables.Variables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FileReading {
    public static void readFiles(Connection connection, String[] fileAttribute, String readType) throws SqlQueryException {
        ResultSet result = null;
        IEncryptionAndDecryption decryption = new DecryptionFile() ;
        try {
            if (readType.equals(Variables.BY_CLASSIFICATION)) {
                result = FilesClassificationMatching.getFiles(connection, fileAttribute);
            } else {
                result = FilesAttributesMatching.getFiles(connection, fileAttribute);
            }
            while (result.next()) {
                //*****
                System.out.println(decryption.encryptAndDecrypt(result.getString("name")) + "." + result.getString("type") + ": ");
                System.out.println(result.getString("context"));
                System.out.println("----------------");
            }
        } catch (SQLException e) {
            throw new SqlQueryException("Read File Query Failed ");
        }
    }
}
