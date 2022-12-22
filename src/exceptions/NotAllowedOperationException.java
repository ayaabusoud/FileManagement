package exceptions;

public class NotAllowedOperationException extends NullPointerException{
    public NotAllowedOperationException(String message){
        super(message);
    }
}
