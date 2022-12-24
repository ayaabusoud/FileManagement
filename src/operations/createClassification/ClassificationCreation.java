package operations.createClassification;

import exceptions.SqlQueryException;
import writeDB.ClassificationAddtion;

import java.sql.Connection;

public class ClassificationCreation implements ICreateClassification{
    public void create(Connection connection){
        try {
            String[] classificationAttributes = NewClassification.createClassification(connection);

                ClassificationAddtion.addNewClassification(connection,classificationAttributes);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("successfully added");
    }
}