package operations.read;

import classification.ChooseClassification;
import encryption.DecryptionFile;
import exceptions.NotAllowedOperationException;
import exceptions.SqlQueryException;
import readDB.GetFilesByAttributes;
import variables.Variables;

import java.sql.Connection;
import java.sql.ResultSet;
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