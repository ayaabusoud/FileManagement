package exceptions;

import java.sql.SQLException;

public class SqlQueryException extends SQLException {
    public SqlQueryException(String message){
        super(message);
    }
}
