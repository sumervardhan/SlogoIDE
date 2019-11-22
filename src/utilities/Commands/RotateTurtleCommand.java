package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object that tells the front end to rotate a turtle clockwise
 *
 * @author Ben lawrence
 */
public class RotateTurtleCommand implements Command {
    private double degrees;
    private int turtleIndex;

    /**
     * Constructor for RotateTurtleCommand class.
     *
     * @param index - The index of the turtle being rotated
     * @param degrees - The number of degrees (in degrees) to rotate the turtle clockwise
     */
    public RotateTurtleCommand(int index, double degrees) {
        this.degrees = degrees;
        turtleIndex = index;
    }

    /**
     * Execute method for this command. Tells the view to rotate the turtle with the given index by degrees amount.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.rotateTurtle(turtleIndex, degrees);
    }

}
