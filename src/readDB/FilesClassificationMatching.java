package readDB;

import application.Main;
import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.SqlQueryException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FilesClassificationMatching {
    private static final Logger logger = LogManager.getLogger(FilesClassificationMatching.class);

    public static ResultSet getFiles(Connection connection, String[]context) throws SqlQueryException {
        logger.debug("Enter to getFiles with args "+ connection + context );
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        ResultSet result = null;
        String nameQuery = Variables.EQUALS;
        String typeQuery = Variables.EQUALS;
        String sizeQuery = Variables.EQUALS;
        if (context[0].equals(Variables.NO_Condition)) {
            nameQuery = Variables.NOT_EQUALS;
        }
        if (context[1].equals(Variables.NO_Condition)) {
            typeQuery = Variables.NOT_EQUALS;
        }
        if (context[2].equals(Variables.NO_Condition)) {
            sizeQuery = Variables.NOT_EQUALS;
        }
        try {
            String query = "SELECT * FROM file WHERE name" + nameQuery + "? AND type" + typeQuery + "? " +
                    "AND size" + sizeQuery + "?" + " AND lastVersion = 1";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, EncryptionFile.encryptAndDecrypt(context[0]));
            preparedStmt.setString(2, context[1]);
            preparedStmt.setString(3, context[2]);
            result = preparedStmt.executeQuery();
            logger.info("Query executed");
            logger.debug("FilesClassificationMatching Exited");

        } catch (SQLException e) {
            throw new SqlQueryException("find files query filed");
        }
        return result;
    }
}
