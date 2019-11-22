package view.statepane;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import view.StateObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Implements StatePane, contains BasicSingleTurtleState objects containing the state property of each turtle.
 * Each BasicSingleTurtleState object corresponds to a different turtle.
 * Creates the graphical view to be displayed as the state pane as well.
 * Uses a simplified version of the observer design pattern to inform observers of changes to its state.
 * Simplified as since only one observer is required, methods to attach and detach observers are not required.
 *
 * @author Goh Khian Wei
 */
public class BasicStatePane implements StatePane {
    private Color defaultPenColor;
    private double defaultPenThickness;
    private ScrollPane scrollPane;
    private GridPane mainPane;
    private ResourceBundle statesResources;
    private Map<Integer, SingleTurtleState> turtleStatePanes;
    private StateObserver observer;

    /**
     * Constructor, creates an empty state pane ready to add turtle states to.
     * Stores default pen color and default pen width of turtles, as well as the observer.
     *
     * @param observer        StateObserver to be updated if state changes
     * @param defaultPenColor Default pen color of new turtles
     * @param defaultPenWidth Default pen width of new turtles
     */
    public BasicStatePane(StateObserver observer, Color defaultPenColor, double defaultPenWidth) {
        this.defaultPenColor = defaultPenColor;
        this.defaultPenThickness = defaultPenWidth;
        this.observer = observer;

        scrollPane = new ScrollPane();
        mainPane = new GridPane();
        scrollPane.setContent(mainPane);

        turtleStatePanes = new HashMap<>();

        mainPane.getStylesheets().add(getClass().getResource(StatePaneConstants.STATE_PANE_STYLESHEET).toExternalForm());
        statesResources = ResourceBundle.getBundle(StatePaneConstants.STATE_RESOURCES);
    }

    /**
     * Returns the node containing the graphical view of the pane.
     *
     * @return Node containing graphical view of pane.
     */
    @Override
    public Node getNode() {
        return scrollPane;
    }

    /**
     * Allows an additional turtle to be added, with initial state values determined using the default value stored in this object,
     * Creates a new BasicSingleTurtleState object.
     *
     * @param turtleIndex index of new turtle to be added
     */
    @Override
    public void addTurtle(int turtleIndex) {
        SingleTurtleState newTurtleState = new BasicSingleTurtleState(statesResources, turtleIndex, observer);
        initTurtleState(newTurtleState);
        turtleStatePanes.put(turtleIndex, newTurtleState);
        mainPane.add(newTurtleState.getNode(), 0, turtleIndex);
    }

    /**
     * Updates value of turtle's position stored in the state pane.
     *
     * @param turtleIndex Index of turtle whose position is to be changed
     * @param position    New position of turtle
     */
    @Override
    public void updateTurtlePosition(int turtleIndex, double[] position) {
        turtleStatePanes.get(turtleIndex).setPosition(position);
    }

    /**
     * Updates value of turtle's heading stored in the state pane.
     *
     * @param turtleIndex Index of turtle whose position is to be changed.
     * @param heading     New heading of turtle.
     */
    @Override
    public void updateTurtleHeading(int turtleIndex, double heading) {
        turtleStatePanes.get(turtleIndex).setHeading(heading);
    }

    /**
     * Updates value of turtle's pen status stored in the state pane.
     *
     * @param turtleIndex Index of turtle whose pen status is to be changed.
     * @param penStatus   New pen status of turtle.
     */
    @Override
    public void updatePenUp(int turtleIndex, boolean penStatus) {
        turtleStatePanes.get(turtleIndex).setPenStatus(penStatus);
    }

    /**
     * Updates value of turtle's pen colour stored in the state pane.
     *
     * @param turtleIndex Index of turtle whose pen colour is to be changed.
     * @param penColor    New pen colour of turtle.
     */
    @Override
    public void updatePenColor(int turtleIndex, Color penColor) {
        turtleStatePanes.get(turtleIndex).setPenColor(penColor);
    }

    /**
     * Updates value of turtle's pen thickness stored in the state pane.
     *
     * @param turtleIndex  Index of turtle whose pen thickness is to be changed.
     * @param penThickness New pen thickness of turtle.
     */
    @Override
    public void updatePenThickness(int turtleIndex, double penThickness) {
        turtleStatePanes.get(turtleIndex).setPenThickness(penThickness);
    }

    /**
     * Sets value of pen width to be used when initialising new SingleTurtleState objects.
     *
     * @param width Default pen width
     */
    @Override
    public void setDefaultPenWidth(double width) {
        this.defaultPenThickness = width;
    }

    /**
     * Sets value of pen colour to be used when initialising new SingleTurtleState objects.
     *
     * @param color Default pen colour
     */
    @Override
    public void setDefaultPenColor(Color color) {
        this.defaultPenColor = color;
    }

    private void initTurtleState(SingleTurtleState newTurtleState) {
        newTurtleState.setHeading(0);
        newTurtleState.setPenColor(defaultPenColor);
        newTurtleState.setPenStatus(false);
        newTurtleState.setPenThickness(defaultPenThickness);
        newTurtleState.setPosition(new double[]{0, 0});
    }

}
