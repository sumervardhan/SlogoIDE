package utilities.Commands;

import utilities.Command;
import view.BasicView;

/**
 * Command object to update the palette with a new index and color values.
 *
 * @author Ben lawrence
 */
public class SetPaletteCommand implements Command {
    private int index;
    private int red;
    private int green;
    private int blue;

    /**
     * Constructor for SetPaletteCommand.
     *
     * @param index - Palette index for new/change in color.
     * @param red - Integer value in 0-255 representing red value in palette color.
     * @param green - Integer value in 0-255 representing greem value in palette color.n
     * @param blue - Integer value in 0-255 representing blue value in palette color.
     */
    public SetPaletteCommand(int index, int red, int green, int blue) {
        this.index = index;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Execute method for this command. Tells the front end to assign the given palette index the RGB color values given.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.setPaletteIndex(index, red, green, blue);
    }
}
