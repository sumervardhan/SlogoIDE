package utilities.Commands;

import utilities.Command;
import view.BasicView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Command object that tells the front end to update its list of user defined commands entered thus far.
 *
 * @author Ben lawrence
 */
public class UpdateUserCommandsCommand implements Command {
    private List<String> userCommands;

    /**
     * Constructor for the UpdateUserCommandsCommand
     *
     * @param userCommands - List of strings representing the user defined commands that have been entered thus far.
     */
    public UpdateUserCommandsCommand(List<String> userCommands) {
        userCommands = new ArrayList<>(userCommands);
        this.userCommands = Collections.unmodifiableList(userCommands);
    }

    /**
     * Execute method for this command. Tells the front end to update its list of user defined commands with this more current list.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.updateUserCommands(userCommands);
    }
}
