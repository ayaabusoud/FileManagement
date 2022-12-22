package operations.importOperation;

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
    public void importFile(Connection connection) throws SQLException, IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean filePathLoop = true;
        String filePath ="";
        InputStream inputStream = null;
        while (filePathLoop){
            filePathLoop =false;
            try {
                System.out.print("Enter file path: ");
                filePath = sc.next();
                inputStream = new FileInputStream(filePath);
            }catch (FileNotFoundException e){
                System.err.println(e.getMessage());
                filePathLoop =true;
                Thread.sleep(100);
            }
        }
        FileInfo newFile = FileNameAndType.splitNameAndType(filePath);

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
        System.out.println("Successfully added");
    }

}
