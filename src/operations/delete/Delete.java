package operations.delete;


import classification.ChooseClassification;
import exceptions.NotAllowedOperationException;
import variables.Variables;

import java.sql.Connection;

public class Delete implements IDelete {
    public void delete(Connection connection){
        try {
            ChooseClassification.classificationChoice(Variables.DELETE_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }

    }
}