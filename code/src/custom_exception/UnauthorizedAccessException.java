package custom_exception;

public class UnauthorizedAccessException extends Exception{
    UnauthorizedAccessException(String message){
        super(message);
    }
}
