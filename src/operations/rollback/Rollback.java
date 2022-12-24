package operations.rollback;

import exceptions.FileSizeException;
import exceptions.SQLthrException;
import file.FileInfo;
import file.FileNameAndType;
import readDB.CheckFileExistences;
import readDB.GetBackupInfo;
import readDB.GetFileInfo;
import variables.Variables;
import writeDB.AddFile;
import writeDB.DeleteBackup;
import writeDB.DeleteLastVersion;
import writeDB.UpdateLastVersion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Rollback implements IRollback {
        public void rollbackVersion(Connection connection, String fileNameAndType) throws SQLthrException {
            FileInfo file = null;
            try {
                file = GetFileInfo.getInfo(connection, FileNameAndType.splitNameAndType(fileNameAndType));

            if (file.getVersionType() == Variables.ONE_VERSION_TYPE) {
                DeleteLastVersion.deleteFile(connection, file);
            } else if (file.getVersionType() == Variables.DEFAULT_VERSION_CONTROL_TYPE) {
                DeleteLastVersion.deleteFile(connection, file);
                UpdateLastVersion.updateToOne(connection, Variables.FILE_TABLE, file);
            } else if (file.getVersionType() == Variables.OVERWRITE_VERSION_CONTROL_TYPE) {
                FileInfo backupFile = GetBackupInfo.getInfo(connection, file);
                DeleteBackup.deleteFile(connection, backupFile);
                DeleteLastVersion.deleteFile(connection, file);
                if (CheckFileExistences.previousVersionIsExist(connection, backupFile)) {
                    backupFile.setVersionType(Variables.DEFAULT_VERSION_CONTROL_TYPE);
                }
                backupFile.setLastVersion(Variables.LAST_VERSION);
                AddFile.addNewFile(connection, Variables.FILE_TABLE, backupFile);
            }
            } catch (SQLException e) {
                throw new SQLthrException("Failed rollback ..");
            }
        }
    }