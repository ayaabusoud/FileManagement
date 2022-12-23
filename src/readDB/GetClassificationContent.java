package readDB;

import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetClassificationContent {
    public static String[] get(Connection connection, String className)throws SqlQueryException {
        String []context = new String[2];
        String query ="SELECT * FROM classification WHERE name = ?";
        try
        {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  className);
            ResultSet result = preparedStmt.executeQuery();
            if(result.next()){
                context = result.getString("context").split(",");
            }
            return context;
        }
        catch (SQLException e) {
            throw new SqlQueryException("Get Classification Content Query ");
        }
    }
}