package operations.export;

import classification.ChooseClassification;
import exceptions.NotAllowedOperationException;
import exceptions.SqlQueryException;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Export implements IExport {

    @Override
    public void export(Connection connection){
        try {
            ChooseClassification.classificationChoice(Variables.EXPORT_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }

    }
}