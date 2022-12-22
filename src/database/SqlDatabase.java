package database;
import exceptions.connectionMySqlException;

import java.sql.*;

public class SqlDatabase implements IDatabase {
    private static boolean connectionStatus = false;
    private final static String URL ="jdbc:mysql://localhost:3306/file_management";
    private final static String USERNAME ="root";
    private final static String PASSWORD ="";
    private final static String CONNECTION_CLASS ="com.mysql.cj.jdbc.Driver";
    private static SqlDatabase instance;
    private static Connection connection;

    private SqlDatabase(){}

    public static IDatabase createInstance(){
        if(instance == null){
            instance = new SqlDatabase();
        }return instance;
    }
    public  Connection connectDB() throws connectionMySqlException {

            if(connectionStatus == true){
                System.out.println("it's already connected");
            }else{
            try{
            Class.forName(CONNECTION_CLASS);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            connectionStatus = true;
            System.out.println("connected to DB");
            }
            catch (Exception e){
            throw new connectionMySqlException("Not able to connect to the DB, try again later.");
            }
        }
        return connection;
    }


    public void closeDB(Connection connection) throws SQLException {
        if(connection == null){
            //throw null exception
        }
        connectionStatus = false;
        connection.close();
    }
}
