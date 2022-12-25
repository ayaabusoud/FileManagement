package operations.delete;


import classification.ClassificationChoice;
import exceptions.NotAllowedOperationException;
import variables.Variables;

import java.sql.Connection;

public class Deletion implements IDeletionBehavior {
    public void delete(Connection connection){
        try {
            ClassificationChoice.chooseClassification(Variables.DELETE_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }

    }
}