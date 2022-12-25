package operations.importOperation;

import exceptions.IncorrectFilePathException;

import java.sql.Connection;

public interface IImportBehavior {
    public void importFile(Connection connection) throws IncorrectFilePathException;

}
