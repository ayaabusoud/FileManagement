package operations.createClassification;

import exceptions.SqlQueryException;
import writeDB.ClassificationAddition;

import java.sql.Connection;

public class ClassificationCreation implements IClassificationCreationBehavior {
    public void create(Connection connection){
        try {
            String[] classificationAttributes = NewClassification.createClassification(connection);

                ClassificationAddition.addNewClassification(connection,classificationAttributes);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("successfully added");
    }
}