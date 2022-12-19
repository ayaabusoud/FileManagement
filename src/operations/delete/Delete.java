package operations.delete;


import classification.ChooseClassification;
import variables.Variables;

import java.sql.Connection;
import java.sql.SQLException;

public class Delete implements IDelete {
    public void delete(Connection connection) throws SQLException {
        ChooseClassification.classificationChoice(Variables.DELETE_FILES,connection);
    }
}