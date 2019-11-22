package workspaces;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import view.View;
import view.selector.features.FeatureConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * In the event that the user creates a workspace without entering in any values, this class picks a set of default values to assign the workspace. Additionally, this class
 * holds the controls for choosing the initial turtle image, pen color, and pen size.
 *
 * @author Ben lawrence
 */
public class DefaultValueSelector implements WorkspaceSetting {
    public static final int DEFAULT_TURTLE_IMAGE_INDEX = 0;
    private static final String INITIAL_LOADING_ERROR_MESSAGE = "Error loading turtle images. Project files configured incorrectly";
    private static final String PROMPT_TEXT = "Color";
    private static final String SET_PEN_COLOR_METHOD = "changePenColor";
    private static final String SET_DEFAULT_PEN_COLOR_METHOD = "setDefaultPenColor";
    private static final int DEFAULT_PEN_INDEX = 1;
    private static final String SET_PEN_SIZE_METHOD = "setPenSize";
    private static final String SET_DEFAULT_PEN_SIZE_METHOD = "setDefaultPenWidth";
    private static final int DEFAULT_PEN_SIZE = 5;
    private static final String SET_TURTLE_IMAGE_METHOD = "changeTurtleImage";
    private static final String SET_DEFAULT_TURTLE_IMAGE_METHOD = "setDefaultTurtleImage";
    private static final int DEFAULT_TURTLE_INDEX = 0;

    private GridPane gridPane;
    private ComboBox colors;
    private TextField size;
    private ComboBox turtleSelector;
    private Map<String, Integer> turtles;
    private ResourceBundle resources;

    /**
     * Constructor for the DefaultValueSelector class. This class takes in no arguments.
     *
     * This class is responsible for initializing all the javaFX objects that will be in the JavaFX tree.
     */
    public DefaultValueSelector() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        colors = new ComboBox();
        colors.setPromptText(PROMPT_TEXT);
        for (int x = 0; x < PalletteMaker.NUM_COLORS; x++) {
            colors.getItems().add("" + x);
        }

        size = new TextField();
        turtleSelector = new ComboBox();
        turtles = new HashMap<>();

        resources = ResourceBundle.getBundle(FeatureConstants.RESOURCE_PATH + "shapes");
        resources.getKeys().asIterator().forEachRemaining(this::fillTurtleOptions);

        gridPane.add(new Text("Default Turtle Image"), 0, 0);
        gridPane.add(turtleSelector, 0, 1);

        gridPane.add(new Text("Default pen color"), 1, 0);
        gridPane.add(colors, 1, 1);

        gridPane.add(new Text("Default pen size"), 2, 0);
        gridPane.add(size, 2, 1);

    }

    /**
     * This class sets the view's default pen color, pen size, and turtle image based on the selected values (or the default ones if nothing is selected).
     *
     * @param v - The view object whose parameters are being set.
     */
    @Override
    public void setProperty(View v) {
        try {
            v.setDefaultPenColor(Integer.parseInt((String) colors.getValue()));
            v.changePenColor(DEFAULT_TURTLE_INDEX, Integer.parseInt((String) colors.getValue()));
        } catch (Exception e) {
            v.changePenColor(DEFAULT_TURTLE_INDEX, DEFAULT_PEN_INDEX);
            v.setDefaultPenColor(DEFAULT_PEN_INDEX);
        }

        try {
            v.setDefaultPenWidth(Double.parseDouble(size.getText()));
            v.setPenSize(DEFAULT_TURTLE_INDEX, Double.parseDouble(size.getText()));
        } catch (Exception e) {
            v.setPenSize(DEFAULT_TURTLE_INDEX, DEFAULT_PEN_SIZE);
            v.setDefaultPenWidth(DEFAULT_PEN_SIZE);
        }

        try {
            v.setDefaultTurtleImage(turtles.get(turtleSelector.getValue()));
            v.changeTurtleImage(DEFAULT_TURTLE_INDEX, turtles.get(turtleSelector.getValue()));
        } catch (Exception e) {
            v.changeTurtleImage(DEFAULT_TURTLE_INDEX, DEFAULT_TURTLE_IMAGE_INDEX);
            v.setDefaultTurtleImage(DEFAULT_TURTLE_IMAGE_INDEX);
        }
    }

    /**
     * Saves the selected properties (or default ones) to a properties file for later loading of the saved workspace.
     *
     * @param properties - The Java properties object that allows the string key + value pairs to be saved.
     */
    @Override
    public void saveProperties(Properties properties) {
        String penColor = "" + ((colors.getValue() == null) ? DEFAULT_PEN_INDEX : colors.getValue());
        properties.setProperty(SET_PEN_COLOR_METHOD, penColor);
        properties.setProperty(SET_DEFAULT_PEN_COLOR_METHOD, penColor);

        String penWidth = "" + ((size.getText().equals("")) ? DEFAULT_PEN_SIZE : size.getText());
        properties.setProperty(SET_PEN_SIZE_METHOD, penWidth);
        properties.setProperty(SET_DEFAULT_PEN_SIZE_METHOD, penWidth);

        String turtleImage = "" + ((turtleSelector.getValue() == null) ? DEFAULT_TURTLE_IMAGE_INDEX : turtles.get(turtleSelector.getValue()));
        properties.setProperty(SET_TURTLE_IMAGE_METHOD, turtleImage);
        properties.setProperty(SET_DEFAULT_TURTLE_IMAGE_METHOD, turtleImage);
    }

    /**
     * return - Returns the top node of the JavaFX tree for displaying the controls for choosing pen color, image, and pens size.
     */
    @Override
    public Node getNode() {
        return gridPane;
    }

    /**
     * Sets the turtle image, pen color, and pen size to the values given in the argument. This is called when loading a saved file.
     *
     * @param vals - The string values of the pen color, pen size, and turtle image all stored in one string.
     */
    @Override
    public void setSettingValue(String vals) {
        // TODO: This should save the values given in the string
    }

    private void fillTurtleOptions(String key) {
        try {
            turtleSelector.getItems().add(resources.getString(key));
            turtles.put(resources.getString(key), Integer.parseInt(key));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, INITIAL_LOADING_ERROR_MESSAGE).showAndWait();
        }
    }
}
