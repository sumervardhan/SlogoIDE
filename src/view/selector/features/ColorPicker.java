package view.selector.features;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * This class is responsible for storing the color palette for the front end. When the user asks the pen or background color to change colors, this class takes care of the index
 * to color translation.
 *
 * @author Ben lawrence
 */
public class ColorPicker {
    public static final Color DEFAULT_COLOR = Color.WHITE;
    public static final String COLOR_FILE_NAME = "colors";
    public static final double MAX_COLOR_VAL = 255.0;
    public static final double OPACITY = 1;

    private ResourceBundle resources;
    private Map<String, Color> colors;

    /**
     * Constructor for the ColorPicker class. This constructor takes in no arguments as it does not depend on any other classes.
     *
     * The palette is read in from a file. If any colors are incorrectly coded, they are replaced with a default color.
     */
    public ColorPicker() {
        colors = new HashMap<>();
        resources = ResourceBundle.getBundle(FeatureConstants.RESOURCE_PATH + COLOR_FILE_NAME);
        resources.getKeys().asIterator().forEachRemaining(key -> {
            try {
                String[] vals = resources.getString(key).split(",");
                int r = Integer.parseInt(vals[0]);
                int g = Integer.parseInt(vals[1]);
                int b = Integer.parseInt(vals[2]);
                colors.put(key, rgbToHex(r, g, b));
            } catch (Exception e) {
                colors.put(key, DEFAULT_COLOR);
            }
        });
    }

    /**
     * Takes in an index and returns the JavaFX color associated with that index in the palette.
     *
     * @param index - Integer index of palette color.
     * @return -Returns the color associated with the given index in the palette. If no color found, just returns the default color.
     */
    public Color getColorFromIndex(int index) {
        String key = "" + index;
        if (colors.containsKey(key))
            return colors.get(key);
        return DEFAULT_COLOR;
    }

    /**
     * Adds a new color with the given index to the palette. If the index already exists, changes the index to the new RGB color.
     *
     * @param index - Integer index of color being created/changed
     * @param r - Integer red value of color in range 0 - 255
     * @param g - Integer green value of color in range 0 - 255
     * @param b - Integer blue value of color in range 0 - 255
     */
    public void addNewColorIndex(int index, int r, int g, int b) {
        try {
            colors.put("" + index, rgbToHex(r, g, b));
        } catch (Exception e) {
            colors.put("" + index, DEFAULT_COLOR);
        }
    }

    private Color rgbToHex(int r, int g, int b) {
        return new Color(r / MAX_COLOR_VAL, g / MAX_COLOR_VAL, b / MAX_COLOR_VAL, OPACITY);
    }
}
