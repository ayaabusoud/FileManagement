package readDB;

import encryption.DecryptionFile;
import exceptions.SqlQueryException;
import file.FileInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetInfo {
    public static FileInfo getInfo(FileInfo file, ResultSet result) throws SqlQueryException {
        try {
        if (result.next()) {
           file.setName(DecryptionFile.decryption(result.getString("name")));
            file.setType(result.getString("type"));
            file.setContext(result.getBlob("context"));
            file.setVersion(result.getInt("version"));
            file.setSize(result.getString("size"));
            file.setVersionType(result.getInt("versionType"));
        }
    }catch (SQLException e) {
            throw new SqlQueryException("failed to get file data");
        }
        return file;
    }
}
