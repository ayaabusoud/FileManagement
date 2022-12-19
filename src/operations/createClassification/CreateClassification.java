package operations.createClassification;

import classification.NewClassification;
import writeDB.AddClassification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateClassification implements ICreateClassification{
    public void create(Connection connection) throws SQLException {
        try {
            String[] classificationAttributes = NewClassification.create();
            AddClassification.addNewClassification(connection,classificationAttributes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}