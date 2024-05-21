package tck.controller.exceptions;

public class NoPersonFoundException extends Exception{
    public NoPersonFoundException(){
        super("No person found");
    }
}
