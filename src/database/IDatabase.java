package database;

import exceptions.connectionMySqlException;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
    public Connection connectDB() throws connectionMySqlException;
    public void closeDB(Connection connection) throws SQLException;
}
