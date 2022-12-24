package readDB;

import encryption.DecryptionFile;
import encryption.EncryptionFile;
import exceptions.SqlQueryException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetFilesByAttributes {
    public static ResultSet getFiles(Connection connection, String[]fileAttribute) throws SqlQueryException {
        ResultSet result =null;
//               if(fileAttribute[0].equals("name")){
//           DecryptionFile.decryption(fileAttribute[1])  ;
//       }
        try {
            String query ="SELECT * FROM file WHERE "+fileAttribute[0]+" = ? AND lastVersion = 1";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, fileAttribute[1]);
            result = preparedStmt.executeQuery();
        }catch (SQLException e){
            throw new SqlQueryException("find files query failed");
        }
        return result;
    }
}
