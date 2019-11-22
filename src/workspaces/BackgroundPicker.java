package workspaces;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import view.View;

import java.util.Properties;

/**
 * This class is responsible for allowing the user to pick the background color of the turtle arena graphically when building a workspace.
 *
 * @author Ben lawrence
 */
public class BackgroundPicker implements WorkspaceSetting {
    private static final String SET_ERROR_MESSAGE = "Error setting background color from saved value";
    private static final String PROMPT_TEXT = "Background Color";
    private static final int DEFAULT_BACKGROUND_INDEX = 0;
    private ComboBox colors;

    /**
     * Constructor for the BackgroundPicker class. This class takes in no arguments.
     *
     * The constructor creates the JavaFX object(s) used to allowing the user to pick which color to make the background color.
     */
    public BackgroundPicker() {
        colors = new ComboBox();
        colors.setPromptText(PROMPT_TEXT);

        for (int x = 0; x < PalletteMaker.NUM_COLORS; x++) {
            colors.getItems().add("" + x);
        }
    }

    /**
     * This class follows the command object design. Here this class is telling the view to change its background color to the color with the index picked by the user.
     *
     * @param v - View object whose background color is to be updated.
     */
    @Override
    public void setProperty(View v) {
        try {
            v.changeBackgroundColor(Integer.parseInt((String) colors.getValue()));
        } catch (Exception e) {
            v.changeBackgroundColor(DEFAULT_BACKGROUND_INDEX);
        }
    }

    /**
     * Saves the chosen background color so that this workspace can be loaded later.
     *
     * @param properties - Java Properties object where all the properties will be saved.
     */
    @Override
    public void saveProperties(Properties properties) {
        String val = ((colors.getValue() == null) ? "" + DEFAULT_BACKGROUND_INDEX : (String) colors.getValue());
        properties.setProperty("" + this.getClass(), val);
    }

    /**
     * @return - Returns the top node of the javaFX tree holding the controls for the user to choose the background color.
     */
    @Override
    public Node getNode() {
        return colors;
    }

    /**
     * Sets the active background color to the value given
     *
     * @param val - String value representing the new active background color index.
     */
    @Override
    public void setSettingValue(String val) {
        try {
            colors.setValue(val);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, SET_ERROR_MESSAGE).showAndWait();
        }

    }
}
