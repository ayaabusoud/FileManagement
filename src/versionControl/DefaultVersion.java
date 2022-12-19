package versionControl;

import file.FileInfo;
import readDB.GetFileInfo;
import variables.Variables;
import writeDB.AddFile;
import writeDB.UpdateLastVersion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultVersion {
        public static void defaultVersion(Connection connection,String tableName, InputStream inputStream, FileInfo newFile) throws SQLException, IOException {
        FileInfo previousFile = GetFileInfo.getInfo(connection,newFile);
        newFile.setVersion(previousFile.getVersion()+1);
        newFile.setVersionType(Variables.DEFAULT_VERSION_TYPE);
        UpdateLastVersion.updateToZero(connection,tableName,previousFile);
        AddFile.addNewFile(connection,tableName,inputStream, newFile);
    }
}
