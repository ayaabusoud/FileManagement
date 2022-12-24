package classification;

import operations.read.FileReading;
import readDB.Exports;
import variables.Variables;
import writeDB.FileDeletion;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ClassificationController {
    public static void controlClassification(Connection connection, String[] fileAttribute
            ,String operationType,String classificationChoice) {
        try {
            if (operationType.equalsIgnoreCase(Variables.READ_FILES)) {
                FileReading.readFiles(connection,fileAttribute,classificationChoice);
            } else if (operationType.equalsIgnoreCase(Variables.DELETE_FILES)) {
                FileDeletion.deleteFile(connection, fileAttribute,classificationChoice);
            }
            else if (operationType.equalsIgnoreCase(Variables.EXPORT_FILES)) {
                Exports.exportFiles(connection, fileAttribute,classificationChoice);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}