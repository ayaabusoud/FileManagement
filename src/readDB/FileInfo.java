package readDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.*;

public abstract class FileInfo {
    private final static String QUERY = "SELECT * FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static FileInformation getInfo(Connection connection, FileInformation file) throws SqlQueryException {
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        ResultSet result;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(file.getName()));
            preparedStmt.setString(2, file.getType());
            result = preparedStmt.executeQuery();
            return InfoAboutFile.getInfo(file,result);
        } catch (SQLException e) {
            throw new SqlQueryException("Get File Info Query Failed ");
        }

    }
}