package operations.read;

import classification.ChooseClassification;
import exceptions.NotAllowedOperationException;
import variables.Variables;

import java.sql.Connection;
import java.sql.SQLException;

public class Read implements IRead {
    @Override
    public void read(Connection connection) {
        try {
            ChooseClassification.classificationChoice(Variables.READ_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }

    }
}