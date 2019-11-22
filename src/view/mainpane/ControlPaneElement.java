package view.mainpane;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Represents each individual pane element in the control visualiser, containing both the actual interactive pane and
 * a label showing the name of the pane.
 *
 * @author Goh Khian Wei
 */
public class ControlPaneElement {

    private BorderPane controllablePane;

    /**
     * Constructor, creates the pane by setting the actual interactive pane and the pane title.
     *
     * @param pane     Node containing the actual GUI pane
     * @param paneName Title of the pane
     */
    public ControlPaneElement(Node pane, String paneName) {
        initControllablePane();
        Label name = initName(paneName);
        controllablePane.setTop(name);
        controllablePane.setCenter(pane);
    }

    /**
     * Returns the Node representing this control pane element.
     *
     * @return Node representing the control pane element.
     */
    public Node getControllablePane() {
        return controllablePane;
    }


    private void initControllablePane() {
        controllablePane = new BorderPane();
        controllablePane.getStylesheets().add(getClass().getResource(MainPaneConstants.CSS_FILE).toExternalForm());
        controllablePane.getStyleClass().add(MainPaneConstants.CSS_TAG_MAIN_PANE);
    }

    private Label initName(String paneName) {
        Label name = new Label(paneName);
        name.getStyleClass().add(MainPaneConstants.CSS_TAG_NAME);
        return name;
    }
}
