package view.selectorpane;

import javafx.scene.Node;

/**
 * Interface representing a selector pane, with methods to access the pane's node and to add possible
 * panes for selection from the selector pane.
 *
 * @author Goh Khian Wei
 */
public interface SelectorPane {

    /**
     * Returns the Node containing the entire visual display of the selector pane
     *
     * @return Node containing visual display of selector pane
     */
    Node getNode();

    /**
     * Add a pane that can be selected from the selector pane, only the name of the pane is required.
     *
     * @param paneName Name of a pane to be added to the list of panes that can be selected from the selector pane.
     */
    void addPane(String paneName);

}
