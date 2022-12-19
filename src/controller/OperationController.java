package controller;

import readDB.ReadFile;
import variables.Variables;
import writeDB.DeleteFiles;
//import writeDB.DeleteFiles;

import java.sql.Connection;
import java.sql.SQLException;

public class OperationController {
    public static void control(Connection connection,String[] fileAttribute, String operationType){
        if (operationType.equalsIgnoreCase(Variables.READ_FILES)){
            try {
                ReadFile.readFiles(connection,fileAttribute);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }  else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {
            try {
                DeleteFiles.deleteFiles(connection,fileAttribute);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}