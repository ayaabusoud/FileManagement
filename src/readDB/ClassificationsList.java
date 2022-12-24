package readDB;

import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ClassificationsList {
    public static void displayClassification(Connection connection) throws SqlQueryException {
       try
       {
           String query ="SELECT * FROM classification";
           PreparedStatement preparedStmt = connection.prepareStatement(query);
           ResultSet result = preparedStmt.executeQuery();

           while (result.next()){
               System.out.println(result.getString("name")+"    "
                       +result.getString("formattedContext"));
           }
       }catch (SQLException e) {
           throw new SqlQueryException("Get Backup Info Query Failed");
       }

    }
}