package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFile {
    public static void readFiles(Connection connection, String[]fileAttribute) throws SQLException {

        String query ="SELECT * FROM file WHERE "+fileAttribute[0]+" = ? AND lastVersion = 1";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1, fileAttribute[1]);
        ResultSet result = preparedStmt.executeQuery();
        while (result.next()){
            System.out.println(DecryptionFile.decryption(result.getString("name"))+"."+result.getString("type")+": ");
            System.out.println(result.getString("context"));
            System.out.println("----------------");
        }
    }
}