package writeDB;


import encryption.EncryptionFile;
import exceptions.FileSizeException;
import exceptions.SQLthrException;
import file.FileInfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class AddFile {

    public static void addNewFile(Connection connection,String tableName, InputStream inputStream, FileInfo newFile) throws SQLthrException, FileSizeException{
        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";
        try {

            String Size = null;
            if (inputStream.available() < 50) {
                Size = "S";
            } else if (inputStream.available() >= 50 && inputStream.available() <= 70) {
                Size = "M";
            } else Size = "L";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, EncryptionFile.encryption(newFile.getName()));
            preparedStmt.setString(2, newFile.getType());
            preparedStmt.setString(3, Size);
            preparedStmt.setBlob(4, inputStream);
            preparedStmt.setInt(5, newFile.getVersion());
            preparedStmt.setInt(6, newFile.getLastVersion());
            preparedStmt.setInt(7, newFile.getVersionType());
            preparedStmt.execute();
        } catch (IOException e){
            throw  new FileSizeException("Failed access") ;
        } catch (SQLException e){
            throw new SQLthrException("Failed insert ..") ;
        }
    }
    public static void addNewFile(Connection connection,String tableName, FileInfo newFile) throws SQLthrException {
        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  EncryptionFile.encryption(newFile.getName()));
            preparedStmt.setString (2, newFile.getType());
            preparedStmt.setString(3, newFile.getSize());
            preparedStmt.setBlob(4,newFile.getContext());
            preparedStmt.setInt(5, newFile.getVersion());
            preparedStmt.setInt(6, newFile.getLastVersion());
            preparedStmt.setInt(7, newFile.getVersionType());
            preparedStmt.execute();
        } catch (SQLException e){
            throw new SQLthrException("Failed insert.. ") ;
        }


    }
}
