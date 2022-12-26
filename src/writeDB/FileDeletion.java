package writeDB;

import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.FilesAttributesMatching;
import readDB.FilesClassificationMatching;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FileDeletion {
    private static final Logger logger = LogManager.getLogger(FileDeletion.class);
    public static void deleteFile(Connection connection
            , String [] fileAttributes , String type) throws SqlQueryException {
        logger.debug("enter deleteFile function");
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
                String backupQuery ="DELETE FROM backup WHERE name = ? AND type = ? " ;
                PreparedStatement preparedStatement = connection.prepareStatement(backupQuery);
                preparedStatement.setString (1, result.getString("name"));
                preparedStatement.setString (2,result.getString("type")  );
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            throw new SqlQueryException("Delete File Query Failed ");
        }
        logger.debug("exit deleteFile function");
    }
}
