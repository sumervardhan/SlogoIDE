package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object that sets the pen color of a given turtle.
 *
 * @author Ben lawrence
 */
public class SetPenColorCommand implements Command {
    private int colorIndex;
    private int turtleIndex;

    /**
     * Constructor for SetPenColorCommand.
     *
     * @param turtleIndex - Index of the turtle whose pen color is being updated.
     * @param colorIndex - Palette index of new color for turtle's pen.
     */
    public SetPenColorCommand(int turtleIndex, int colorIndex) {
        this.turtleIndex = turtleIndex;
        this.colorIndex = colorIndex;
    }

    /**
     * Execute method for this command. Tells the front end to change the turtle with the given index to the color in the palette with the given index.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.changePenColor(turtleIndex, colorIndex);
    }
}
