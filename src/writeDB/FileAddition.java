package writeDB;


import encryption.EncryptionFile;
import exceptions.FileSizeException;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public abstract class FileAddition {

    public static void addNewFile(Connection connection,String tableName
            , InputStream inputStream, FileInformation newFile) throws SqlQueryException, FileSizeException {

        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";
        try{
            String Size= SizeConversion.CSize(inputStream) ;
           PreparedStatement preparedStmt = connection.prepareStatement(query);
           preparedStmt.setString (1,  EncryptionFile.encryptFile(newFile.getName()));
           preparedStmt.setString (2, newFile.getType());
           preparedStmt.setString(3, Size);
           preparedStmt.setBlob(4,inputStream);
           preparedStmt.setInt(5, newFile.getVersion());
           preparedStmt.setInt(6, newFile.getLastVersion());
           preparedStmt.setInt(7, newFile.getVersionType());
           preparedStmt.execute();
       }
       catch (SQLException e) {
           throw new SqlQueryException(" Add New File Query Failed");
       }catch (IOException e){
            throw new FileSizeException("Fail in file input or output");
        }
    }
    public static void addNewFile(Connection connection,String tableName
            , FileInformation newFile) throws SqlQueryException{

        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  EncryptionFile.encryptFile(newFile.getName()));
            preparedStmt.setString (2, newFile.getType());
            preparedStmt.setString(3, newFile.getSize());
            preparedStmt.setBlob(4,newFile.getContext());
            preparedStmt.setInt(5, newFile.getVersion());
            preparedStmt.setInt(6, newFile.getLastVersion());
            preparedStmt.setInt(7, newFile.getVersionType());
            preparedStmt.execute();
        }catch (SQLException e) {
            throw new SqlQueryException(" Add New File Query Failed... ");
        }

    }
}