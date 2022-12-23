package readDB;

import encryption.EncryptionFile;
import exceptions.FileIsAlreadyExist;
import file.FileInfo;
import variables.Variables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExportFiles {
    public static void export(Connection connection, FileInfo file, String folderName) throws FileIsAlreadyExist {
        try {
            if(CheckFileExistences.isExist(connection, Variables.FILE_TABLE,file)){

                PreparedStatement statement = connection.prepareStatement("SELECT context " +
                        "INTO OUTFILE '/"+folderName+"/"+file.getName()+"."+file.getType()+"' " +
                        "FROM file WHERE name = ? And type = ? And lastVersion = ?" );
                statement.setString(1, EncryptionFile.encryption(file.getName()));
                statement.setString(2,file.getType());
                statement.setInt(3,1);
                statement.executeQuery() ;
                System.out.println("Exported successfully");
            }else {
                System.out.println("The file doesn't exist");
            }
        }catch(SQLException e){
            throw new FileIsAlreadyExist("The file is already exists in this folder, delete it or change the folder name");
        }
    }
}
