package view.selectorpane;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import view.mainpane.SelectorPaneObserver;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

/**
 * Implements SelectorPane, creates a pane that stores possible additional panes that users can show and hide.
 * Uses a simplified version of the observer design pattern (where there is only one observer and so attaching and detaching of observers is not required),
 * updates these observers whenever the user selects a pane from the selector pane to be shown or hidden.
 *
 * @author Goh Khian Wei
 */
public class BasicSelectorPane implements SelectorPane {
    private static final String CSS_FILE = "/resources/stylesheets/selectorPaneStylesheet.css";
    private static final String CSS_TAG_MAIN_PANE = "selector-pane";
    private static final String CSS_TAG_LABEL = "my-label";
    private static final String CSS_TAG_BUTTON = "my-button";
    private static final String RESOURCE_BUNDLE_LOCATION = "view/selectorpane/resources/";
    private static final String DEFAULT_RESOURCE_FILE = "buttons";
    private static final String ERROR_RESOURCE_FILE = "errors";
    private static final String ERROR_UNABLE_TO_INVOKE_METHOD = "errorInvokeMethod";
    private static final String ERROR_UNABLE_TO_RETRIEVE_METHOD = "errorRetrieveMethod";

    ResourceBundle buttonResources;
    ResourceBundle errorResources;
    Pane selectorPane;
    GridPane gridPane;
    int rowCount;
    SelectorPaneObserver myObserver;

    /**
     * Constructor, stores the observer which has to know whenever the state of the selector pane changes
     * (i.e. whenever a pane is chosen to hidden or shown)
     * Visual design of selector pane set using css files.
     *
     * @param observer SelectorPaneObserver which has to know when state of selector pane is changed.
     */
    public BasicSelectorPane(SelectorPaneObserver observer) {
        buttonResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION + DEFAULT_RESOURCE_FILE);
        errorResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION + ERROR_RESOURCE_FILE);
        myObserver = observer;
        rowCount = 0;
        gridPane = new GridPane();

        selectorPane = new Pane();
        selectorPane.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        selectorPane.getStyleClass().add(CSS_TAG_MAIN_PANE);
        selectorPane.getChildren().add(gridPane);
    }

    /**
     * Returns the node containing the visual display of the selector pane.
     *
     * @return Node containing the visual display of the selector pane.
     */
    @Override
    public Node getNode() {
        return selectorPane;
    }

    /**
     * Initialises an element in the selection pane representing a new pane that can be shown or hidden.
     * Buttons present in pane are completely initialised through a resource file (buttons.properties), additional buttons can be added through adding the
     * necessary methods and modifying the resource file.
     *
     * @param paneName Name of a pane to be added to the list of panes that can be selected from the selector pane.
     */
    @Override
    public void addPane(String paneName) {
        BorderPane selectorElement = new BorderPane();
        Label name = new Label(paneName);
        name.getStyleClass().add(CSS_TAG_LABEL);
        HBox buttonBox = new HBox();
        buttonResources.getKeys().asIterator().forEachRemaining(key -> {
            try {
                String[] buttonInfo = buttonResources.getString(key).split(",");
                Method m = myObserver.getClass().getDeclaredMethod(buttonInfo[1], String.class);
                Button newButton = makeButton(buttonInfo[0], e -> {
                    try {
                        m.invoke(myObserver, paneName);
                    } catch (Exception exception) {
                        new Alert(Alert.AlertType.ERROR, errorResources.getString(ERROR_UNABLE_TO_INVOKE_METHOD)).showAndWait();
                    }
                });
                buttonBox.getChildren().add(newButton);
            } catch (Exception exception) {
                new Alert(Alert.AlertType.ERROR, errorResources.getString(ERROR_UNABLE_TO_RETRIEVE_METHOD)).showAndWait();
            }
        });
        selectorElement.setLeft(name);
        selectorElement.setRight(buttonBox);
        gridPane.add(selectorElement, 0, rowCount);
        rowCount++;
    }

    private Button makeButton(String buttonLabel, EventHandler handler) {
        Button newButton = new Button(buttonLabel);
        newButton.setOnAction(handler);
        newButton.getStyleClass().add(CSS_TAG_BUTTON);
        return newButton;
    }
}