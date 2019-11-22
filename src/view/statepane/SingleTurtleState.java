package view.statepane;

import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Represents a object which stores the state of a single turtle, with values able to be dynamically modified.
 *
 * @author Goh Khian Wei
 */
public interface SingleTurtleState {

    /**
     * Returns the Node containing the graphical view of the turtle state.
     *
     * @return Node containing graphical view of the turtle state.
     */
    Node getNode();

    /**
     * Set value of position of turtle.
     *
     * @param position New turtle position.
     */
    void setPosition(double[] position);

    /**
     * Set value of heading of turtle.
     *
     * @param heading New turtle heading.
     */
    void setHeading(double heading);

    /**
     * Set value of pen status of turtle.
     *
     * @param penStatus New turtle pen status.
     */
    void setPenStatus(boolean penStatus);

    /**
     * Set value of pen colour of turtle.
     *
     * @param color New turtle pen colour.
     */
    void setPenColor(Color color);

    /**
     * Set value of pen thickness of turtle.
     *
     * @param thickness New turtle pen thickness.
     */
    void setPenThickness(double thickness);

}
