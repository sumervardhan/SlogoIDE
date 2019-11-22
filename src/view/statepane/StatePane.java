package view.statepane;

import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Interface that classes able to display each turtle's individual state dynamically implements.
 * Only method that returns a value is the method that returns the Node created that contains the graphical view of the state pane.
 *
 * @author Goh Khian Wei
 */
public interface StatePane {

    /**
     * Returns the Node containing the graphical view of the state pane
     *
     * @return Node containing the graphical view of the state pane
     */
    Node getNode();

    /**
     * Add a turtle with a given index to the state pane for visualisation of properties
     *
     * @param turtleIndex Index of turtle to be added
     */
    void addTurtle(int turtleIndex);

    /**
     * Updates position value of turtle with given index.
     *
     * @param turtleIndex Index of turtle whose position is to be updated.
     * @param position    New position of turtle.
     */
    void updateTurtlePosition(int turtleIndex, double[] position);

    /**
     * Updates heading value of turtle with given index.
     *
     * @param turtleIndex Index of turtle whose heading is to be updated.
     * @param heading     New heading of turtle.
     */
    void updateTurtleHeading(int turtleIndex, double heading);

    /**
     * Updates pen status of turtle with given index.
     *
     * @param turtleIndex Index of turtle whose pen status is to be updated.
     * @param penStatus   New pen status of turtle.
     */
    void updatePenUp(int turtleIndex, boolean penStatus);

    /**
     * Updates pen colour of turtle with given index.
     *
     * @param turtleIndex Index of turtle whose pen colour is to be updated.
     * @param penColor    New pen colour of turtle.
     */
    void updatePenColor(int turtleIndex, Color penColor);

    /**
     * Updates pen thickness of turtle with given index.
     *
     * @param turtleIndex  Index of turtle whose pen thickness is to be updated.
     * @param penThickness New pen thickness of turtle.
     */
    void updatePenThickness(int turtleIndex, double penThickness);

    /**
     * Sets the default pen colour to be used when initialising values for new turtles.
     *
     * @param color Default pen colour.
     */
    void setDefaultPenColor(Color color);

    /**
     * Sets the default pen width to be used when initialising values for new turtles.
     *
     * @param width Default pen width.
     */
    void setDefaultPenWidth(double width);
}
