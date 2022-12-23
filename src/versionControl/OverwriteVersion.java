package versionControl;

import exceptions.RunTimeException;
import file.FileInfo;
import readDB.GetFileInfo;
import variables.Variables;
import writeDB.AddFile;
import writeDB.DeleteLastVersion;
import writeDB.UpdateLastVersion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class OverwriteVersion {
    public static void overwriteFile(Connection connection, InputStream inputStream, FileInfo newFile)
     {
        try {
            FileInfo previousFile = GetFileInfo.getInfo(connection, newFile);
            DeleteLastVersion.deleteFile(connection, previousFile);
            UpdateLastVersion.updateToZero(connection, Variables.BACKUP_TABLE, previousFile);
            previousFile.setVersionType(Variables.OVERWRITE_VERSION_TYPE);
            AddFile.addNewFile(connection, Variables.BACKUP_TABLE, previousFile);
            newFile.setVersion(previousFile.getVersion() + 1);
            AddFile.addNewFile(connection, Variables.FILE_TABLE, inputStream, newFile);
        } catch (RunTimeException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}