package readDB;

import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FilesAttributesMatching {
    private static final Logger logger = LogManager.getLogger(FilesAttributesMatching.class);
    public static ResultSet getFiles(Connection connection, String[]fileAttribute) throws SqlQueryException {
        logger.debug("Enter into getFiles function");
        ResultSet result =null;
        try {
            String query ="SELECT * FROM file WHERE "+fileAttribute[0]+" = ? AND lastVersion = 1";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, fileAttribute[1]);
            result = preparedStmt.executeQuery();

            logger.debug("select file query executed for files of " +fileAttribute[0]+": "+fileAttribute[1]);
            logger.debug("FilesAttributesMatching Exited");
        }catch (SQLException e){
            throw new SqlQueryException("find files query failed");
        }

        return result;
    }
}
