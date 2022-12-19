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
    public void importFile(Connection connection, String path) throws SQLException, IOException {
        Scanner sc = new Scanner(System.in);
        InputStream inputStream = new FileInputStream(path);
        FileInfo newFile = FileNameAndType.splitNameAndType(path);

        if (!CheckFileExistences.isExist(connection, Variables.FILE_TABLE, newFile)) {
            AddFile.addNewFile(connection,Variables.FILE_TABLE,inputStream, newFile);

        }
        else {
            System.out.print("Do you want to disable the default version? (yes/no) ");
            String defaultVersion = sc.next();
            if(defaultVersion.equalsIgnoreCase("no")){
                DefaultVersion.defaultVersion(connection,Variables.FILE_TABLE,inputStream,newFile);
            }else if(defaultVersion.equalsIgnoreCase("yes")) {
                OverwriteVersion.overwriteFile(connection,inputStream, newFile);
            }
        }
        System.out.println("Successfully added");
    }

}
