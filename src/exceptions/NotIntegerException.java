package exceptions;

import java.util.InputMismatchException;

public class NotIntegerException extends InputMismatchException {
 public NotIntegerException(String message){
     super(message);
 }
}
