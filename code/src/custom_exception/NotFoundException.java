package custom_exception;

public class NotFoundException extends Exception{
    NotFoundException(String message){
        super(message);
    }
}
