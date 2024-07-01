package tck.controller.exceptions;

public class NoAdminFoundException extends Exception{
    public NoAdminFoundException(){
        super("No Admin Found");
    }
}
