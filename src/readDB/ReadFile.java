package readDB;

import encryption.DecryptionFile;
import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFile {
    public static void readFiles(Connection connection, String[]fileAttribute)  throws SqlQueryException {
        try{
            String query ="SELECT * FROM file WHERE "+fileAttribute[0]+" = ? AND lastVersion = 1";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, fileAttribute[1]);
            ResultSet result = preparedStmt.executeQuery();
            if (!result.next()){
                System.out.println("There is no such files in the system.");
            }
            while (result.next()){
                System.out.println(DecryptionFile.decryption(result.getString("name"))+"."+result.getString("type")+": ");
                System.out.println(result.getString("context"));
                System.out.println("----------------");
            }
        }
        catch (SQLException e) {
            throw new SqlQueryException("Read File Query Failed ");
        }


    }
}