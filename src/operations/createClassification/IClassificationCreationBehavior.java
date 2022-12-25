package operations.createClassification;

import java.sql.Connection;

public interface IClassificationCreationBehavior {
    public void create(Connection connection);
}