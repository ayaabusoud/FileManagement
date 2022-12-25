package versionControl;

import application.Main;
import exceptions.FileSizeException;
import exceptions.SqlQueryException;
import file.FileInformation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.FileInfo;
import variables.Variables;
import writeDB.FileAddition;
import writeDB.OverwriteFile;
import writeDB.LastVersionUpdation;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class OverwriteVersion {
    private static final Logger logger = LogManager.getLogger(OverwriteVersion.class);

    public static void addOverwriteFile(Connection connection, InputStream inputStream
            , FileInformation newFile) {
        logger.debug("Enter to addOverwriteFile with args => "+ connection +inputStream + newFile );
        try {
            FileInformation previousFile = FileInfo.getInfo(connection, newFile);
            LastVersionUpdation.updateToZero(connection, Variables.BACKUP_TABLE, previousFile);
            previousFile.setVersionType(Variables.OVERWRITE_VERSION_CONTROL_TYPE);
            FileAddition.addNewFile(connection, Variables.BACKUP_TABLE, previousFile);
            newFile.setVersion(previousFile.getVersion() + 1);
            OverwriteFile.updateFile(connection, inputStream, newFile);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Exit from addOverwriteFile");
    }
    }