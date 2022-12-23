package operations.createClassification;

import classification.NewClassification;
import exceptions.SQLthrException;
import writeDB.AddClassification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateClassification implements ICreateClassification{
    public void create(Connection connection) throws SQLthrException {
        try {
            String[] classificationAttributes = NewClassification.create(connection);

                AddClassification.addNewClassification(connection,classificationAttributes);
        } catch (SQLException e) {
            throw new SQLthrException("Failed ..");
        }
        System.out.println("successfully added");
    }
}