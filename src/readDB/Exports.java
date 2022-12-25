package readDB;

import application.Main;
import exceptions.FileIsAlreadyExist;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import variables.Variables;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public abstract class Exports {

    private static final Logger logger = LogManager.getLogger(Exports.class);
    public static void exportFiles(Connection connection, String[]fileAttribute
            ,String exportType) throws SQLException {
        logger.debug("Enter to exportFiles function");
        Scanner sc = new Scanner(System.in);
        ResultSet result =null;

        if(exportType.equals(Variables.BY_CLASSIFICATION)){
            result = FilesClassificationMatching.getFiles(connection,fileAttribute);
            logger.debug("export files by classification");
        }else {
            result = FilesAttributesMatching.getFiles(connection,fileAttribute);
            logger.debug("export files by attribute");
        }
        System.out.print("Enter folder name: ");
        String folderPath = sc.next();
        File newFolder = new File("/"+folderPath);
        newFolder.mkdir();
    try {
    while (result.next()){
        PreparedStatement statement = connection.prepareStatement("SELECT context " +
                "INTO OUTFILE '/"+folderPath+"/"+result.getString("name")
                +"."+result.getString("type")+"' " +
                "FROM file WHERE name = ? And type = ? And lastVersion = ?" );

        statement.setString(1,result.getString("name") );
        statement.setString(2,result.getString("type") );
        statement.setInt(3,Variables.LAST_VERSION);
        statement.executeQuery() ;
        logger.debug("export query executed for file: "+result.getString("name")
        +"."+result.getString("type"));
    }
    }catch(SQLException e){
        throw new FileIsAlreadyExist("The file is already exists in this folder, delete it or change the folder name");
    }
        System.out.println("exported successfully");
    }
}
