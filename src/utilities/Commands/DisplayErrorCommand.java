package utilities.Commands;


import utilities.Command;
import view.BasicView;

/**
 * This command object tells the front end view to display an error message to the user.
 *
 * @author Ben lawrence
 */
public class DisplayErrorCommand implements Command {
    private String message;

    /**
     * Constructor for the DisplayErrorCommand.
     *
     * @param errorMessage - String value containing error message to be displayed to the user. This could be a Java exception message or a custom backend message.
     */
    public DisplayErrorCommand(String errorMessage) {
        message = errorMessage;
    }

    /**
     * This method is called to execute this command's function. In this case, the command object tells the BasicView to display the stored error message.
     *
     * @param view - BasicView being told to print the error message to the user.
     */
    @Override
    public void execute(BasicView view) {
        view.displayErrorMessage(message);
    }

}
