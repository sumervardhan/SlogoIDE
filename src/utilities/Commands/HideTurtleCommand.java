package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * This command object tells the front end view to hide a turtle.
 *
 * @author Ben lawrence
 */
public class HideTurtleCommand implements Command {
    private int turtleIndex;

    /**
     * Constructor for the HideTurtleCommand.
     *
     * @param index - Index of the turtle that the view should stop displaying on the screen.
     */
    public HideTurtleCommand(int index) {
        turtleIndex = index;
    }

    /**
     * Execute method for the command object. Tells the front end view to stop displaying the turtle with the given index if it exists.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.hideTurtle(turtleIndex);
    }
}
