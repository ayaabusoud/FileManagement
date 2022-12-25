package operations.rollback;

import exceptions.SqlQueryException;
import file.FileInformation;
import file.FileNameAndType;
import operations.createClassification.ClassificationCreation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.BackupInfo;
import readDB.FileInfo;
import readDB.PreviousFileExistence;
import variables.Variables;
import writeDB.FileAddition;
import writeDB.LastVersionDeletion;
import writeDB.LastVersionUpdation;

import java.sql.Connection;
import java.util.Scanner;

public class Rollback implements IRollbackBehavior {
    private static final Logger logger = LogManager.getLogger(Rollback.class);
    public void rollbackVersion(Connection connection){
            logger.debug("enter rollbackVersion function");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter file.type: ");
            String fileNameAndType = sc.next();
            logger.debug("file name.type: "+fileNameAndType);
            FileInformation file = null;
            try {
                file = FileInfo.getInfo(connection, FileNameAndType.splitNameAndType(fileNameAndType));

            if (file.getVersionType() == Variables.ONE_VERSION_TYPE) {
                LastVersionDeletion.deleteFile(connection, file );
            }
            else if (file.getVersionType() == Variables.DEFAULT_VERSION_CONTROL_TYPE) {
                LastVersionDeletion.deleteFile(connection, file);
                LastVersionUpdation.updateToOne(connection, Variables.FILE_TABLE, file);
            }
            else if (file.getVersionType() == Variables.OVERWRITE_VERSION_CONTROL_TYPE) {
                FileInformation backupFile = BackupInfo.getInfo(connection, file);
                LastVersionDeletion.deleteFile(connection, backupFile );
                LastVersionDeletion.deleteFile(connection, file );
                if (PreviousFileExistence.isExists(connection, backupFile)) {
                    backupFile.setVersionType(Variables.DEFAULT_VERSION_CONTROL_TYPE);
                }
                backupFile.setLastVersion(Variables.LAST_VERSION);
                FileAddition.addNewFile(connection, Variables.FILE_TABLE, backupFile);
            }
            } catch (SqlQueryException e) {
                System.err.println(e.getMessage());
            }
            logger.debug("exit rollbackVersion function");
    }
    }