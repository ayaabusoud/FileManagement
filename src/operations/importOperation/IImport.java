package operations.importOperation;

import exceptions.IncorrectFilePathException;

import java.sql.Connection;

public interface IImport {
    public void importFile(Connection connection) throws IncorrectFilePathException;

}
