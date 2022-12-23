package operations.export;

import encryption.EncryptionFile;
import exceptions.FileIsAlreadyExist;
import file.FileInfo;
import readDB.CheckFileExistences;
import readDB.ExportFiles;
import variables.Variables;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Export implements IExport {

    @Override
    public void export(Connection connection , FileInfo file ){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter folder name: ");
        String folder = sc.next();

        File newFolder = new File("/"+folder);
        boolean bool = newFolder.mkdir();
        try {
            ExportFiles.export(connection,file,folder);
        }catch (FileIsAlreadyExist e){
            System.err.println(e.getMessage());
        }


    }
}