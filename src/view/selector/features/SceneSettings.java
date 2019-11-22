package view.selector.features;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import view.FeatureReceiver;
import view.selector.Feature;
import view.selector.SelectorConstants;

import java.util.ResourceBundle;

/**
 * This class holds the visual objects that a user might use to interact with the scene. For example, this could hold pen color, background color, language, etc. This class does
 * not perform actions. Instead it reads from properties files what type of settings the user should be allowed to modify, and creates those respective classes.
 *
 * @author Ben Lawrence
 */
public class SceneSettings implements Feature {
    private ScrollPane scrollPane;
    private GridPane elems;

    private ResourceBundle resources;

    /**
     * The constructor for the SceneSettings class.
     *
     * @param observer - This is the observer passed off to other features. The scene settings class instantiates features and gives them the observer as an argument.
     */
    public SceneSettings(FeatureReceiver observer) {
        scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        elems = new GridPane();

        resources = ResourceBundle.getBundle(FeatureConstants.RESOURCE_PATH + "settings");
        resources.getKeys().asIterator().forEachRemaining(key -> {
            try {
                String[] val = resources.getString(key).split(",");
                Class<?> clazz = Class.forName(SelectorConstants.FEATURES_PATH + key);
                Feature f = (Feature) clazz.getDeclaredConstructor(FeatureReceiver.class).newInstance(observer);

                elems.add(f.getNode(), Integer.parseInt(val[0]), Integer.parseInt(val[1]));
            } catch (Exception e) {

            }
        });
        scrollPane.setContent(elems);
    }

    /**
     *
     * @return - Returns the root node of the javaFX tree representing the scene settings objects.
     */
    @Override
    public Node getNode() {
        return scrollPane;
    }

    /**
     * This method does nothing intentionally. There is nothing to update here since this feature creates other features instead of holding values.
     * @param args
     */
    @Override
    public void update(Object... args) {

    }
}
