package controller;

import exceptions.SqlQueryException;
import readDB.ReadFile;
import variables.Variables;
import writeDB.DeleteFiles;
//import writeDB.DeleteFiles;

import java.sql.Connection;

public class OperationController {
    public static void control(Connection connection,String[] fileAttribute, String operationType){
        try {
        if (operationType.equalsIgnoreCase(Variables.READ_FILES)){
                ReadFile.readFiles(connection,fileAttribute);

        }  else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {
                DeleteFiles.deleteFiles(connection,fileAttribute);
        }
        }
        catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
    }
}