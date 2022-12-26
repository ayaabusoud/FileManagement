package database;
import exceptions.ConnectionMySqlException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class SqlDatabase implements IDatabase {
    private static final Logger logger = LogManager.getLogger(SqlDatabase.class);
    private static boolean connectionStatus = false;
    private final static String URL ="jdbc:mysql://localhost:3306/file_management";
    private final static String USERNAME ="root";
    private final static String PASSWORD ="";
    private final static String CONNECTION_CLASS ="com.mysql.cj.jdbc.Driver";
    private static SqlDatabase instance;
    private static Connection connection;

    private SqlDatabase(){}

    public static IDatabase createInstance(){
        logger.debug("enter createInstance function");
        if(instance == null){
            logger.debug("create new SqlDatabase object");
            instance = new SqlDatabase();
            logger.debug("exit createInstance function");
        }return instance;
    }
    public  Connection connectDB() throws ConnectionMySqlException {

            if(connectionStatus == true){
                System.out.println("it's already connected");
            }else{
            try {
                logger.debug("create connection, with url: "+ URL +", username: "+USERNAME
                        +", connection class: "+CONNECTION_CLASS);
                Class.forName(CONNECTION_CLASS);
                connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                connectionStatus = true;
                System.out.println("connected to DB");
            }catch ( ClassNotFoundException e){
                throw new ConnectionMySqlException("Not able to connect to the DB, try again later.");
            }catch (SQLException e){
                throw new ConnectionMySqlException("Not able to connect to the DB, try again later.");
            }
            logger.debug("exit connection function");
        }
        return connection;
    }


    public void closeDB(Connection connection) throws ConnectionMySqlException {
        try {
            logger.debug("enter closedb function");
            connectionStatus = false;
            connection.close();
            logger.debug("closed connection");
        } catch (SQLException e) {
            throw new ConnectionMySqlException("Close connection failed");
        }
        logger.debug("exit closedb function");
    }

}
