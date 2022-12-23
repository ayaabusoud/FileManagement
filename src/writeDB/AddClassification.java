package writeDB;

import encryption.EncryptionFile;
import exceptions.RunTimeException;
import file.FileInfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddClassification {
    public static void addNewClassification(Connection connection, String[] classificationAttributes) throws RunTimeException, IOException {
        String query = "INSERT INTO classification (name,context,formattedContext) values (?,?,?)";
        try
        {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  classificationAttributes[0]);
            preparedStmt.setString (2, classificationAttributes[1]);
            preparedStmt.setString (3, classificationAttributes[2]);
            preparedStmt.execute();
        }
        catch (SQLException e) {
            throw new RunTimeException("Add Classification Query Failed");
        }
    }
}