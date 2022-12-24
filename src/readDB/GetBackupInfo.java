package readDB;

import encryption.EncryptionFile;
import encryption.IEncrAndDecrption;
import exceptions.SqlQueryException;
import file.FileInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


    public abstract class GetBackupInfo {
        private final static String QUERY = "SELECT * FROM backup WHERE name = ? AND type = ? AND version = ?";

        public static FileInformation getInfo(Connection connection, FileInformation file) throws SqlQueryException {
            PreparedStatement preparedStmt = null;
            IEncrAndDecrption EncryptionFile = new EncryptionFile();
            ResultSet result;
            try {
                preparedStmt = connection.prepareStatement(QUERY);
                preparedStmt.setString(1, EncryptionFile.IncAndDec(file.getName()));
                preparedStmt.setString(2, file.getType());
                preparedStmt.setInt(3, file.getVersion() - 1);
                result = preparedStmt.executeQuery();
                return InfoAboutFile.getInfo(file,result);
            } catch (SQLException e) {
                throw new SqlQueryException("Get Backup Info Query Failed");
            }

        }
}
