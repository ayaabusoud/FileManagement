package versionControl;

import exceptions.SqlQueryException;
import file.FileInformation;
import readDB.FileInfo;
import variables.Variables;
import writeDB.FileAddition;
import writeDB.OverwriteFile;
import writeDB.UpdateLastVersion;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class OverwriteVersion {
    public static void addOverwriteFile(Connection connection, InputStream inputStream
            , FileInformation newFile) {
        try {
            FileInformation previousFile = FileInfo.getInfo(connection, newFile);
//            LastVersionDeletion.deleteFile(connection, previousFile);
            UpdateLastVersion.updateToZero(connection, Variables.BACKUP_TABLE, previousFile);
            previousFile.setVersionType(Variables.OVERWRITE_VERSION_CONTROL_TYPE);
            FileAddition.addNewFile(connection, Variables.BACKUP_TABLE, previousFile);
            newFile.setVersion(previousFile.getVersion() + 1);
            OverwriteFile.updateFile(connection, inputStream, newFile);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}