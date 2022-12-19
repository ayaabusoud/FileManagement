package controller;

import readDB.ReadFilesByClassification;
import variables.Variables;
import writeDB.DeleteFilesByClassification;
//import writeDB.DeleteFilesByClassification;

import java.sql.Connection;
import java.sql.SQLException;

public class ClassificationController {
    public static void control(Connection connection,String[] fileAttribute, String operationType){
        if (operationType.equalsIgnoreCase(Variables.READ_FILES)){
            try {
                ReadFilesByClassification.readFiles(connection,fileAttribute);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {
            try {
                DeleteFilesByClassification.deleteFiles(connection,fileAttribute);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}