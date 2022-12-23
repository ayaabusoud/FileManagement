package operations.createClassification;

import classification.NewClassification;
import exceptions.RunTimeException;
import writeDB.AddClassification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateClassification implements ICreateClassification{
    public void create(Connection connection){
        try {
            String[] classificationAttributes = NewClassification.create(connection);

                AddClassification.addNewClassification(connection,classificationAttributes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (RunTimeException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("successfully added");
    }
}