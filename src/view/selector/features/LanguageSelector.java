package view.selector.features;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import view.FeatureReceiver;
import view.selector.Feature;

import java.util.ResourceBundle;

/**
 * This class follows the observer design pattern. The observer is the front end and is updated when this class tells it to update. This class is specifically responsible for
 * telling the front end when to update its language based on how the user interacts with this class.
 *
 * @author Ben Lawrence
 */
public class LanguageSelector implements Feature {
    private ComboBox selector;
    private ResourceBundle resourceBundle;
    private FeatureReceiver receiver;

    /**
     * Constructor for LanguageSelector class.
     *
     * @param observer - Observer object representing the front end. The observer is told when to update based on how the user interacts with the JavaFX components initialzied
     *                 here.
     */
    public LanguageSelector(FeatureReceiver observer) {
        selector = new ComboBox();
        resourceBundle = ResourceBundle.getBundle("/resources/languages/LanguageOptions");
        resourceBundle.getKeys().asIterator().forEachRemaining(key -> {
            try {
                selector.getItems().add(resourceBundle.getString(key));
            } catch (Exception e) {
                //do nothing
            }
        });

        selector.setOnAction(event -> updateLanguage());
        this.receiver = observer;

        update("English");
    }

    /**
     * Allows the front end to get the root node representing the JavaFX object(s) for selecting language.
     *
     * @return - Returns the root Node of the javaFX tree holding the language objects.
     */
    @Override
    public Node getNode() {
        return selector;
    }

    /**
     * Tells the language selector to update which language is currently being displayed as the active language. This is used if the user changes the language via the workspace
     * builder, command line, or some other means.
     *
     * @param args - Takes in variable arguments representing the string value of the active language
     */
    @Override
    public void update(Object... args) {
        try {
            selector.getSelectionModel().select(args[0]);
            updateLanguage();
        } catch (Exception e) {
            // TODO: display error if incorrect language option
        }
    }

    private void updateLanguage() {
        receiver.changeLanguage((String) selector.getValue());
    }
}