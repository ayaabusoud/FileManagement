package readDB;

import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserPassword {

    private static final Logger logger = LogManager.getLogger(UserPassword.class);
    public static String getPassword(Connection connection, String username) throws SqlQueryException {
        logger.debug("Enter to getPassword with args => "+ connection + username);
        ResultSet result;
        try {
            String QUERY = "SELECT * FROM user WHERE name = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, username);
            result = preparedStmt.executeQuery();
            logger.info("Query executed ");
            logger.debug("UserPassword Exited");
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