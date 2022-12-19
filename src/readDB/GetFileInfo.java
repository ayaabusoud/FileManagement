package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import file.FileInfo;

import java.sql.*;

public class GetFileInfo {
    private final static String QUERY = "SELECT * FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static FileInfo getInfo(Connection connection, FileInfo file) throws SQLException {
        PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
        preparedStmt.setString (1, EncryptionFile.encryption(file.getName()));
        preparedStmt.setString (2, file.getType());
        ResultSet result = preparedStmt.executeQuery();

        if(result.next()){
            file.setName(DecryptionFile.decryption(result.getString("name")));
            file.setType(result.getString("type"));
            file.setContext(result.getBlob("context"));
            file.setVersion(result.getInt("version"));
            file.setSize(result.getInt("size"));
            file.setVersionType(result.getInt("versionType"));
        }
        return file;
    }
}