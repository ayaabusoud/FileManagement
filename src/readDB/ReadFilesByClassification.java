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
        String nameQuery = " = ";
        String typeQuery  =" = ";
        String sizeQuery =" = ";
        if(context[0].equals(Variables.noCondition)){
            nameQuery = " != ";
        }
        if(context[1].equals(Variables.noCondition)){
            typeQuery = " != ";
        }
        if(context[2].equals(Variables.noCondition)){
            sizeQuery = " != ";
        }

        String query ="SELECT * FROM file WHERE name"+nameQuery+"? AND type"+typeQuery+"? AND size"+sizeQuery+"?"+" AND lastVersion = 1";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString (1,  EncryptionFile.encryption(context[0]));
        preparedStmt.setString (2,  context[1]);
        preparedStmt.setString (3,  context[2]);
        ResultSet result = preparedStmt.executeQuery();
        while (result.next()){
            System.out.println(DecryptionFile.decryption(result.getString("name"))+"."+result.getString("type")+": ");
            System.out.println(result.getString("context"));
            System.out.println("----------------");
        }
    }
}