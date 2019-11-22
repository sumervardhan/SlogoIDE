package view.mainpane;

import javafx.scene.Node;

/**
 * A container used to contain and set necessary properties of a pane in the main pane.
 *
 * @author Goh Khian Wei
 */
public class PaneProperties {
    private String paneName;
    private Node pane;
    private boolean isDisplayed;

    /**
     * Instantiates an instance, setting default values of the private variables of the class.
     * Assumes that pane is not displayed at point of creation of this PaneProperties class.
     *
     * @param pane     Node containing the entire visual pane.
     * @param paneName name of the pane.
     */
    public PaneProperties(Node pane, String paneName) {
        this.pane = pane;
        this.paneName = paneName;
        isDisplayed = false;
    }

    /**
     * Returns the pane name.
     * Can return the original string as string is immutable.
     *
     * @return pane name.
     */
    public String getPaneName() {
        return paneName;
    }

    /**
     * Returns the node containing the pane.
     * Purposely returns the actual node stored within the class, as any class that has access to this object is assumed to be
     * authorised to modify the object's contents.
     *
     * @return Node containing the pane.
     */
    public Node getPane() {
        return pane;
    }

    /**
     * Returns boolean value of whether the pane a class object represents is being displayed.
     *
     * @return boolean value of whether pane is displayed.
     */
    public boolean isDisplayed() {
        return isDisplayed;
    }

    /**
     * Sets the value of whether pane is displayed.
     *
     * @param value whether pane is displayed.
     */
    public void setDisplayed(boolean value) {
        isDisplayed = value;
    }
}
