package writeDB;

import encryption.EncryptionFile;
import exceptions.SQLthrException;
import file.FileInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteLastVersion {

    final static String QUERY = "DELETE FROM file WHERE name = ? AND type = ? AND lastVersion = 1";

    public static void deleteFile(Connection connection, FileInfo file) throws SQLthrException {
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(QUERY);
            preparedStmt.setString(1, EncryptionFile.encryption(file.getName()));
            preparedStmt.setString(2, file.getType());
            preparedStmt.execute();
            System.out.println("Delete successfully.. ");
        } catch (SQLException e){
            throw  new SQLthrException("Failed delete..") ;
        }

    }
}