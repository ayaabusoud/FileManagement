package operations.operation;


import java.sql.Connection;


public interface IOperation {

    public void importFiles(Connection connection);
    public void deleteFiles(Connection connection);
    public void readFiles(Connection connection);
    public void rollBack(Connection connection);
    public void createClassification(Connection connection);
    public void exportFile(Connection connection);

    //void exportFile(String nameOfFile, Connection connection);
}