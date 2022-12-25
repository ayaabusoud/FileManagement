package operations.export;

import classification.ClassificationChoice;
import exceptions.NotAllowedOperationException;
import variables.Variables;

import java.sql.Connection;

public class Export implements IExportBehavior {

    @Override
    public void export(Connection connection){
        try {
            ClassificationChoice.chooseClassification(Variables.EXPORT_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }

    }
}