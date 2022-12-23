package writeDB;

import encryption.DecryptionFile;
import exceptions.RunTimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteFiles {
    public static void deleteFiles(Connection connection, String[]fileAttribute)throws RunTimeException {
        try
        {
            String query ="DELETE FROM file WHERE "+fileAttribute[0]+" = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, fileAttribute[1]);
            preparedStmt.execute();
        }
        catch (SQLException e) {
            throw new RunTimeException("Delete Files Query Failed");
        }
    }
}