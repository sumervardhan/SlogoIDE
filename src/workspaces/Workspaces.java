package workspaces;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import view.View;

/**
 * Thsi class is responsible for holding all of the workspaces along with their views. This class allows a user to add a workspace and manages the views held in that workspace.
 *
 * @author Ben lawrence
 */
public class Workspaces implements WorkspaceList {
    private static final double STAGE_HEIGHT = 500;
    private static final double STAGE_WIDTH = 1000;

    private Stage myStage;
    private TabPane tabPane;
    private Scene myScene;
    private WorkspaceBuilder wBuilder;

    /**
     * The constructor for the Workspaces class.
     *
     * @param stage - Takes in a JavaFX stage on which it holds all of the workspaces.
     */
    public Workspaces(Stage stage) {
        tabPane = new TabPane();
        myStage = stage;
        myScene = new Scene(tabPane);
        wBuilder = new WorkspaceBuilder(this);
        Tab t = new Tab("Workspace Builder", wBuilder.getNode());
        t.setClosable(false);
        tabPane.getTabs().add(t);
        initStage();
    }

    @Override
    public void addWorkspace(View v, String title) {
        tabPane.getTabs().add(new Tab(title, v.getNode()));
    }

    private void initStage() {
        myStage.setScene(myScene);
        myStage.setHeight(STAGE_HEIGHT);
        myStage.setWidth(STAGE_WIDTH);
        myStage.show();
    }

}
