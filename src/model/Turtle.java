package model;

import utilities.Coordinate;

public class Turtle {

    public static final int DEFAULT_PEN_COLOR = 0;
    private double xCor;
    private double yCor;
    private double heading;
    private int isPenUp;
    private int isShowing;
    private int penColor;
    private int penSize;
    private int shape;

    public Turtle() {
        xCor = 0;
        yCor = 0;
        heading = 0;
        isPenUp = 0;
        isShowing = 1;
        penColor = DEFAULT_PEN_COLOR;
    }

    public Coordinate getCoordinates() {
        return new Coordinate(xCor, yCor);
    }

    public void setCoordinates(Coordinate coordinate) {
        xCor = coordinate.getX();
        yCor = coordinate.getY();
    }

    public int getIsShowing() {
        return isShowing;
    }

    public void setIsShowing(int toShow) {
        isShowing = toShow;
    }

    public double getHeading() {
        return heading;
    }

    public void setHeading(double heading) {
        this.heading = heading % 360;
    }

    public int isPenUp() {
        return isPenUp;
    }

    public void setPenUp(int penUp) {
        isPenUp = penUp;
    }

    public int getPenColor() {
        return penColor;
    }

    public void setPenColor(int penColor) {
        this.penColor = penColor;
    }

    public int getPenSize() {
        return penSize;
    }

    public void setPenSize(int size) {
        penSize = size;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }
}
