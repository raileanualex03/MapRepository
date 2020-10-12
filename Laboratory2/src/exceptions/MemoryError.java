package exceptions;

public class MemoryError extends Exception{

    public MemoryError(String errorMessage){

        super("ERROR:" + errorMessage);
    }
}
