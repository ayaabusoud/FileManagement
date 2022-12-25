package operations.delete;

import java.sql.Connection;

public interface IDeletionBehavior {
    public void delete(Connection connection);
}
