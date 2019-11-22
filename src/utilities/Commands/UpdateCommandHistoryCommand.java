package utilities.Commands;

import utilities.Command;
import view.BasicView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Command object that tells the front end to update its command history.
 *
 * @author Ben lawrence
 */
public class UpdateCommandHistoryCommand implements Command {
    private List<String> history;

    /**
     * Constructor for UpdateCommandHistoryCommand.
     *
     * @param history - A List of strings representing every command (both valid and invalid) that has been entered by the user thus far.
     */
    public UpdateCommandHistoryCommand(List<String> history) {
        history = new ArrayList<>(history);
        this.history = Collections.unmodifiableList(history);
    }

    /**
     * Execute method for this command. Tells the front end to update its list of commands to the current list.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.updateCommandHistory(history);
    }
}
