package operations.createClassification;

import exceptions.SqlQueryException;
import file.FileNameAndType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import writeDB.ClassificationAddition;

import java.sql.Connection;

public class ClassificationCreation implements IClassificationCreationBehavior {
    private static final Logger logger = LogManager.getLogger(ClassificationCreation.class);
    public void create(Connection connection){
        logger.info("enter create function");
        try {
                String[] classificationAttributes = NewClassification.createClassification(connection);
                ClassificationAddition.addNewClassification(connection,classificationAttributes);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("successfully added");
        logger.info("exit create function");
    }
}