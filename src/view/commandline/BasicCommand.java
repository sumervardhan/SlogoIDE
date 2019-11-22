package view.commandline;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import view.CmdObserver;
import view.selector.Feature;
import view.selector.features.FileSelector;

import java.util.ResourceBundle;

/**
 * An implementation of the CommandLine interface, where users can input commands and send it to the program for execution, or load/ save commands.
 * Data driven programming, where buttons and their handler methods are determined from a properties file.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 */
public class BasicCommand implements Feature {
    private static final String FILE_DESCRIPTION = "SLogo Files (*.logo)";
    private static final String FILE_EXTENSION = "*.logo";

    private ResourceBundle myResources;

    private BorderPane mainCmdPane;
    private TextArea textArea;
    private VBox buttonBox;
    private FileSelector fileSelector;

    private CmdObserver view;

    /**
     * Constructor, takes in a CmdObserver object (in a simplified version of the observer design pattern, as final implementation only ever requires one observer).
     * Initialises the command line pane.
     *
     * @param view CmdObserver that gets updated every time the user sends a command.
     */
    public BasicCommand(CmdObserver view) {
        this.view = view;
        myResources = ResourceBundle.getBundle(CmdLineConstants.RESOURCE_PATH);

        initMainCmdPane();
        initTextArea();
        initButtons();

        mainCmdPane.setCenter(textArea);
        mainCmdPane.setRight(buttonBox);

        fileSelector = new FileSelector(FILE_DESCRIPTION, FILE_EXTENSION);
    }

    /**
     * Returns the javafx Node containing the entire pane.
     *
     * @return javafx Node containing entire pane.
     */
    @Override
    public Node getNode() {
        return mainCmdPane;
    }

    /**
     * Do nothing for update
     *
     * @param args Any number of objects
     */
    @Override
    public void update(Object... args) {
        // Do nothing
    }

    private void initMainCmdPane() {
        mainCmdPane = new BorderPane();
        mainCmdPane.getStylesheets().add(getClass().getResource(CmdLineConstants.CMD_CSS_FILE).toExternalForm());
        mainCmdPane.getStyleClass().add(CmdLineConstants.CSS_TAG_CMD_MAIN);
    }

    private void initTextArea() {
        textArea = new TextArea();
        textArea.getStyleClass().add(CmdLineConstants.CSS_TAG_CMD_TEXT_AREA);
    }

    private void initButtons() {
        buttonBox = new VBox();
        buttonBox.getStyleClass().add(CmdLineConstants.CSS_TAG_CMD_BUTTON_AREA);
        myResources.getKeys().asIterator().forEachRemaining(key -> {
            buttonBox.getChildren().add(makeButton(key));
        });
    }

    private Button makeButton(String property) {
        String[] buttonInfo = myResources.getString(property).split(",");
        Button result = new Button(buttonInfo[0]);
        result.getStyleClass().add(CmdLineConstants.CSS_TAG_CMD_BUTTON);
        result.setOnAction(s -> {
            try {
                this.getClass().getDeclaredMethod(buttonInfo[1]).invoke(this);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, CmdLineConstants.ERROR_BUTTON_HANDLER).showAndWait();
            }
        });
        return result;
    }

    private void runAction() {
        String text = textArea.getText();
        textArea.clear();
        view.updateCmd(text);
    }

    private void clearAction() {
        textArea.clear();
    }

    private void readFile() {
        textArea.clear();
        textArea.setText(fileSelector.getFileAsString());
    }

    private void saveFile() {
        fileSelector.saveStringAsFile(textArea.getText());
    }

}
