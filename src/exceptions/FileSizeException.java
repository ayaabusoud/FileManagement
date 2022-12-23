package exceptions;

import java.io.IOException;

public class FileSizeException extends IOException {
    public FileSizeException(String message){
        super(message);
    }
}
