package view.statepane;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import view.StateObserver;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Class which implements SingleTurtleState and so is able to store and dynamically update stored state of a turtle, and display this stored state in a graphcial view.
 * Also able to allow user to dynamically modify stored state and inform the StateObserver whenever the stored state is modified by the user, using a simplified observer pattern.
 * Uses reflection to initialise each possible state field, store its name, its graphical position and its event handler when modified.
 *
 * @author Goh Khian Wei
 */
public class BasicSingleTurtleState implements SingleTurtleState {

    private final int id;

    private Map<String, TextField> editableOptions;
    private GridPane turtlePane;
    private StateObserver observer;
    private ResourceBundle errorResource;

    /**
     * Constructor
     *
     * @param statesResources Resource bundle from which to initialise the possible state fields from
     * @param turtleIndex     Index of turtle whose state is being stored
     * @param observer        StateObserver to be updated whenever state is dynamically changed by a user
     */
    public BasicSingleTurtleState(ResourceBundle statesResources, int turtleIndex, StateObserver observer) {
        this.observer = observer;
        id = turtleIndex;
        errorResource = ResourceBundle.getBundle(StatePaneConstants.ERROR_RESOURCES);

        editableOptions = new HashMap<>();
        turtlePane = new GridPane();
        turtlePane.getStylesheets().add(getClass().getResource(StatePaneConstants.RESOURCE_STYLESHEET_PATH).toExternalForm());
        initProperties(statesResources);

    }

    /**
     * Return the Node containing the graphical view of the turtle state.
     *
     * @return Node containing graphical view of the turtle state.
     */
    public Node getNode() {
        return turtlePane;
    }

    /**
     * Set value of position of turtle.
     *
     * @param position New turtle position.
     */
    @Override
    public void setPosition(double[] position) {
        editableOptions.get(StatePaneConstants.KEY_POSITION).setText(String.format("%.0f,%.0f", position[0], position[1]));
    }

    /**
     * Set value of heading of turtle.
     *
     * @param heading New turtle heading.
     */
    @Override
    public void setHeading(double heading) {
        editableOptions.get(StatePaneConstants.KEY_HEADING).setText(String.format("%.0f", heading));
    }

    /**
     * Set value of pen status of turtle.
     *
     * @param penStatus New turtle pen status.
     */
    @Override
    public void setPenStatus(boolean penStatus) {
        editableOptions.get(StatePaneConstants.KEY_PENUP).setText(Boolean.toString(penStatus));
    }

    /**
     * Set value of pen colour of turtle.
     *
     * @param color New turtle pen colour.
     */
    @Override
    public void setPenColor(Color color) {
        editableOptions.get(StatePaneConstants.KEY_PEN_COLOUR).setText(colorToRGB(color));
        editableOptions.get(StatePaneConstants.KEY_PEN_COLOUR).setStyle("-fx-text-inner-color: " + colorToRGB(color));
    }

    /**
     * Set value of pen thickness of turtle.
     *
     * @param thickness New turtle pen thickness.
     */
    @Override
    public void setPenThickness(double thickness) {
        editableOptions.get(StatePaneConstants.KEY_PEN_THICKNESS).setText(Double.toString(thickness));
    }

    private void initProperties(ResourceBundle statesResources) {
        statesResources.getKeys().asIterator().forEachRemaining(key -> {
            String[] stateProperties = statesResources.getString(key).split(",");
            makeLabel(key, stateProperties[0], Integer.parseInt(stateProperties[1]), stateProperties[2], stateProperties[3]);
        });
    }

    private void makeLabel(String key, String property, int row, String idTag, String updateMethod) {
        Label stateLabel = new javafx.scene.control.Label(property);
        stateLabel.getStyleClass().add(StatePaneConstants.CSS_TAG_LABEL);
        Control valText;
        if (idTag.equals(StatePaneConstants.ID_TAG)) {
            valText = new Label(Integer.toString(id));
        } else {
            TextField newText = new TextField();
            editableOptions.put(key, newText);
            newText.setOnAction(s -> {
                try {
                    this.getClass().getDeclaredMethod(updateMethod, String.class).invoke(this, newText.getText());
                } catch (NoSuchMethodException | IllegalAccessException e) {
                    new Alert(Alert.AlertType.ERROR, errorResource.getString(StatePaneConstants.ERROR_METHOD_INVOCATION));
                } catch (InvocationTargetException e) {
                    observer.displayError(e);
                }
            });
            valText = newText;
        }
        valText.getStyleClass().add(StatePaneConstants.CSS_TAG_VAL);
        turtlePane.add(stateLabel, 0, row);
        turtlePane.add(valText, 1, row);
    }

    private String colorToRGB(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * StatePaneConstants.MAX_COLOUR_VAL),
                (int) (color.getGreen() * StatePaneConstants.MAX_COLOUR_VAL),
                (int) (color.getBlue() * StatePaneConstants.MAX_COLOUR_VAL));
    }

    private void updatePosition(String input) {
        try {
            String[] coords = input.strip().split(",");
            if (coords.length != 2) {
                throw new IllegalInputException(errorResource.getString(StatePaneConstants.ERROR_POSITION));
            }
            int xPos = Integer.parseInt(coords[0]);
            int yPos = Integer.parseInt(coords[1]);
            observer.updatePosition(id, xPos, yPos);
        } catch (Exception e) {
            throw new IllegalInputException(errorResource.getString(StatePaneConstants.ERROR_POSITION), e);
        }
    }

    private void updateHeading(String input) {
        try {
            double angle = Double.parseDouble(input.strip());
            observer.updateHeading(id, angle);
        } catch (Exception e) {
            throw new IllegalInputException(errorResource.getString(StatePaneConstants.ERROR_HEADING), e);
        }
    }

    private void updatePenStatus(String input) {
        try {
            boolean penStatus;
            if (input.strip().equalsIgnoreCase(StatePaneConstants.TRUE)) {
                penStatus = true;
            } else if (input.strip().equalsIgnoreCase(StatePaneConstants.FALSE)) {
                penStatus = false;
            } else {
                throw new IllegalInputException();
            }
            observer.updatePenStatus(id, penStatus);
        } catch (Exception e) {
            throw new IllegalInputException(errorResource.getString(StatePaneConstants.ERROR_PEN_STATUS), e);
        }
    }

    private void updatePenColour(String input) {
        try {
            Color color = Color.web(input.strip());
            observer.updatePenColour(id, color);
        } catch (Exception e) {
            throw new IllegalInputException(errorResource.getString(StatePaneConstants.ERROR_PEN_COLOUR), e);
        }
    }

    private void updatePenThickness(String input) {
        try {
            double thickness = Double.parseDouble(input.strip());
            if (thickness <= 0) {
                throw new IllegalInputException();
            }
            observer.updatePenThickness(id, thickness);
        } catch (Exception e) {
            throw new IllegalInputException(errorResource.getString(StatePaneConstants.ERROR_PEN_THICKNESS), e);
        }
    }
}
