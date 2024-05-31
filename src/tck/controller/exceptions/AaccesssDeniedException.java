package tck.controller.exceptions;

public class AaccesssDeniedException extends Exception{
    public AaccesssDeniedException(){
        super("wrong username/password");
    }

}
