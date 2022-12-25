package readDB;

import application.Main;
import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClassificationContent {
    private static final Logger logger = LogManager.getLogger(ClassificationContent.class);

    public static String[] getContent(Connection connection, String classificationName)throws SqlQueryException {
        logger.debug("Enter to getContent function" );
        String [] context = new String[2];
        String query ="SELECT * FROM classification WHERE name = ?";
        try
        {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  classificationName);
            ResultSet result = preparedStmt.executeQuery();
            logger.info("Query executed");
            if(result.next()){
                context = result.getString("context").split(",");
            }
            logger.debug("get info content executed for classification: "+classificationName);
            return context;
        }
        catch (SQLException e) {
            throw new SqlQueryException("Get Classification Content Query ");
        }
    }
}