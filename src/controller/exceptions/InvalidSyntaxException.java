package controller.exceptions;

/**
 * Exception meant to be thrown in the backend in the event that the user enters in incorrect input.
 *
 * @author Ben Lawrence
 */
public class InvalidSyntaxException extends RuntimeException {
    // for serialization
    private static final long serialVersionUID = 1L;

    /**
     * Create an exception based on an issue in our code.
     */
    public InvalidSyntaxException(String message, Object... values) {
        super(String.format(message, values));
    }

    /**
     * Create an exception based on a caught exception with a different message.
     */
    public InvalidSyntaxException(Throwable cause, String message, Object... values) {
        super(String.format(message, values), cause);
    }

    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public InvalidSyntaxException(Throwable cause) {
        super(cause);
    }

}
