package tck.controller.exceptions;

public class FailedSignInException extends Exception{
    public FailedSignInException(){
        super("Sign In UnSuccess!" );
    }
}
