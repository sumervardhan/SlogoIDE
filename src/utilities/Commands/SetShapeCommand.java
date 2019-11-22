package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object that tells the front end to change the shape of a given turtle.
 *
 * @author Ben lawrence
 */
public class SetShapeCommand implements Command {
    private int shapeIndex;
    private int turtleIndex;

    /**
     * Constructor for SetShapeCommand.
     *
     * @param turtleIndex - Integer index of the turtle whose shape is being changed.
     * @param shapeIndex - Index of the new shape of the turtle
     */
    public SetShapeCommand(int turtleIndex, int shapeIndex) {
        this.turtleIndex = turtleIndex;
        this.shapeIndex = shapeIndex;
    }

    /**
     * Execute method for this command. Tells the front end to change the shape of the turtle with the given index to the turtle's shape associated with the given shape index.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.changeTurtleImage(turtleIndex, shapeIndex);
    }
}
