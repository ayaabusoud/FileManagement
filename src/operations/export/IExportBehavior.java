package operations.export;

import java.sql.Connection;

public interface IExportBehavior {
    public  void export(Connection connection);

}