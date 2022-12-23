package writeDB;


import encryption.EncryptionFile;
import exceptions.RunTimeException;
import file.FileInfo;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class AddFile {

    public static void addNewFile(Connection connection,String tableName, InputStream inputStream, FileInfo newFile)throws RunTimeException, IOException {
        String query = "INSERT INTO " + tableName + " (name,type,size,context,version,lastVersion,versionType) values (?,?,?,?,?,?,?)";

        String Size = null ;
        if(inputStream.available() < 50){
            Size = "S" ;
        } else  if (inputStream.available() >= 50 && inputStream.available() <= 70 ){
            Size = "M" ;
        } else Size = "L" ;
        try{
           PreparedStatement preparedStmt = connection.prepareStatement(query);
           preparedStmt.setString (1,  EncryptionFile.encryption(newFile.getName()));
           preparedStmt.setString (2, newFile.getType());
           preparedStmt.setString(3, Size);
           preparedStmt.setBlob(4,inputStream);
           preparedStmt.setInt(5, newFile.getVersion());
           preparedStmt.setInt(6, newFile.getLastVersion());
           preparedStmt.setInt(7, newFile.getVersionType());
           preparedStmt.execute();
       }
       catch (SQLException e) {
           throw new RunTimeException(" Add New File Query Failed (one)");
       }

    }
    public static void addNewFile(Connection connection,String tableName, FileInfo newFile) throws RunTimeException, IOException {
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
        }catch (SQLException e) {
            throw new RunTimeException(" Add New File Query Failed (tow)");
        }

    }
}
