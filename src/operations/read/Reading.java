package operations.read;

import classification.ClassificationChoice;
import exceptions.NotAllowedOperationException;
import operations.rollback.Rollback;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import variables.Variables;

import java.sql.Connection;

public class Reading implements IReadingBehavior {
    private static final Logger logger = LogManager.getLogger(Reading.class);
    @Override
    public void read(Connection connection) {
        logger.debug("enter read function");
        try {
            ClassificationChoice.chooseClassification(Variables.READ_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }
        logger.debug("exit read function");
    }
}