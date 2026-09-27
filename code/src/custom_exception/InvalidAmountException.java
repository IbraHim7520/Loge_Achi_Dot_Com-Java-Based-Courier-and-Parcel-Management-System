package custom_exception;

public class InvalidAmountException extends Exception{
    InvalidAmountException(String message){
        super(message);
    }
}
