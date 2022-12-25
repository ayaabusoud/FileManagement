package readDB;

import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClassificationsList {
    private static final Logger logger = LogManager.getLogger(ClassificationsList.class);

    public static void displayClassification(Connection connection) throws SqlQueryException {
        logger.debug("Enter to displayClassification function");
       try
       {
           String query ="SELECT * FROM classification";
           PreparedStatement preparedStmt = connection.prepareStatement(query);
           ResultSet result = preparedStmt.executeQuery();
           logger.debug("get all classification query executed");

           while (result.next()){
               System.out.println(result.getString("name")+"    "
                       +result.getString("formattedContext"));
           }
           logger.debug("Exit to ClassificationsList");

       }catch (SQLException e) {
           throw new SqlQueryException("Get Backup Info Query Failed");
       }

    }
}