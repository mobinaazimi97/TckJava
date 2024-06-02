package tck.controller.exceptions;

public class FailedRequiermentException extends Exception{
    public FailedRequiermentException() {
        super("Person Does NOT Have Minimum Requirement !!!");
    }
}
