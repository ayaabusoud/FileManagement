package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


    public class GetBackupInfo {
        private final static String QUERY = "SELECT * FROM backup WHERE name = ? AND type = ? AND version = ?";

        public static FileInfo getInfo(Connection connection, FileInfo file) throws SqlQueryException {
            PreparedStatement preparedStmt = null;
            ResultSet result;
            try {
                preparedStmt = connection.prepareStatement(QUERY);
                preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
                preparedStmt.setString(2, file.getType());
                preparedStmt.setInt(3, file.getVersion() - 1);
                result = preparedStmt.executeQuery();
                return GetInfo.getInfo(file,result);
            } catch (SQLException e) {
                throw new SqlQueryException("Get Backup Info Query Failed");
            }

        }
}
