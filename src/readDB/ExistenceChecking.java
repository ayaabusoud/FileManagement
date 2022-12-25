package readDB;

import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ExistenceChecking {
    private static final Logger logger = LogManager.getLogger(ExistenceChecking.class);

    public static boolean isExists(Connection connection,String table, String username) throws SqlQueryException {
        logger.debug("Enter to isExists with args => "+ connection + "Table name"+ table +"User name"+ username);
        try {
            String query = "SELECT * FROM "+table+" WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            logger.info("Query executed");
            if(result.next()){
                return true;
            }
            return false;
        }  catch (SQLException e) {
            throw new SqlQueryException("Check Existences Query Failed");
        }

    }
}
