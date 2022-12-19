package writeDB;


import encryption.EncryptionFile;
import file.FileInfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class AddFile {

    public static void addNewFile(Connection connection,String tableName, InputStream inputStream, FileInfo newFile) throws SQLException, IOException {
        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  EncryptionFile.encryption(newFile.getName()));
        preparedStmt.setString (2, newFile.getType());
        preparedStmt.setInt(3, inputStream.available()); // Calculate size
        preparedStmt.setBlob(4,inputStream);
        preparedStmt.setInt(5, newFile.getVersion());
        preparedStmt.setInt(6, newFile.getLastVersion());
        preparedStmt.setInt(7, newFile.getVersionType());
        preparedStmt.execute();
    }
    public static void addNewFile(Connection connection,String tableName, FileInfo newFile) throws SQLException, IOException {
        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";

        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  EncryptionFile.encryption(newFile.getName()));
        preparedStmt.setString (2, newFile.getType());
        preparedStmt.setInt(3, newFile.getSize());
        preparedStmt.setBlob(4,newFile.getContext());
        preparedStmt.setInt(5, newFile.getVersion());
        preparedStmt.setInt(6, newFile.getLastVersion());
        preparedStmt.setInt(7, newFile.getVersionType());
        preparedStmt.execute();
    }
}
