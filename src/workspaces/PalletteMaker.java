package workspaces;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This class creates and holds the visuals of selecting a palette for a workspace. Additionally, it updates the view with the new palette.
 *
 * @author Ben lawrence
 */
public class PalletteMaker implements WorkspaceSetting {
    public static final int NUM_COLORS = 9;
    private static final String SET_ERROR_MESSAGE = "Error setting palette of index: ";
    private static final double MAX_COLOR_VALUE = 255.0;
    private static final double MAX_COLOR_OPACITY = 1;
    private static final int NUM_ROWS = 1;

    private GridPane pallette;
    private List<ColorPicker> colors;

    /**
     * Constructor for PalletteMaker class. This class creates the palette and instantiates the javaFX objects used to control which colors are selected.
     */
    public PalletteMaker() {
        pallette = new GridPane();
        pallette.setGridLinesVisible(true);
        colors = new ArrayList<>();

        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLORS; c++) {
                colors.add(new ColorPicker(getRandomColor()));
                addColorPicker(r, c);
            }
        }
    }

    /**
     * Sets the palette of the view to the chosen palette from the user interface.
     *
     * @param v - View object whose palette is being updated.
     */
    @Override
    public void setProperty(View v) {
        for (int i = 0; i < colors.size(); i++) {
            Color c = colors.get(i).getValue();
            v.setPaletteIndex(i, (int) (c.getRed() * MAX_COLOR_VALUE), (int) (c.getGreen() * MAX_COLOR_VALUE), (int) (c.getBlue() * MAX_COLOR_VALUE));
        }
    }

    /**
     * Saves the properties of the palette as key + value pairs.
     *
     * @param properties - Java Properties object responsible for saving the workspace to a properties file.
     */
    @Override
    public void saveProperties(Properties properties) {
        String key = "setPaletteIndex";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < colors.size(); i++) {
            int red = (int) (colors.get(i).getValue().getRed() * MAX_COLOR_VALUE);
            int green = (int) (colors.get(i).getValue().getGreen() * MAX_COLOR_VALUE);
            int blue = (int) (colors.get(i).getValue().getBlue() * MAX_COLOR_VALUE);
            String val = "" + DefaultValueSelector.DEFAULT_TURTLE_IMAGE_INDEX + "," + red + "," + green + "," + blue;
            stringBuilder.append(val);
            if (i != colors.size() - 1) {
                stringBuilder.append("|");
            }
        }
        properties.setProperty("" + this.getClass(), stringBuilder.toString());
    }

    /**
     *
     * @return - Returns the top level node of the JavaFX tree holding the controls for selecting palette colors.
     */
    @Override
    public Node getNode() {
        return pallette;
    }

    /**
     * Sets the palette to the colors stored in the string. Used when reading a workspace from a file.
     *
     * @param vals - Values of each palette index along with its associated color.
     */
    @Override
    public void setSettingValue(String vals) {
        String[] stringColors = vals.split("|");
        for (int i = 0; i < stringColors.length; i++) {
            String[] colorVals = stringColors[i].split(",");
            try {
                int r = Integer.parseInt(colorVals[1]);
                int g = Integer.parseInt(colorVals[2]);
                int b = Integer.parseInt(colorVals[3]);
                colors.set(Integer.parseInt(colorVals[0]), new ColorPicker(getSetColor(r, g, b)));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, SET_ERROR_MESSAGE + colorVals[0]).showAndWait();
            }
        }
    }

    private void addColorPicker(int row, int col) {
        pallette.add(new Text("" + col), col, row);
        pallette.add(colors.get(col), col, row + 1);
    }

    private Color getRandomColor() {
        return new Color(Math.random(), Math.random(), Math.random(), MAX_COLOR_OPACITY);
    }

    private Color getSetColor(int r, int g, int b) {
        return new Color((int) (r * MAX_COLOR_VALUE), (int) (g * MAX_COLOR_VALUE), (int) (b * MAX_COLOR_VALUE), MAX_COLOR_OPACITY);
    }
}
