package view.turtlearena;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Implementation of Turtle, represents the actual turtle viewed by the user as well as creates the lines drawn by the turtle.
 * Stores graphics information such as pen thickness, pen width and turtle image.
 *
 * @author Goh Khian Wei
 */

public class BasicTurtle implements Turtle {

    private ImageView turtle;
    private Color penColor;
    private double penThickness;
    private Image image;

    /**
     * Constructor, initialises turtle based on default image, pen colour and pen width of turtle
     *
     * @param defaultImage    Image to initialise turtle with
     * @param defaultPenColor Pen colour to initialise turtle with
     * @param defaultPenWidth Pen width to initialise turtle with
     */
    public BasicTurtle(Image defaultImage, Color defaultPenColor, double defaultPenWidth) {
        penThickness = defaultPenWidth;
        penColor = defaultPenColor;
        image = defaultImage;

        initTurtle();
    }

    /**
     * Returns Node containing graphical view of turtle
     *
     * @return Node containing graphical view of turtle
     */
    @Override
    public Node getTurtle() {
        return turtle;
    }

    /**
     * Hides the turtle visually
     */
    @Override
    public void hide() {
        turtle.setVisible(false);
    }

    /**
     * Visually shows the turtle
     */
    @Override
    public void show() {
        turtle.setVisible(true);
    }

    /**
     * Moves turtle to new location absolutely
     *
     * @param xPos X coordinate of new location
     * @param yPos Y coordinate of new location
     */
    @Override
    public void move(double xPos, double yPos) {
        turtle.relocate(xPos - turtle.getBoundsInLocal().getCenterX(), yPos - turtle.getBoundsInLocal().getCenterY());
    }

    /**
     * Moves turtle to new location as specified by offset from current location
     *
     * @param dx X offset from current x coordinate
     * @param dy Y offset from current y coordinate
     */
    @Override
    public void moveOffset(double dx, double dy) {
        turtle.relocate(turtle.getLayoutX() + dx, turtle.getLayoutY() + dy);
    }

    /**
     * Moves turtle to new location and draws line from old to new location
     *
     * @param xPos X coordinate of new location
     * @param yPos Y coordinate of new location
     * @return Node containing graphical view of line drawn by turtle
     */
    @Override
    public Node addLine(double xPos, double yPos) {
        Line line = new Line(turtle.getBoundsInParent().getCenterX(),
                turtle.getBoundsInParent().getCenterY(),
                xPos,
                yPos);
        line.setStroke(penColor);
        line.setStrokeWidth(penThickness);
        turtle.relocate(xPos - turtle.getBoundsInLocal().getCenterX(), yPos - turtle.getBoundsInLocal().getCenterY());
        return line;
    }

    /**
     * Rotates turtle to new angle
     *
     * @param angle New heading angle of turtle
     */
    @Override
    public void rotate(double angle) {
        turtle.setRotate(angle);
    }

    /**
     * Sets turtle to new image
     *
     * @param image Image turtle should be set to
     */
    @Override
    public void setTurtleImage(Image image) {
        turtle.setImage(image);
    }

    /**
     * Sets the pen colour of the turtle, such that subsequent lines are drawn with the new pen colour
     *
     * @param color Color of the pen
     */
    @Override
    public void setPenColor(Color color) {
        penColor = color;

    }

    /**
     * Sets the pen thickness of the turtle, such that subsequent lines are drawn with the new pen thickness
     *
     * @param penThickness Thickness of the pen
     */
    @Override
    public void setPenThickness(double penThickness) {
        this.penThickness = penThickness;
    }

    private void initTurtle() {
        turtle = new ImageView();

        turtle.setFitHeight(ArenaConstants.TURTLE_MAX_DIMENSION);
        turtle.setFitWidth(ArenaConstants.TURTLE_MAX_DIMENSION);
        turtle.setPreserveRatio(true);
        try {
            setTurtleImage(image);
        } catch (Exception e) {
            //Do nothing
        }

    }
}