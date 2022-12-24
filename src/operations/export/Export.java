package operations.export;

import classification.ClassificationChoice;
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
            ClassificationChoice.chooseClassification(Variables.EXPORT_FILES,connection);
        }catch (NotAllowedOperationException e){
            System.err.println(e.getMessage());
        }

    }
}