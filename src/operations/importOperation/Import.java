package operations.importOperation;

import exceptions.FileSizeException;
import exceptions.IncorrectFilePathException;
import exceptions.SqlQueryException;
import file.FileInformation;
import file.FileNameAndType;
import operations.delete.Deletion;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import readDB.FileExistences;
import variables.Variables;
import versionControl.DefaultVersion;
import versionControl.OverwriteVersion;
import writeDB.FileAddition;

import java.io.*;
import java.sql.Connection;
import java.util.Scanner;

public class Import implements IImportBehavior {
    private static final Logger logger = LogManager.getLogger(Import.class);
    @Override
    public void importFile(Connection connection) throws IncorrectFilePathException {
        logger.debug("enter importFile function");
        Scanner sc = new Scanner(System.in);
        String filePath ="";
        InputStream inputStream = null;

            try {
                System.out.print("Enter file path: ");
                filePath = sc.next();
                logger.debug("file path: "+filePath);
                inputStream = new FileInputStream(filePath);
                Thread.sleep(100);

                FileInformation newFile = FileNameAndType.splitNameAndType(filePath);

            if (!FileExistences.isExist(connection, newFile)) {
                FileAddition.addNewFile(connection,Variables.FILE_TABLE,inputStream, newFile);
            }
            else {
                if(Variables.adminUser)
                {
                    System.out.print("Do you want to disable the default version? (yes/no) ");
                    String defaultVersion = sc.next();
                    if(defaultVersion.equalsIgnoreCase(Variables.DEFAULT)){
                        DefaultVersion.addDefaultVersion(connection,Variables.FILE_TABLE,inputStream,newFile);
                    }else if(defaultVersion.equalsIgnoreCase(Variables.NOT_DEFAULT)) {
                        OverwriteVersion.addOverwriteFile(connection,inputStream, newFile);
                    }
                }
                else
                    DefaultVersion.addDefaultVersion(connection,Variables.FILE_TABLE,inputStream,newFile);
                }
        } catch (SqlQueryException e) {
                System.err.println(e.getMessage());
        } catch (FileSizeException e) {
                System.err.println(e.getMessage());
        }catch (FileNotFoundException e) {
                throw new IncorrectFilePathException("The file path is incorrect");
        }catch (InterruptedException ex) {
                throw new RuntimeException(ex);
        }
        System.out.println("Successfully added");
    }
}
