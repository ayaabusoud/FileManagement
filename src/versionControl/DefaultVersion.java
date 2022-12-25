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
import writeDB.LastVersionUpdation;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

public abstract class DefaultVersion {
    private static final Logger logger = LogManager.getLogger(DefaultVersion.class);

    public static void addDefaultVersion(Connection connection, String tableName
            , InputStream inputStream, FileInformation newFile)  {
        logger.debug("Enter to addDefaultVersion with args => " + connection + tableName + inputStream + newFile);
        try {
            FileInformation previousFile = FileInfo.getInfo(connection, newFile);
            newFile.setVersion(previousFile.getVersion() + 1);
            newFile.setVersionType(Variables.DEFAULT_VERSION_CONTROL_TYPE);
            LastVersionUpdation.updateToZero(connection, tableName, previousFile);
            FileAddition.addNewFile(connection, tableName, inputStream, newFile);
        } catch (SqlQueryException e) {
            System.err.println(e.getMessage());
        } catch (FileSizeException e) {
            System.err.println(e.getMessage());
        }
    logger.debug("Exit from addDefaultVersion");
    }
}