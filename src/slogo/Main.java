package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import workspaces.Workspaces;

import java.util.ResourceBundle;

/**
 * Launch point of program. Sets the stage title and launches the workspace builder scene.
 * Stage name is stored in resource file.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 */
public class Main extends Application {
    private static final String RESOURCE_BUNDLE_LOCATION = "slogo/resources/english";
    private static final String KEY_TITLE = "stagename";

    /**
     * Start of the program.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Launch point for Application class.
     *
     * @param stage initial stage to be displayed.
     */
    @Override
    public void start(Stage stage) {
        ResourceBundle myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION);
        stage.setTitle(myResources.getString(KEY_TITLE));
        Workspaces v = new Workspaces(stage);
    }
}
