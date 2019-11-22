package utilities.Commands;

import utilities.Command;
import utilities.Coordinate;
import view.BasicView;

/**
 * Command object that tells the front end to move a given turtle from one location to another.
 *
 * @author Ben lawrence
 */
public class MoveTurtleCommand implements Command {
    private Coordinate coord1;
    private Coordinate coord2;
    private int turtleIndex;

    /**
     * Constructor for MoveTurtleCommand.
     *
     * @param index - index of the turlte being moved
     * @param x1 - starting x coordinate according to the turtle arena coordinate system
     * @param y1 - starting y coordinate according to the turtle arena coordinate system
     * @param x2 - ending x coordinate according to the turtle arena coordinate system
     * @param y2 - ending y coordinate according to the turtle arena coordinate system
     */
    public MoveTurtleCommand(int index, double x1, double y1, double x2, double y2) {
        coord1 = new Coordinate(x1, y1);
        coord2 = new Coordinate(x2, y2);
        turtleIndex = index;
    }

    /**
     * Execute method for this command object. Tells the view to move the turtle with the given index from coordinate 1 to coordinate 2
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.moveTurtle(turtleIndex, coord1, coord2);
    }
}
