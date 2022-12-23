package operations.export;

import exceptions.RunTimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Export implements IExport {

    @Override
    public void export(Connection connection ,String FileName ) throws RunTimeException {
        try{
            PreparedStatement p = connection.prepareStatement("SELECT context " +
                    "INTO OUTFILE '/MyFolder/student.java' " +
                    "FROM file WHERE name = ? " );
            p.setString(1,FileName);
            System.out.println("export successfully ... ");
            p.executeQuery() ;
        }
        catch (SQLException e) {
            throw new RunTimeException("Export Query Failed");
        }

    }
}