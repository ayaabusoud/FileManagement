package classification;

import operations.read.FileReading;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.Exports;
import variables.Variables;
import writeDB.FileDeletion;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ClassificationController {
    private static final Logger logger = LogManager.getLogger(ClassificationController.class) ;
    public static void controlClassification(Connection connection, String[] fileAttribute
            ,String operationType,String classificationChoice) {
        logger.info("Enter to ClassificationChoice function");

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