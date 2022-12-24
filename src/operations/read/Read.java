package operations.read;

import classification.ChooseClassification;
import variables.Variables;

import java.sql.Connection;
import java.sql.SQLException;

public class Read implements IRead {
    @Override
    public void read(Connection connection) {
        ChooseClassification.classificationChoice(Variables.READ_FILES,connection);
    }
}