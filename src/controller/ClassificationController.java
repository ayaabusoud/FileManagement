package controller;

import exceptions.SqlQueryException;
import readDB.ReadFilesByClassification;
import variables.Variables;
import writeDB.DeleteFilesByClassification;
//import writeDB.DeleteFilesByClassification;

import java.sql.Connection;

public class ClassificationController {
    public static void control(Connection connection, String[] fileAttribute, String operationType) {
        try {


            if (operationType.equalsIgnoreCase(Variables.READ_FILES)) {
                ReadFilesByClassification.readFiles(connection, fileAttribute);

            } else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {

                DeleteFilesByClassification.deleteFiles(connection, fileAttribute);

            }
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
    }
}