package writeDB;

import encryption.EncryptionFile;
import exceptions.RunTimeException;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteLastVersion {

    final static String QUERY = "DELETE FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static void deleteFile(Connection connection, FileInfo file) throws RunTimeException {

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.execute();
        } catch (SQLException e) {
            throw new RunTimeException("Delete Latest Version Query Failed");
        }

    }
}