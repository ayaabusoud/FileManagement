package readDB;

import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckExistence {
    public static boolean isExists(Connection connection,String table, String username) throws SqlQueryException {
        try {
            String query = "SELECT * FROM "+table+" WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()){
                return true;
            }
            return false;
        }  catch (SQLException e) {
            throw new SqlQueryException("Check Existences Query Failed");
        }

    }
}
