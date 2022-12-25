package operations.rollback;

import java.sql.Connection;

public interface IRollbackBehavior {
    public void rollbackVersion(Connection connection);
}
