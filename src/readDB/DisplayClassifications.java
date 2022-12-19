package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayClassifications {
    public static void display(Connection connection) throws SQLException {
        String query ="SELECT * FROM classification";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        ResultSet result = preparedStmt.executeQuery();

        while (result.next()){
            System.out.println(result.getString("name")+"    "+result.getString("formattedContext"));
        }
    }
}