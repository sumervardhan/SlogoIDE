package view.turtlearena;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Interface to represent a turtle that is displayed in the GUI.
 * All turtles displayed in the GUI should implement this interface.
 *
 * @author Goh Khian Wei
 */

interface Turtle {

    /**
     * Returns Node containing graphical view of the turtle
     *
     * @return Node containing graphical view of the turtle
     */
    Node getTurtle();

    /**
     * Hides turtle in the graphical view
     */
    void hide();

    /**
     * Shows turtle in the graphical view
     */
    void show();

    /**
     * Moves turtle to the given position
     *
     * @param xPos X coordinate of new location
     * @param yPos Y coordinate of new location
     */
    void move(double xPos, double yPos);

    /**
     * Moves turtle by relative offset from current location
     *
     * @param dx X offset from current x coordinate
     * @param dy Y offset from current y coordinate
     */
    void moveOffset(double dx, double dy);

    /**
     * Moves turtle and leaves a line from old to new position
     *
     * @param xPos X coordinate of new location
     * @param yPos Y coordinate of new location
     * @return Node representing line drawn
     */
    Node addLine(double xPos, double yPos);

    /**
     * Rotate turtle to the new angle given
     *
     * @param angle New heading angle of turtle
     */
    void rotate(double angle);

    /**
     * Sets the turtle image
     *
     * @param image Image turtle should be set to
     */
    void setTurtleImage(Image image);

    /**
     * Sets the pen colour of lines drawn
     *
     * @param color Color of the pen
     */
    void setPenColor(Color color);

    /**
     * Sets the thickness of lines drawn
     *
     * @param thickness Thickness of the pen
     */
    void setPenThickness(double thickness);

}
