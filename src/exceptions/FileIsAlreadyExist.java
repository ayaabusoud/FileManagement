package exceptions;

import java.sql.SQLException;

public class FileIsAlreadyExist extends SQLException {
    public FileIsAlreadyExist(String message){
        super(message);
    }
}
