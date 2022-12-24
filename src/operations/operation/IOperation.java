package operations.operation;

import exceptions.NotAllowedOperationException;
import file.FileInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IOperation {

    public void importFiles(Connection connection);
    public void deleteFiles(Connection connection);
    public void readFiles(Connection connection);
    public void rollBack(Connection connection);
    public void createClassification(Connection connection);
    public void exportFile(Connection connection);

    //void exportFile(String nameOfFile, Connection connection);
}