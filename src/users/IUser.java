package users;


import java.sql.Connection;


public interface IUser {

    public void importFiles(Connection connection);
    public void deleteFiles(Connection connection);
    public void readFiles(Connection connection);
    public void rollBack(Connection connection);
    public void createClassification(Connection connection);
    public void exportFile(Connection connection);

    //void exportFile(String nameOfFile, Connection connection);
}