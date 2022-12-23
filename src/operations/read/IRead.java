package operations.read;

import exceptions.SqlQueryException;

import java.sql.Connection;
import java.sql.SQLException;

public interface IRead {
    void read(Connection connection);
}
