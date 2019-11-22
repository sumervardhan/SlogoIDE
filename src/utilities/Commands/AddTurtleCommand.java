package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * This command object tells the basic view to add a new turtle with the given index.
 *
 * @author Ben lawrence
 */
public class AddTurtleCommand implements Command {
    private int turtleIndex;

    /**
     * Constructor for the AddTurtleCommand.
     *
     * @param index - index of the new turtle being created. Can be any integer
     */
    public AddTurtleCommand(int index) {
        turtleIndex = index;
    }

    /**
     * This method is called to execute this command's function. In this case, the command object tells the BasicView to add a new turtle.
     *
     * @param view - BasicView being being told to add a turtle
     */
    @Override
    public void execute(BasicView view) {
        view.addTurtle(turtleIndex);
    }
}
