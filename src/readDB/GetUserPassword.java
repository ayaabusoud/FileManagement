package readDB;

import factory.Factory;
import users.UserTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static login.CheckPassword.checkPass;
import static variables.Variables.readerUser;

public class GetUserPassword {
    public static String getPassword(Connection connection, String username) throws SQLException {
        String QUERY = "SELECT * FROM user WHERE name = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
        preparedStmt.setString(1, username);
        ResultSet result = preparedStmt.executeQuery();
        String hashedPassword = "";
        if (result.next()) {
            hashedPassword = result.getString("password");
        }
        return hashedPassword;
    }
}