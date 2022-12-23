package exceptions;

import java.sql.SQLException;

public class RunTimeException extends SQLException {
    public RunTimeException(String message){
        super(message);
    }
}
