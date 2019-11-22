package view;

import utilities.Coordinate;

/**
 * This view is concerned with methods that change what the user sees on the screen.
 * Any form of movement that might take place via a call from another class.
 *
 * @author Ben Lawrence
 * @author Khian Wei Goh
 * @author Joshua Medway
 * @author Sumer Vardhan
 */
public interface MovementView {

    /**
     * Tells the view to add al line for the turtle with the given index starting and ending at the given coordinates
     *
     * @param index - integer turtle index whose movement has drawn the line
     * @param start - coordinate start value in slogo coordinate system
     * @param end - coordinate end value in slogo coordinate system
     */
    void addLine(int index, Coordinate start, Coordinate end);

    /**
     * Tells the view to move the turtle with the given index from the start coordinate to the end coordinate
     *
     * @param turtleIndex - integer index of turtle to move
     * @param start - coordinate from which the turtle starts in slogo coordinate system
     * @param end - coordinate from which the turtle ends
     */
    void moveTurtle(int turtleIndex, Coordinate start, Coordinate end);

    /**
     * Tells the view to rotate a turtle with the given index
     *
     * @param turtleIndex - integer index of turtle to be rotated
     * @param degrees - double number of degrees to rotate the turtle clockwise
     */
    void rotateTurtle(int turtleIndex, double degrees);

}
