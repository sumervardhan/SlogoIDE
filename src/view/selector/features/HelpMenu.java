package view.selector.features;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import view.FeatureReceiver;
import view.selector.Feature;

import java.io.File;
import java.nio.file.Files;

/**
 * Help menu pane that contains descriptions of all available commands.
 *
 * @author Ben Lawrence
 * @author Goh Khian Wei
 */
public class HelpMenu implements Feature {
    private static final String REFERENCE_FILE_PATH = "/src/view/selector/features/resources/reference";
    private static final String COMMAND_DELINEATION = "================";
    private static final String NEW_LINE = ": \n";
    private static final String USER_DIR = "user.dir";

    private ScrollPane scrollPane;
    private GridPane pane;

    /**
     * Constructor, data driven population of the help pane by iterating through every file in a folder.
     *
     * @param view Unused.
     */
    public HelpMenu(FeatureReceiver view) {
        scrollPane = new ScrollPane();
        pane = new GridPane();

        File dir = new File(System.getProperty(USER_DIR) + REFERENCE_FILE_PATH);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    Text newCmd = new Text(child.getName().toUpperCase() + NEW_LINE + Files.readString(child.toPath()) + COMMAND_DELINEATION);
                    pane.add(newCmd, 0, pane.getRowCount());
                } catch (Exception e) {
                    //do nothing
                }
            }
        }
        scrollPane.setContent(pane);
    }

    /**
     * Returns node containing the pane.
     *
     * @return Node containing the pane.
     */
    @Override
    public Node getNode() {
        return scrollPane;
    }

    /**
     * Does nothing
     *
     * @param args Not used.
     */
    @Override
    public void update(Object... args) {
        // intentionally left blank
    }
}
