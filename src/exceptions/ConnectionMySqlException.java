package exceptions;

import java.sql.SQLException;

public class ConnectionMySqlException extends SQLException {
    public ConnectionMySqlException(String message){
        super(message);
    }
}
