package readDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetClassificationContent {
    public static String[] get(Connection connection, String className) throws SQLException {
        String []context = new String[2];
        String query ="SELECT * FROM classification WHERE name = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  className);
        ResultSet result = preparedStmt.executeQuery();
        if(result.next()){
            context = result.getString("context").split(",");
        }
        return context;
    }
}