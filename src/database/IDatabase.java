package database;

import exceptions.ConnectionMySqlException;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
    public Connection connectDB() throws ConnectionMySqlException;
    public void closeDB(Connection connection) throws ConnectionMySqlException;
}
