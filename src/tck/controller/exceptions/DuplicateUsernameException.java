package tck.controller.exceptions;

public class DuplicateUsernameException extends Exception {
    public DuplicateUsernameException() {
        super("Duplicate Username!");
    }
}
