package operations.read;

import classification.ClassificationChoice;
import exceptions.NotAllowedOperationException;
import variables.Variables;

import java.sql.Connection;

public class Reading implements IReading {
    @Override
    public void read(Connection connection) {
        try {
            ClassificationChoice.chooseClassification(Variables.READ_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }
    }
}