package database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
    public Connection connectDB();
    public void closeDB(Connection connection) throws SQLException;
}
