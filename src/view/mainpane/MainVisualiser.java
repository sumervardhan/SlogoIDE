package view.mainpane;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import view.selectorpane.BasicSelectorPane;
import view.selectorpane.SelectorPane;

import java.util.HashSet;
import java.util.Set;

/**
 * Main Visualiser class storing the turtle pane and the selector pane. Dynamically resizeable.
 * Responsible for handling creation of additional panes if user interacts with selector pane.
 * Implements MainPane and SelectorPaneObserver separately as a way of increasing encapsulation, as instances of this class
 * can be passed to other objects as a specific interface, ensuring that only specific methods are accessible to other classes.
 * <p>
 * MainPane contains methods for updating number of possible panes and getting the Node of the main pane.
 * SelectorPaneObserver contains methods that should be invoked when the selector pane's state is updated.
 *
 * @author Goh Khian Wei
 */
public class MainVisualiser implements MainPane, SelectorPaneObserver {

    private BorderPane mainPane;
    private SelectorPane selectorPane;
    private Set<PaneProperties> availablePanes;
    private ControlVisualiser controlVisualiser;

    /**
     * Instantiates a MainVisualiser, places turtle arena node within the graphical view.
     * Instantiates a ControlVisualiser which will manage and display additional views.
     *
     * @param turtleArena Node containing the turtleArena of the simulation.
     */
    public MainVisualiser(Node turtleArena) {

        SelectorPaneObserver observer = this;
        selectorPane = new BasicSelectorPane(observer);
        controlVisualiser = new ControlVisualiser();
        availablePanes = new HashSet<>();
        mainPane = new BorderPane();

        mainPane.setLeft(selectorPane.getNode());
        mainPane.setCenter(turtleArena);

    }

    /**
     * Method from the SelectorPaneObserver interface.
     * Method that receives updates whenever a pane is selected to be hidden.
     * Updates the control pane and does the necessary handling.
     *
     * @param paneName name of pane that has been hidden
     */
    @Override
    public void updateHidePane(String paneName) {
        availablePanes.forEach(pane -> {
            if (pane.getPaneName().equals(paneName) && pane.isDisplayed()) {
                controlVisualiser.hidePane(pane.getPane());
                pane.setDisplayed(false);
            }
        });
    }

    /**
     * Method from the SelectorPaneObserver interface.
     * Method that receives updates whenever a pane is selected to be shown.
     * Updates the control pane and does the necessary handling.
     *
     * @param paneName name of pane that has been shown
     */
    @Override
    public void updateShowPane(String paneName) {
        controlVisualiser.showStage();
        availablePanes.forEach(pane -> {
            if (pane.getPaneName().equals(paneName) && !pane.isDisplayed()) {
                controlVisualiser.showPane(pane.getPane(), pane.getPaneName());
                pane.setDisplayed(true);
            }
        });
    }

    /**
     * Method from the MainPane interface.
     * Accepts a possible additional view pane that could be subsequently displayed.
     * Adds new pane to the selector pane for display as a possible option.
     *
     * @param pane     Node containing the new pane
     * @param paneName Name of this new pane
     */
    @Override
    public void addPane(Node pane, String paneName) {
        availablePanes.add(new PaneProperties(pane, paneName));
        selectorPane.addPane(paneName);
    }

    /**
     * Method from the MainPane interface.
     * Returns the node containing the entire main pane for display in the scene graph.
     *
     * @return node containing entire main pane.
     */
    @Override
    public Node getNode() {
        return mainPane;
    }


}
