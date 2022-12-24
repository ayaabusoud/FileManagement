package versionControl;

import exceptions.FileSizeException;
import exceptions.SqlQueryException;
import file.FileInformation;
import readDB.GetFileInfo;
import variables.Variables;
import writeDB.FileAddition;
import writeDB.LastVersionDeletion;
import writeDB.UpdateLastVersion;

import java.io.InputStream;
import java.sql.Connection;

public abstract class OverwriteVersion {
    public static void addOverwriteFile(Connection connection, InputStream inputStream, FileInformation newFile) {
        try {
            FileInformation previousFile = GetFileInfo.getInfo(connection, newFile);
            LastVersionDeletion.deleteFile(connection, previousFile);
            UpdateLastVersion.updateToZero(connection, Variables.BACKUP_TABLE, previousFile);
            previousFile.setVersionType(Variables.OVERWRITE_VERSION_TYPE);
            FileAddition.addNewFile(connection, Variables.BACKUP_TABLE, previousFile);
            newFile.setVersion(previousFile.getVersion() + 1);
            FileAddition.addNewFile(connection, Variables.FILE_TABLE, inputStream, newFile);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        } catch (FileSizeException e) {
            System.err.println(e.getMessage());
        }
    }
}