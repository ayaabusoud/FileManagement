package operations.rollback;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IRollback {
    public void rollbackVersion(Connection connection, String path) throws SQLException, IOException;
}
