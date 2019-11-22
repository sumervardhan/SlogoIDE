package view.mainpane;

import javafx.scene.Node;

/**
 * Interface representing a main pane object that contains the basic views required for any slogo simulation.
 *
 * @author Goh Khian Wei
 */
public interface MainPane {

    /**
     * Add an additional view pane into the possible panes that could be displayed.
     *
     * @param pane     Node containing the new pane
     * @param paneName Name of this new pane
     */
    void addPane(Node pane, String paneName);

    /**
     * Returns node containing the entire main pane
     *
     * @return Node containing the entire main pane
     */
    Node getNode();
}
