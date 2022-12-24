package operations.importOperation;

import exceptions.IncorrectFilePathException;
import file.FileInfo;
import file.FileNameAndType;
import readDB.CheckFileExistences;
import variables.Variables;
import versionControl.DefaultVersion;
import versionControl.OverwriteVersion;
import writeDB.AddFile;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Import implements IImport {

    @Override
    public void importFile(Connection connection) throws IncorrectFilePathException {
        Scanner sc = new Scanner(System.in);
        boolean filePathLoop = true;
        String filePath ="";
        InputStream inputStream = null;

            try {
                System.out.print("Enter file path: ");
                filePath = sc.next();
                inputStream = new FileInputStream(filePath);
                Thread.sleep(100);
            }catch (FileNotFoundException e) {
                throw new IncorrectFilePathException("The file path is incorrect");
            }
            catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

        FileInfo newFile = FileNameAndType.splitNameAndType(filePath);

        try {
            if (!CheckFileExistences.isExist(connection, Variables.FILE_TABLE, newFile)) {
                AddFile.addNewFile(connection,Variables.FILE_TABLE,inputStream, newFile);
            }
            else {
                if(Variables.AdminUser)
                {
                    System.out.print("Do you want to disable the default version? (yes/no) ");
                    String defaultVersion = sc.next();
                    if(defaultVersion.equalsIgnoreCase("no")){
                        DefaultVersion.defaultVersion(connection,Variables.FILE_TABLE,inputStream,newFile);
                    }else if(defaultVersion.equalsIgnoreCase("yes")) {
                        OverwriteVersion.overwriteFile(connection,inputStream, newFile);
                    }
                }
                else
                    DefaultVersion.defaultVersion(connection,Variables.FILE_TABLE,inputStream,newFile);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Successfully added");
    }

}
