package writeDB;

import encryption.EncryptionFile;
import encryption.IEncryptionAndDecryption;
import exceptions.FileSizeException;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class OverwriteFile {
    public static void updateFile(Connection connection, InputStream inputStream, FileInformation newFile) throws SQLException {
        String query ="Update file SET size = ?, context = ?,version = ?, versionType = ? WHERE name = ? AND type = ? And lastVersion = ?";
        IEncryptionAndDecryption EncryptionFile = new EncryptionFile();
        try {
            String Size= SizeConversion.convertSize(inputStream) ;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1,  Size);
            preparedStmt.setBlob(2,inputStream);
            preparedStmt.setInt(3, newFile.getVersion());
            preparedStmt.setInt(4, newFile.getVersionType());
            preparedStmt.setString(5, EncryptionFile.encryptAndDecrypt(newFile.getName()));
            preparedStmt.setString (6, newFile.getType());
            preparedStmt.setInt(7, 1);

           ;
            int result = preparedStmt.executeUpdate();
        } catch (SQLException e) {
            throw new SqlQueryException("Overwrite Query Failed");
        }
        catch (FileSizeException e) {
            System.err.println(e.getMessage());
        }
    }

}
