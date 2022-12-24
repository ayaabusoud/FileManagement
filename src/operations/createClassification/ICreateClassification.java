package operations.createClassification;

import java.sql.Connection;
import java.sql.SQLException;

public interface ICreateClassification {
    public void create(Connection connection) throws SQLException;
}