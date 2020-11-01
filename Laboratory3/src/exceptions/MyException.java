package exceptions;

public class MyException extends Exception{
    public MyException(String errorMessage){
        super("Error:" + errorMessage);
    }
}
