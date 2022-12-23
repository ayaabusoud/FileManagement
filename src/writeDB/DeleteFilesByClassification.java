package writeDB;

import encryption.EncryptionFile;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteFilesByClassification {
    public static void deleteFiles(Connection connection, String[]context) throws SQLException {
        String nameQuery = Variables.EQUALS;
        String typeQuery  =Variables.EQUALS;
        String sizeQuery =Variables.EQUALS;
        if(context[0].equals(Variables.noCondition)){
            nameQuery = Variables.NOT_EQUALS;
        }
        if(context[1].equals(Variables.noCondition)){
            typeQuery =Variables.NOT_EQUALS;
        }
        if(context[2].equals(Variables.noCondition)){
            sizeQuery = Variables.NOT_EQUALS;
        }
        String query ="DELETE FROM file WHERE name"+nameQuery+"? AND type"+typeQuery+"? AND size"+sizeQuery+"? ";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  EncryptionFile.encryption(context[0]));
        preparedStmt.setString (2,  context[1]);
        preparedStmt.setString (3,  context[2]);
        preparedStmt.execute();

    }
}