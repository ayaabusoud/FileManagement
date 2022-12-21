package operations.export;

import java.sql.Connection;
import java.sql.SQLException;

public interface IExport {
    public  void export(Connection connection , String FileName) throws SQLException;

}