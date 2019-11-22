package view.statepane;

/**
 * Exception thrown whenever an input into the state pane is not a valid input.
 *
 * @author Goh Khian Wei
 */
public class IllegalInputException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for exception
     *
     * @param message Format of message to be stored in exception that can be viewed by other classes which catch the exception
     * @param values  Strings representing specific values to be added to the message
     */
    public IllegalInputException(String message, Object... values) {
        super(String.format(message, values));
    }

    /**
     * Constructor for exception that does not require any message
     */
    public IllegalInputException() {
        super();
    }
}
