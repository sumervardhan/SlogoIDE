package utilities;

/**
 * is a simple coordinate class which keeps track of the row and columns for use in the hashMap of the grid
 */
public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * the hashCode used to compare different rows and columns of other coordinates
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Double.hashCode(x) + Double.hashCode(y);
    }

    /**
     * returns true if the row and columns are the exact same value
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        Coordinate cord = (Coordinate) obj;

        return x == cord.getX() && y == cord.getY();
    }

    /**
     * simple toString to represent a coordinate
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("Utilities.Coordinate{x: %s, y: %s}", x, y);
    }
}
