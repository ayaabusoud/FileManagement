package controller;

import readDB.ExportFiles;
import operations.read.ReadFile;
import variables.Variables;
import writeDB.Delete;


import java.sql.Connection;
import java.sql.SQLException;

public class OperationController {
    public static void control(Connection connection,String[] fileAttribute, String operationType){
        try {
        if (operationType.equalsIgnoreCase(Variables.READ_FILES)){
            ReadFile.readFiles(connection,fileAttribute,Variables.BY_ATTRIBUTES);
        }  else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {
//            DeleteFile.deleteFile(connection,fileAttribute,Variables.BY_ATTRIBUTES);
            Delete.AYAS(connection, fileAttribute, Variables.BY_ATTRIBUTES);
        } else if (operationType.equalsIgnoreCase(Variables.EXPORT_FILES)) {
            ExportFiles.export(connection,fileAttribute,Variables.BY_ATTRIBUTES);
        }
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}