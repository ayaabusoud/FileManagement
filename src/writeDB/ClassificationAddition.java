package writeDB;

import exceptions.SqlQueryException;
import operations.rollback.Rollback;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class ClassificationAddition {
    private static final Logger logger = LogManager.getLogger(ClassificationAddition.class);
    public static void addNewClassification(Connection connection
            , String[] classificationAttributes) throws SqlQueryException{
        logger.debug("enter addNewClassification function");
        String query = "INSERT INTO classification (name,context,formattedContext) values (?,?,?)";
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  classificationAttributes[0]);
            preparedStmt.setString (2, classificationAttributes[1]);
            preparedStmt.setString (3, classificationAttributes[2]);
            preparedStmt.execute();
            logger.debug("insert classification with name: "+ classificationAttributes[0]
                    +"context: "+classificationAttributes[1]);
        }
        catch (SQLException e) {
            throw new SqlQueryException("Add Classification Query Failed");
        }
        logger.debug("exit addNewClassification function");
    }
}