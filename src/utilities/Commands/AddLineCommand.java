package utilities.Commands;

import utilities.Command;
import utilities.Coordinate;
import view.BasicView;

/**
 * Tells the BasicView object to add a line for the specific turtle index with given starting and ending coordinates. This follows the command design pattern.
 *
 * @author Ben Lawrence
 */
public class AddLineCommand implements Command {
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private int turtleIndex;

    /**
     * Constructor for AddLineCommand
     * @param index - turtle index of turtle which moved causing the line to be drawn
     * @param x1 - starting x coordinate
     * @param y1 - starting y coordinate
     * @param x2 - ending x coordinate
     * @param y2 - ending y coordinate
     */
    public AddLineCommand(int index, double x1, double y1, double x2, double y2) {
        startCoordinate = new Coordinate(x1, y1);
        endCoordinate = new Coordinate(x2, y2);
        turtleIndex = index;
    }

    /**
     * Execute command following execute design patter. Tells view to draw a line
     *
     * @param view - BasicView object that will actually draw the line
     */
    @Override
    public void execute(BasicView view) {
        view.addLine(turtleIndex, startCoordinate, endCoordinate);
    }
}
