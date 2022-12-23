package readDB;

import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckClassificationExistences {
    public static boolean isExist(Connection connection , String fileName) throws SqlQueryException {
        try
        {
            String query ="SELECT name FROM classification WHERE name = ? ";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,fileName);
            ResultSet result = preparedStmt.executeQuery();
            if (result.next()) {
                return true;
            }
            return false;
        }
        catch (SQLException e) {
            throw new SqlQueryException("Check Classification Existences Query filed");
        }

    }
}
