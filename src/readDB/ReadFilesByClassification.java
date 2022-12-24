package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadFilesByClassification {
    public static void readFiles(Connection connection, String[]context) throws SQLException {
        String nameQuery = Variables.EQUALS;
        String typeQuery  =Variables.EQUALS;
        String sizeQuery =Variables.EQUALS;
        if(context[0].equals(Variables.noCondition)){
            nameQuery = Variables.NOT_EQUALS;
        }
        if(context[1].equals(Variables.noCondition)){
            typeQuery = Variables.NOT_EQUALS;
        }
        if(context[2].equals(Variables.noCondition)){
            sizeQuery = Variables.NOT_EQUALS;
        }

        String query ="SELECT * FROM file WHERE name"+nameQuery+"? AND type"+typeQuery+"? AND size"+sizeQuery+"?"+" AND lastVersion = 1";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  EncryptionFile.encryption(context[0]));
        preparedStmt.setString (2,  context[1]);
        preparedStmt.setString (3,  context[2]);
        ResultSet result = preparedStmt.executeQuery();
        if (!result.next()){
            System.out.println("There is no such files in the system.");
        }
        while (result.next()){
            System.out.println(DecryptionFile.decryption(result.getString("name"))+"."+result.getString("type")+": ");
            System.out.println(result.getString("context"));
            System.out.println("----------------");
        }
    }
}