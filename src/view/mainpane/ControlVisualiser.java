package view.mainpane;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utilities.GridPositionComparator;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Control Visualiser class containing the stage that contains all possible additional panes that user can interact with.
 * Allows for panes to be dynamically added and removed from the stage, with dynamic resizing and positioning of panes whenever
 * number of panes changes.
 * Is not responsible for actually displaying the stage, just setting up the scene graph and elements within the stage.
 *
 * @author Goh Khian Wei
 */
public class ControlVisualiser {

    private GridPositionComparator pointComparator;
    private Stage controlPaneStage;
    private GridPane controlPane;
    private Point nextNodeLocation;

    /**
     * Constructor, creates a new stage and sets the scene and title.
     */
    public ControlVisualiser() {
        pointComparator = GridPositionComparator.getInstance();
        controlPaneStage = new Stage();
        controlPane = new GridPane();
        nextNodeLocation = new Point(0, 0);

        controlPaneStage.setScene(new Scene(controlPane));
        controlPaneStage.setTitle(MainPaneConstants.STAGE_TITLE);
    }

    /**
     * Adds a pane to the scene graph and shows the pane name.
     * The pane is added to the end of the grid, moving to a new row where necessary.
     * The number of columns in the grid is fixed by MAX_COLUMN_INDEX.
     * If a new row is created, the entire stage is sized accordingly to display the new row.
     *
     * @param pane     Node containing pane to be added to the scene graph.
     * @param paneName Name of the pane.
     */
    public void showPane(Node pane, String paneName) {
        ControlPaneElement controlPaneElement = new ControlPaneElement(pane, paneName);
        controlPane.add(controlPaneElement.getControllablePane(), (int) nextNodeLocation.getX(), (int) nextNodeLocation.getY());
        if (nextNodeLocation.getX() >= MainPaneConstants.MAX_COLUMN_INDEX) {
            nextNodeLocation.setLocation(0, (int) nextNodeLocation.getY() + 1);
        } else {
            nextNodeLocation.setLocation(nextNodeLocation.getX() + 1, nextNodeLocation.getY());
        }
        controlPaneStage.sizeToScene();
    }

    /**
     * Removes a pane from the grid.
     * If this causes a row to be empty, the entire stage is resized such that the empty row does not have to be displayed.
     * If a pane removed is in the middle of the grid, then the entire grid is shifted to fill the empty space vacated.
     *
     * @param pane The node to be hidden.
     */
    public void hidePane(Node pane) {
        Point removedLocation = new Point(GridPane.getColumnIndex(pane.getParent()), GridPane.getRowIndex(pane.getParent()));
        controlPane.getChildren().remove(pane.getParent());
        Point maxNodeLocation = new Point(removedLocation);

        //Copy to another list to prevent concurrent operation errors
        List<Node> nodes = new ArrayList<>(controlPane.getChildren());
        for (Node node : nodes) {

            Point nodeLocation = new Point(GridPane.getColumnIndex(node), GridPane.getRowIndex(node));
            if (pointComparator.compare(nodeLocation, removedLocation) > 0) {
                switchNodeLocation(node, nodeLocation);

                if (pointComparator.compare(nodeLocation, maxNodeLocation) > 0) {
                    maxNodeLocation = nodeLocation;
                }
            }
        }
        nextNodeLocation = maxNodeLocation;

        if (controlPane.getChildren().isEmpty()) {
            controlPaneStage.close();
        }
        controlPaneStage.sizeToScene();
    }

    /**
     * Visually display the stage.
     */
    public void showStage() {
        controlPaneStage.show();
    }

    private void switchNodeLocation(Node node, Point nodeLocation) {
        controlPane.getChildren().remove(node);
        if (nodeLocation.getX() == 0) {
            controlPane.add(node, MainPaneConstants.MAX_COLUMN_INDEX, (int) nodeLocation.getY() - 1);
        } else {
            controlPane.add(node, (int) nodeLocation.getX() - 1, (int) nodeLocation.getY());
        }
    }

}
