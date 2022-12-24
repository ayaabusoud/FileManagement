package operations.read;

import encryption.DecryptionFile;
import exceptions.SqlQueryException;
import readDB.GetFilesByAttributes;
import readDB.GetFilesByClassification;
import variables.Variables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFile {
    public static void readFiles(Connection connection, String[] fileAttribute, String readType) throws SqlQueryException {
        ResultSet result = null;
        try {
            if (readType.equals(Variables.BY_CLASSIFICATION)) {
                result = GetFilesByClassification.getFiles(connection, fileAttribute);
            } else {
                result = GetFilesByAttributes.getFiles(connection, fileAttribute);
            }
            while (result.next()) {
                System.out.println(DecryptionFile.decryption(result.getString("name")) + "." + result.getString("type") + ": ");
                System.out.println(result.getString("context"));
                System.out.println("----------------");
            }
        } catch (SQLException e) {
            throw new SqlQueryException("Read File Query Failed ");
        }
    }
}
