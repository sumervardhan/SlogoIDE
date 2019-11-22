package view.selector.features.variabletextfeature;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.FeatureReceiver;
import view.LanguageChangeObserver;
import view.selector.Feature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Abstract class representing panes containing dynamically changing text that can be dynamically translated.
 * Implements Feature and LanguageChangeObserver separately for encapsulation, ensuring that different methods can be made available to different objects.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 */

public abstract class VariableTextFeature implements Feature, LanguageChangeObserver {

    private ScrollPane scrollPane;
    private FeatureReceiver observer;
    private List<String> history;
    private VBox historyNodes;
    private boolean containsHistory;

    /**
     * Constructor, creates and initialises the pane and the text contained within.
     * Stores the FeatureReceiver from which to retrieve information from.
     *
     * @param view FeatureReceiver from which to retrieve information from.
     */
    public VariableTextFeature(FeatureReceiver view) {
        historyNodes = new VBox();
        scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setContent(historyNodes);
        this.observer = view;
        history = new ArrayList<>();

        containsHistory = false;
        history = updateHistory(view);
        setNewHistory();
    }

    /**
     * Returns the node containing the pane.
     *
     * @return The node containing the pane.
     */
    @Override
    public Node getNode() {
        history = updateHistory(observer);
        setNewHistory();
        return scrollPane;
    }

    /**
     * Updates the text content in the pane.
     *
     * @param args Not required.
     */
    @Override
    public void update(Object... args) {
        history = updateHistory(observer);
        setNewHistory();
    }

    /**
     * Updates the language of the text contained within the pane from the old language to the new langauage
     *
     * @param newLanguage New language to be translated to
     * @param oldLanguage Old language that pane is currently in
     */
    @Override
    public void updateLanguage(String newLanguage, String oldLanguage) {
        if (containsHistory) {
            ResourceBundle oldLanguageFile = ResourceBundle.getBundle(String.format("%s%s", VariableTextFeatureConstants.LANGUAGE_FOLDER, oldLanguage));
            Map<String, String> oldLanguageMap = parseOldLanguageFile(oldLanguageFile);
            ResourceBundle newLanguageFile = ResourceBundle.getBundle(String.format("%s%s", VariableTextFeatureConstants.LANGUAGE_FOLDER, newLanguage));
            historyNodes.getChildren().forEach(histTxt -> {
                updateCommandLanguage((Text) histTxt, oldLanguageMap, newLanguageFile);
            });
        }
    }

    protected abstract List<String> updateHistory(FeatureReceiver observer);

    protected abstract String getDefaultString();

    private void setNewHistory() {
        try {
            historyNodes.getChildren().clear();
            if (!history.isEmpty()) {
                history.forEach(cmd -> {
                    Text histTxt = new Text();
                    histTxt.setText(cmd);
                    historyNodes.getChildren().add(histTxt);
                    historyNodes.getChildren().add(new Text(VariableTextFeatureConstants.NEW_ENTRY_STRING));
                });
                containsHistory = true;
            } else {
                historyNodes.getChildren().add(new Text(getDefaultString()));
                containsHistory = false;
            }
        } catch (Exception e) {
            history = new ArrayList<>();
            historyNodes.getChildren().add(new Text(getDefaultString()));
            containsHistory = false;
        }
    }

    private Map<String, String> parseOldLanguageFile(ResourceBundle oldLanguageFile) {
        Map<String, String> oldLanguageMap = new HashMap<>();
        oldLanguageFile.getKeys().asIterator().forEachRemaining(key -> {
            String[] possibleCommandVariants = oldLanguageFile.getString(key).split("\\|+?");
            for (String variant : possibleCommandVariants) {
                oldLanguageMap.put(variant, key);
            }
        });
        return oldLanguageMap;
    }

    private void updateCommandLanguage(Text histTxt, Map<String, String> oldLanguageMap, ResourceBundle newLanguageFile) {

        // Reading string by line approach referenced from: https://stackoverflow.com/questions/9259411/what-is-the-best-way-to-iterate-over-the-lines-of-a-java-string
        Iterable<String> sc = () -> new Scanner(histTxt.getText()).useDelimiter("\n");
        List<String> newLines = new ArrayList<>();
        sc.forEach(line -> {
            String[] words = line.split("\\s+");
            for (int i = 0; i < words.length; i++) {
                if (oldLanguageMap.get(words[i]) != null) {
                    words[i] = newLanguageFile.getString(oldLanguageMap.get(words[i])).split("\\|+?")[0];
                }
            }
            String translatedLine = String.join(VariableTextFeatureConstants.STRING_SPACE, words);
            newLines.add(translatedLine);
        });
        String finalTranslatedText = String.join(VariableTextFeatureConstants.STRING_NEWLINE, newLines);
        histTxt.setText(finalTranslatedText);
    }

}
