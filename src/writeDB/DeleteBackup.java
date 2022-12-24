package writeDB;

import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBackup {

    public static void deleteFile(Connection connection, FileInfo file ) throws SqlQueryException {
        String QUERY = "DELETE FROM backup WHERE name = ? AND type = ? AND version = ?";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.setInt(3, file.getVersion());
            preparedStmt.execute();
            System.out.println("AAAAAAA");
        } catch (SQLException e) {
            throw new SqlQueryException("Delete Backup Query Failed");
        }

    }
}