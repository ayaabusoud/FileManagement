package operations.read;

import java.sql.Connection;

public interface IReadingBehavior {
    void read(Connection connection);
}
