package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


    public class GetBackupInfo {
        private final static String QUERY = "SELECT * FROM backup WHERE name = ? AND type = ? AND version = ?";

        public static FileInfo getInfo(Connection connection, FileInfo file) throws SQLException {
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString (1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString (2, file.getType());
            preparedStmt.setInt (3, file.getVersion()-1);
            ResultSet result = preparedStmt.executeQuery();

            if(result.next()){
                file.setName(DecryptionFile.decryption(result.getString("name")));
                file.setType(result.getString("type"));
                file.setContext(result.getBlob("context"));
                file.setVersion(result.getInt("version"));
                file.setSize(result.getString("size"));
                file.setVersionType(result.getInt("versionType"));
            }
            return file;
        }
}
