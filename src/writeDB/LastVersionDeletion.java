package writeDB;

import encryption.EncryptionFile;
import encryption.IEncrAndDecrption;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class LastVersionDeletion {
    final static String QUERY = "DELETE FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static void deleteFile(Connection connection, FileInformation file) throws SqlQueryException {
        PreparedStatement preparedStmt = null;
        IEncrAndDecrption EncryptionFile = new EncryptionFile();
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.IncAndDec(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Latest Version Query Failed");
        }

    }
}