package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object that tells the front end to change the pen size for a given turtle.
 *
 * @author Ben lawrence
 */
public class SetPenSizeCommand implements Command {
    private double pixels;
    private int turtleIndex;

    /**
     * Constructor for the SetPenSizeCommand.
     *
     * @param index - Index of turtle whose pen size is changing.
     * @param pixels - The new double size of turtle's pen in pixels.
     */
    public SetPenSizeCommand(int index, double pixels) {
        this.pixels = pixels;
        this.turtleIndex = index;
    }

    /**
     * Execute method for this command. Tells the front end to update the turtle with the given index to have a pen with the given number of pixels in width.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.setPenSize(turtleIndex, pixels);
    }
}
