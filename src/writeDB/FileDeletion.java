package writeDB;

import exceptions.SqlQueryException;
import readDB.FilesAttributesMatching;
import readDB.FilesClassificationMatching;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FileDeletion {
    public static void deleteFile(Connection connection
            , String [] fileAttributes , String type) throws SqlQueryException {
        ResultSet result = null;
        try {
            if (type.equals(Variables.BY_CLASSIFICATION)) {
                result = FilesClassificationMatching.getFiles(connection, fileAttributes);
            } else {
                result = FilesAttributesMatching.getFiles(connection, fileAttributes);
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
