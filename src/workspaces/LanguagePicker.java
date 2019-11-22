package workspaces;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import view.View;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * This class is responsible for setting the new workspace's language.
 *
 * @author Ben lawrence
 */
public class LanguagePicker implements WorkspaceSetting {
    private static final String SET_ERROR_MESSAGE = "Error selecting language from file";
    private static final String INITIAL_LOADING_ERROR_MESSAGE = "Error loading language. Files not configured correctly";
    private static final String PROMPT_TEXT = "Language";
    private static final String DEFAULT_LANGUAGE = "English";
    private ComboBox languages;
    private ResourceBundle resourceBundle;

    /**
     * The constructor for the LanguagePicker class. The languages are read from a file and displayed to the user.
     */
    public LanguagePicker() {
        languages = new ComboBox();
        languages.setPromptText(PROMPT_TEXT);

        resourceBundle = ResourceBundle.getBundle("/resources/languages/LanguageOptions");
        resourceBundle.getKeys().asIterator().forEachRemaining(key -> {
            try {
                languages.getItems().add(resourceBundle.getString(key));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, INITIAL_LOADING_ERROR_MESSAGE).showAndWait();
            }
        });
    }

    /**
     * Sets the view's language to the chosen language.
     *
     * @param v - View object whose language is being selected.
     */
    @Override
    public void setProperty(View v) {
        if (languages.getValue() == null) {
            v.changeLanguage(DEFAULT_LANGUAGE);
        } else {
            v.changeLanguage((String) languages.getValue());
        }

    }

    /**
     * Saves the selected language to a properties file for later loading a worksapce.
     *
     * @param properties - The Java Properties object saving the key + value pairs
     */
    @Override
    public void saveProperties(Properties properties) {
        String val = "" + ((languages.getValue() == null) ? DEFAULT_LANGUAGE : languages.getValue());
        properties.setProperty("" + this.getClass(), val);
    }

    /**
     *
     * @return - Returns top node of JavaFX tree showing controls to pick language.
     */
    @Override
    public Node getNode() {
        return languages;
    }

    public void setSettingValue(String lan) {
        try {
            languages.setValue(lan);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, SET_ERROR_MESSAGE).showAndWait();
        }

    }
}
