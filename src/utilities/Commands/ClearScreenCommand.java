package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * This command object tells the front end view to clear it's screen.
 *
 * @author Ben lawrence
 */
public class ClearScreenCommand implements Command {

    /**
     * Constructor for the ClearScreenCommand. This class takes in no values as it is just telling the view to clear itself.
     */
    public ClearScreenCommand() {
        // Intentionally left blank
    }

    /**
     * This method is called to execute this command's function. In this case, the command object tells the BasicView clear its screen.
     *
     * @param view - BasicView being told to clear its screen.
     */
    @Override
    public void execute(BasicView view) {
        view.clearScreen();
    }
}
