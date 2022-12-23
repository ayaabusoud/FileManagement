package exceptions;

import java.sql.SQLException;

public class SQLthrException extends SQLException {
    public SQLthrException(String message){
        super(message);
    }
}
