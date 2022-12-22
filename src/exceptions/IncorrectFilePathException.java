package exceptions;

import java.io.FileNotFoundException;

public class IncorrectFilePathException extends FileNotFoundException {
    public IncorrectFilePathException(String message){
        super(message);
    }
}
