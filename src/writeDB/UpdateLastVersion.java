package writeDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UpdateLastVersion {

    public static void updateToZero(Connection connection,String tableName, FileInformation newFile) throws SqlQueryException {
        String QUERY ="Update "+ tableName +" SET lastVersion = 0 WHERE name = ? AND type = ?";
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            int result = preparedStmt.executeUpdate();
            } catch (SQLException e) {
                throw new SqlQueryException("Update Latest Version To Zero Query Failed");
            }
    }
    public static void updateToOne(Connection connection,String tableName, FileInformation newFile) throws SqlQueryException {
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            String QUERY ="Update "+tableName +"SET lastVersion = 1 WHERE name = ? AND type = ? AND version = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            preparedStmt.setInt(3, newFile.getVersion()-1);
            int result = preparedStmt.executeUpdate();
           } catch (SQLException e) {
            throw new SqlQueryException("Update Latest Version To One Query Failed");
        }


    }
}
