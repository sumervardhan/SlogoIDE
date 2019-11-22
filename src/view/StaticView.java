package view;

/**
 * External front-end interface containing methods to invoke static changes to the front-end.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 * @author Joshua Medway
 */
public interface StaticView {

    /**
     * Method to change the background colour of the turtle arena
     *
     * @param colorIndex Index corresponding to a colour in the palette
     */
    void changeBackgroundColor(int colorIndex);

    /**
     * Method to change the pen colour of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param colorIndex  Index corresponding to a colour in the palette
     */
    void changePenColor(int turtleIndex, int colorIndex);

    /**
     * Method to change the image of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param shapeIndex  Index corresponding to a image in the image repository
     */
    void changeTurtleImage(int turtleIndex, int shapeIndex);

    /**
     * Method to hide a specific turtle from view
     *
     * @param turtleIndex Index of turtle
     */
    void hideTurtle(int turtleIndex);

    /**
     * Method to show a specific turtle
     *
     * @param turtleIndex Index of turtle
     */
    void showTurtle(int turtleIndex);

    /**
     * Method to add a turtle with specified index to the front-end view
     *
     * @param turtleIndex Index of turtle
     */
    void addTurtle(int turtleIndex);

    /**
     * Method to set the pen size of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param pixels      Width of the pen
     */
    void setPenSize(int turtleIndex, double pixels);

    /**
     * Method to set the colour corresponding to a specific index of the palette
     *
     * @param index Index
     * @param red   0-255 integer representation of red component
     * @param green 0-255 integer representation of green component
     * @param blue  0-255 integer representation of blue component
     */
    void setPaletteIndex(int index, int red, int green, int blue);

    /**
     * Method to clear and reset the turtle arena
     */
    void clearScreen();

    /**
     * Method to update the pen status of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param penState    New pen status
     */
    void updatePenUp(int turtleIndex, boolean penState);

}
