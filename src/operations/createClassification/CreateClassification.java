package operations.createClassification;

import classification.NewClassification;
import exceptions.SqlQueryException;
import writeDB.AddClassification;

import java.io.IOException;
import java.sql.Connection;

public class CreateClassification implements ICreateClassification{
    public void create(Connection connection){
        try {
            String[] classificationAttributes = NewClassification.create(connection);

                AddClassification.addNewClassification(connection,classificationAttributes);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("successfully added");
    }
}