package operations.operation;

import exceptions.NotAllowedOperationException;
import file.FileInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IOperation {

    public void importFiles(Connection connection) throws NotAllowedOperationException;
    public void deleteFiles(Connection connection) throws SQLException;
    public void readFiles(Connection connection) throws SQLException;
    public void rollBack(Connection connection, String path) throws SQLException, IOException;
    public void createClassification(Connection connection) throws SQLException;
    public void exportFile(Connection connection, FileInfo file) throws SQLException;

    //void exportFile(String nameOfFile, Connection connection);
}