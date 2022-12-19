package operations.importOperation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface IImport {
    public void importFile(Connection connection, String path) throws SQLException, IOException;

}
