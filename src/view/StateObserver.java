package view;

import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;

/**
 * Part of a simplified observer design pattern.
 * Represents objects which are observers of the state pane, which should execute different commands whenever different state fields are modified.
 *
 * @author Goh Khian Wei
 */
public interface StateObserver {

    /**
     * Method to be executed whenever a specific turtle's position is updated.
     *
     * @param turtleIndex Index of turtle
     * @param xPos        X coordinate of new position
     * @param yPos        Y coordinate of new position
     */
    void updatePosition(int turtleIndex, int xPos, int yPos);

    /**
     * Method to be executed whenever a specific turtle's heading is updated.
     *
     * @param turtleIndex Index of turtle
     * @param angle       Angle of new heading
     */
    void updateHeading(int turtleIndex, double angle);

    /**
     * Method to be executed whenever a specific turtle's pen status is updated.
     *
     * @param turtleIndex Index of turtle
     * @param penStatus   New pen status
     */
    void updatePenStatus(int turtleIndex, boolean penStatus);

    /**
     * Method to be executed whenever a specific turtle's pen colour is updated.
     *
     * @param turtleIndex Index of turtle
     * @param color       New pen colour
     */
    void updatePenColour(int turtleIndex, Color color);

    /**
     * Method to be executed whenever a specific turtle's pen thickness is updated.
     *
     * @param turtleIndex Index of turtle
     * @param thickness   New pen thickness
     */
    void updatePenThickness(int turtleIndex, double thickness);

    /**
     * Method to be executed whenever an error in the state pane updating occurs.
     *
     * @param e Caught exception
     */
    void displayError(InvocationTargetException e);
}
