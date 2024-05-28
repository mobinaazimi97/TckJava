package tck.controller.exceptions;

public class NoResponseFoundException extends Exception{
    public NoResponseFoundException(){
        super("No reponse found");
    }

}
