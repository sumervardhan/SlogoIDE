package view.selector.features.variabletextfeature;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.FeatureReceiver;
import view.selector.Feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class keeps track of the user defined variables in the program. It then creates a JavaFX tree of nodes to display the user defined commands in an easy to understand way.
 *
 * @author Ben Lawrence
 */
public class EnvironmentVariables implements Feature {
    private ScrollPane scrollPane;
    private FeatureReceiver observer;
    private List<String> envVariables;
    private VBox historyNodes;

    /**
     * Constructor for the EnvironmentVariables class. The constructor creates the VBox to as the root node of the tree along with initializing all other private variables.
     *
     * @param observer - Does not do anything. The use of the observer is deprecated. Formally, this class updated variables asynchronously. Now, it is done with a command via
     *                 the update() method.
     */
    public EnvironmentVariables(FeatureReceiver observer) {
        historyNodes = new VBox();
        scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setContent(historyNodes);
        this.observer = observer;
        envVariables = new ArrayList<>();

        setNewEnvVars();
    }

    /**
     * Gets the root node to display the user defined variables.
     *
     * @return - Returns a JavaFX Node object as the root of the tree containing all the user defined variables.
     */
    @Override
    public Node getNode() {
        setNewEnvVars();
        return scrollPane;
    }

    /**
     * Updates the user defined variables with the given arguments. Since different Features take in differing parameters, the map of variables to values is passed in as a set
     * of variable arguments.
     *
     * @param args - Variable arguments representing the map of string variable names to string variable values.
     */
    @Override
    public void update(Object... args) {
        try {
            Map<String, String> envVars = (HashMap) args[0];
            envVariables.clear();
            envVars.keySet().forEach(key -> {
                envVariables.add(key + " = " + envVars.get(key));
            });
        } catch (Exception e) {

        }

        setNewEnvVars();
    }

    private void setNewEnvVars() {
        try {
            historyNodes.getChildren().clear();
            if (envVariables.size() != 0) {
                envVariables.forEach(elem -> {
                    Text histTxt = new Text();
                    histTxt.setText(elem);
                    historyNodes.getChildren().add(histTxt);
                    historyNodes.getChildren().add(new Text("--------"));
                });
            } else {
                historyNodes.getChildren().add(new Text("No variables have been created yet"));
            }
        } catch (Exception e) {
            envVariables = new ArrayList<>();
            historyNodes.getChildren().add(new Text("No variables have been created yet"));
        }
    }
}
