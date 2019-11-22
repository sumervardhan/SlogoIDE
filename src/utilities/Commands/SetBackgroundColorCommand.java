package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object that tells the front end to set the background of the turtle arena
 *
 * @author Ben lawrence
 */
public class SetBackgroundColorCommand implements Command {
    private int index;

    /**
     * Constructor for SetBackgroundColorCommand.
     *
     * @param index - Index of the color stored in the front end palette that will be the new background color.
     */
    public SetBackgroundColorCommand(int index) {
        this.index = index;
    }

    /**
     * Execute method for this command. Tells the front end to change the background color to the color associated with index in the palette.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.changeBackgroundColor(index);
    }
}
