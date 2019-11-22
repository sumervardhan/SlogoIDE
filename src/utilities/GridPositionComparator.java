package utilities;

import java.awt.*;
import java.util.Comparator;

/**
 * Comparator to compare Points which represent grid position in the selector pane.
 * Uses singleton design pattern as only one instance of class is ever required.
 * Only useful for comparing Points which represent grid position in selector pane, would not make sense in any other instance.
 *
 * @author Goh Khian Wei
 */
public class GridPositionComparator implements Comparator<Point> {

    private static GridPositionComparator comparator;

    private GridPositionComparator() {
        //Do nothing
    }

    /**
     * Ensures that only one instance of GridPositionComparator is ever created.
     *
     * @return instance of GridPositionComparator
     */
    public static GridPositionComparator getInstance() {
        if (comparator == null) {
            comparator = new GridPositionComparator();
        }
        return comparator;
    }

    /**
     * Comparison method to compare 2 points representing grid positions.
     *
     * @param o1 Point 1
     * @param o2 Point 2
     * @return int corresponding to relative value of both points.
     */
    @Override
    public int compare(Point o1, Point o2) {
        if (o1.getY() == o2.getY()) {
            return Double.compare(o1.getX(), o2.getX());
        } else {
            return Double.compare(o1.getY(), o2.getY());
        }
    }
}
