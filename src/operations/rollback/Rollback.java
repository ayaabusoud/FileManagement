package operations.rollback;

import exceptions.IncorrectFileException;
import exceptions.SqlQueryException;
import file.FileInformation;
import file.FileNameAndType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.BackupInfo;
import readDB.FileInfo;
import readDB.PreviousFileExistence;
import variables.Variables;
import writeDB.*;

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
                OneLastVersion.updateToOne(connection, Variables.FILE_TABLE, file);
            }
            else if (file.getVersionType() == Variables.OVERWRITE_VERSION_CONTROL_TYPE) {
                FileInformation backupFile = BackupInfo.getInfo(connection, file);
                BackupDeletion.deleteFile(connection, backupFile );
                LastVersionDeletion.deleteFile(connection, file );
                if (PreviousFileExistence.isExists(connection, backupFile)) {
                    backupFile.setVersionType(Variables.DEFAULT_VERSION_CONTROL_TYPE);
                }
                backupFile.setLastVersion(Variables.LAST_VERSION);
                BackupFileAddition.addNewFile(connection, Variables.FILE_TABLE, backupFile);
            }
            } catch (SqlQueryException e) {
                System.err.println(e.getMessage());
            }catch (IncorrectFileException e){
                System.err.println(e.getMessage());
            }
            System.out.println("Successfully rolled back");
            logger.debug("exit rollbackVersion function");
    }
    }