package writeDB;

import encryption.EncryptionFile;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateLastVersion {


    public static void updateToZero(Connection connection,String tableName, FileInfo newFile) throws SQLException {
        String QUERY ="Update "+tableName +" SET lastVersion = 0 WHERE name = ? AND type = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
        preparedStmt.setString(1, EncryptionFile.encryption(newFile.getName()));
        preparedStmt.setString(2, newFile.getType());
         int result = preparedStmt.executeUpdate();
    }
    public static void updateToOne(Connection connection,String tableName, FileInfo newFile) throws SQLException {
        String QUERY ="Update "+tableName +" SET lastVersion = 1 WHERE name = ? AND type = ? AND version = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
        preparedStmt.setString(1, EncryptionFile.encryption(newFile.getName()));
        preparedStmt.setString(2, newFile.getType());
        preparedStmt.setInt(3, newFile.getVersion()-1);
        int result = preparedStmt.executeUpdate();
    }
}
