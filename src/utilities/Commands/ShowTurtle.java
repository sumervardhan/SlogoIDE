package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object telling the front end to show a turtle on the screen. This does not add a turtle. It just ensures that the turtle if hidden before is now being displayed.
 *
 * @author Ben lawrence
 */
public class ShowTurtle implements Command {
    private int turtleIndex;

    /**
     * Constructor for ShowTurtle command.
     *
     * @param index - Integer index of the turtle that should be visible on the screen.
     */
    public ShowTurtle(int index) {
        turtleIndex = index;
    }

    /**
     * Execute method for this command. Tells the front end that the turtle with the given index should be visible to the user if not already visible.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.showTurtle(turtleIndex);
    }
}
