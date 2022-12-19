package operations.operation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IOperation {

    public void importFiles(Connection connection, String path) throws SQLException, IOException;
    public void deleteFiles(Connection connection) throws SQLException;

    public void exportFile();
    public void rollBack(Connection connection,String path) throws SQLException, IOException;
    public void createClassification();
}
