package tck.controller.exceptions;

public class AccessDeniedException extends Exception{
    public AccessDeniedException(){
        super("wrong data");
    }

}
