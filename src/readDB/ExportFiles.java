package readDB;

import encryption.EncryptionFile;
import exceptions.FileIsAlreadyExist;
import exceptions.SqlQueryException;
import file.FileInfo;
import readDB.GetFilesByAttributes;
import readDB.GetFilesByClassification;
import variables.Variables;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExportFiles {
    public static void export(Connection connection, String[]fileAttribute,String exportType) throws SQLException {

        Scanner sc = new Scanner(System.in);
        ResultSet result =null;

        if(exportType.equals(Variables.BY_CLASSIFICATION)){
            result = GetFilesByClassification.getFiles(connection,fileAttribute);
        }else {
            result = GetFilesByAttributes.getFiles(connection,fileAttribute);
        }

        System.out.println("Enter folder name: ");
        String folderPath = sc.next();
        File newFolder = new File("/"+folderPath);
        boolean createFolder = newFolder.mkdir();

    try {
    while (result.next()){
        PreparedStatement statement = connection.prepareStatement("SELECT context " +
                "INTO OUTFILE '/"+folderPath+"/"+result.getString("name")+"."+result.getString("type")+"' " +
                "FROM file WHERE name = ? And type = ? And lastVersion = ?" );
        statement.setString(1,result.getString("name") );
        statement.setString(2,result.getString("type") );
        statement.setInt(3,Variables.LAST_VERSION);
        statement.executeQuery() ;

    }
    }catch(SQLException e){
        throw new FileIsAlreadyExist("The file is already exists in this folder, delete it or change the folder name");
    }
        System.out.println("exported successfully");
    }
}
