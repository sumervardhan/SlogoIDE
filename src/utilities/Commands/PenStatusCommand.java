package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object that tells the front end whether the pen is up or down for a given turtle.
 *
 * @author Ben lawrence
 */
public class PenStatusCommand implements Command {
    private boolean penState;
    private int turtleIndex;

    /**
     * Constructor for the PenStatusCommand class.
     *
     * @param index - Index of the turtle whose pen state is being updated
     * @param state - New state of the pen. Whether True is up or down is up to the implementation of the front end. This just tells it what the correct assignment should be for
     *             the current state.
     */
    public PenStatusCommand(int index, boolean state) {
        penState = state;
        turtleIndex = index;
    }

    /**
     * Execute method for command object.  Tells the view to assign the new pen state (up/down) to the turtle with the given index.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.updatePenUp(turtleIndex, penState);
    }
}
