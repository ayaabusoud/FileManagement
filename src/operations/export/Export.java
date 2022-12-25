package operations.export;

import classification.ClassificationChoice;
import exceptions.NotAllowedOperationException;
import operations.delete.Deletion;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import variables.Variables;

import java.sql.Connection;

public class Export implements IExportBehavior {
    private static final Logger logger = LogManager.getLogger(Deletion.class);
    @Override
    public void export(Connection connection){
        logger.debug("enter export function");
        try {
            ClassificationChoice.chooseClassification(Variables.EXPORT_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }
        logger.debug("exit export function");
    }
}