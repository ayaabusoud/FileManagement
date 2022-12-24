package operations.export;

import file.FileInfo;

import java.sql.Connection;
import java.sql.SQLException;

public interface IExport {
    public  void export(Connection connection);

}