package operations.rollback;

import file.FileInfo;
import file.FileNameAndType;
import readDB.CheckFileExistences;
import readDB.GetBackupInfo;
import readDB.GetFileInfo;
import writeDB.AddFile;
import writeDB.DeleteBackup;
import writeDB.DeleteLastVersion;
import writeDB.UpdateLastVersion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Rollback implements IRollback {

    public void rollbackVersion(Connection connection, String path) throws SQLException, IOException {

    }
}
