package controller;

import exceptions.RunTimeException;
import readDB.ReadFile;
import variables.Variables;
import writeDB.DeleteFiles;
//import writeDB.DeleteFiles;

import java.sql.Connection;
import java.sql.SQLException;

public class OperationController {
    public static void control(Connection connection,String[] fileAttribute, String operationType) throws RunTimeException{
        try {
        if (operationType.equalsIgnoreCase(Variables.READ_FILES)){
                ReadFile.readFiles(connection,fileAttribute);

        }  else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {
                DeleteFiles.deleteFiles(connection,fileAttribute);
        }
    }catch (RunTimeException e) {
            System.err.println(e.getMessage());
        }
    }
}