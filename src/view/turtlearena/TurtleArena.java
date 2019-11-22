package view.turtlearena;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Interface for a turtle arena. Any turtle arena should implement this interface.
 * Has access to the field on which the turtles exist and the turtles themselves.
 *
 * @author Goh Khian Wei
 */
public interface TurtleArena {

    /**
     * Returns Node containing graphical view of turtle arena.
     *
     * @return Node containing graphical view of turtle arena.
     */
    Node getTurtleArena();

    /**
     * Changes background colour of the turtle arena
     *
     * @param color Colour to be changed to
     */
    void changeBackgroundColor(Color color);

    /**
     * Changes the visual image of a particular turtle identified by its index
     *
     * @param turtleIndex Index of turtle to be affected
     * @param image       New image turtle should display
     */
    void changeTurtleImage(int turtleIndex, Image image);

    /**
     * Changes the colour of subsequent lines left by turtle identified by its index.
     *
     * @param turtleIndex Index of turtle to be affected
     * @param color       New colour of lines drawn by turtle
     */
    void changePenColor(int turtleIndex, Color color);

    /**
     * Changes the thickness of subsequent lines left by turtle identified by its index.
     *
     * @param turtleIndex Index of turtle to be affected
     * @param thickness   Thickness of lines drawn by turtle
     */
    void changePenThickness(int turtleIndex, double thickness);

    /**
     * Adds a new turtle of specified index to the arena
     *
     * @param turtleIndex Index of new turtle
     */
    void addTurtle(int turtleIndex);

    /**
     * Hides visual display of turtle identified by its index.
     *
     * @param turtleIndex Index of turtle to be hidden.
     */
    void hideTurtle(int turtleIndex);

    /**
     * Visually shows turtle identified by its index.
     *
     * @param turtleIndex Index of turtle to be shown.
     */
    void showTurtle(int turtleIndex);

    /**
     * Moves turtle of specified index and draws line from old position to new position
     *
     * @param turtleIndex Index of turtle to be moved
     * @param xPos        X coordinate of new position.
     * @param yPos        Y coordinate of new position.
     */
    void addLine(int turtleIndex, double xPos, double yPos);

    /**
     * Moves turtle of specified index from old position to new position
     *
     * @param turtleIndex Index of turtle to be moved
     * @param xPos        X coordinate of new position.
     * @param yPos        Y coordinate of new position.
     */
    void moveTurtle(int turtleIndex, double xPos, double yPos);

    /**
     * Rotates turtle of specified index to given angle
     *
     * @param turtleIndex Index of turtle to be rotated
     * @param angle       Angle turtle should be rotated to
     */
    void rotateTurtle(int turtleIndex, double angle);

    /**
     * Sets the default image all subsequent new turtles should have
     *
     * @param image Default image
     */
    void setDefaultImage(Image image);

    /**
     * Sets the default pen colour all subsequent new turtles should have
     *
     * @param color Default pen colour
     */
    void setDefaultPenColor(Color color);

    /**
     * Sets the default pen width all subsequent new turtles should have
     *
     * @param width Default pen width
     */
    void setDefaultPenWidth(double width);

    /**
     * Resets the turtle arena, removing all lines drawn and recentering all turtles.
     */
    void clearScreen();
}
