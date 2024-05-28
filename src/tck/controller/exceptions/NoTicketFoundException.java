package tck.controller.exceptions;

public class NoTicketFoundException extends Exception {
    public NoTicketFoundException() {
        super("No Ticket Found");
    }
}
