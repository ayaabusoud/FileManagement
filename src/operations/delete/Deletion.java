package operations.delete;


import classification.ClassificationChoice;
import exceptions.NotAllowedOperationException;
import operations.createClassification.ClassificationCreation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import variables.Variables;

import java.sql.Connection;

public class Deletion implements IDeletionBehavior {
    private static final Logger logger = LogManager.getLogger(Deletion.class);
    public void delete(Connection connection){
        logger.debug("enter delete function");
        try {
            ClassificationChoice.chooseClassification(Variables.DELETE_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Successfully deleted");
        logger.debug("exit delete function");
    }
}