package writeDB;

import exceptions.SqlQueryException;
import readDB.GetFilesByAttributes;
import readDB.GetFilesByClassification;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete {
    public static void AYAS(Connection connection , String [] atrib , String type) throws SqlQueryException {
        ResultSet result = null;
        try {
            if (type.equals(Variables.BY_CLASSIFICATION)) {
                result = GetFilesByClassification.getFiles(connection, atrib);
            } else {
                result = GetFilesByAttributes.getFiles(connection, atrib);
            }
            while (result.next()) {
                String query ="DELETE FROM file WHERE name = ? AND type = ? " ;
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString (1, result.getString("name"));
                preparedStmt.setString (2,result.getString("type")  );
                preparedStmt.execute();

            }
        } catch (SQLException e) {
            throw new SqlQueryException("Delete File Query Failed ");
        }
    }
}
