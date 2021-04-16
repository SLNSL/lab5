package exception;


public abstract class AbstractException extends IllegalArgumentException{

    String message;

    public AbstractException(){};

    public AbstractException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
