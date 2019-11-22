package utilities;

import view.BasicView;

/**
 * Command interface. Follows command design pattern. Each command object tells the BasicView to perform a given action or set of actions.
 *
 * @author Ben Lawrence
 */
public interface Command {

    /**
     * Method called by object wanting to execute the command
     *
     * @param view - BasicView being executed on
     */
    void execute(BasicView view);
}
