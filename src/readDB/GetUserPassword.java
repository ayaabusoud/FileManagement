package readDB;

import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserPassword {
    public static String getPassword(Connection connection, String username) throws SqlQueryException {
        ResultSet result;
        try {
            String QUERY = "SELECT * FROM user WHERE name = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, username);
            result = preparedStmt.executeQuery();
            String hashedPassword = "";
            if (result.next()) {
                hashedPassword = result.getString("password");
            }
            return hashedPassword;
        }
        catch (SQLException e) {
            throw new SqlQueryException("Get User Password Query Failed ");
        }

    }
}