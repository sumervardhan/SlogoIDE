package workspaces;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.View;
import view.selector.features.FileSelector;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class is responsible for building workspaces. It also saves newly created workspaces and loads workspaces from files.
 *
 * @author Ben lawrence
 */
public class WorkspaceBuilder {
    private final static String PATH_TO_SAVE_PROPERTIES = "/src/workspaces/resources/workspaces/";
    private static final String FILE_DESCRIPTION = "Properties files (*.properties)";
    private static final String FILE_EXTENSION = ".properties";
    private static final String BUTTON_TEXT = "Load Workspace";
    private static final String DEFAULT_WORKSPACE_TITLE = "Workspace";
    private static final String SAVE_BUTTON_TEXT = "Save and Create new Workspace";
    private static final int SHORT_SPACING = 10;
    private static final int LONG_SPACING = 50;
    private static final String SAVE_ERROR_MESSAGE = "Error saving file. Please Try again.";
    private static final String LOADING_ERROR_MESSAGE = "Error loading file. Using default values...";

    private VBox box;
    private WorkspaceList wList;
    private LanguagePicker languagePicker;
    private BackgroundPicker backgroundPicker;
    private PalletteMaker palletteMaker;
    private TextField title;
    private DefaultValueSelector defaultValueSelector;
    private FileSelector fileSelector;
    private Map<Class, WorkspaceSetting> objectMap;


    /**
     * The constructor instantiates all workspace settings objects used to create a workspace.
     *
     * @param wList - Takes in a workspace list object. This allows the creation of workspaces to happen asynchronously.
     */
    public WorkspaceBuilder(WorkspaceList wList) {
        this.wList = wList;
        objectMap = new HashMap<>();

        box = new VBox();
        box.setPadding(new Insets(SHORT_SPACING, LONG_SPACING, LONG_SPACING, SHORT_SPACING));
        box.setSpacing(SHORT_SPACING);

        title = new TextField();
        box.getChildren().add(title);

        languagePicker = new LanguagePicker();
        addToMapAndBox(languagePicker);

        palletteMaker = new PalletteMaker();
        addToMapAndBox(palletteMaker);

        defaultValueSelector = new DefaultValueSelector();
        addToMapAndBox(defaultValueSelector);

        backgroundPicker = new BackgroundPicker();
        addToMapAndBox(backgroundPicker);

        Button generateWorkspace = new Button(SAVE_BUTTON_TEXT);
        generateWorkspace.setOnAction(event -> getNewWorkspace());
        box.getChildren().add(generateWorkspace);

        box.getChildren().add(new Text("OR"));

        fileSelector = new FileSelector(FILE_DESCRIPTION, "*" + FILE_EXTENSION);
        Button chooseFile = new Button(BUTTON_TEXT);
        chooseFile.setOnAction(event -> generateWorkspaceFromFile());
        box.getChildren().add(chooseFile);
    }

    /**
     *
     * @return - Returns the top level node of the JavaFX tree holding all the controls for choosing a workspace's settings.
     */
    public Node getNode() {
        return box;
    }

    private void getNewWorkspace() {
        saveProperties();
        wList.addWorkspace(getNewView(), getWorkspaceTitle());
    }

    private View getNewView() {
        View v = new View();

        languagePicker.setProperty(v);
        palletteMaker.setProperty(v);
        backgroundPicker.setProperty(v);
        defaultValueSelector.setProperty(v);

        return v;
    }

    private void generateWorkspaceFromFile() {
        wList.addWorkspace(getNewViewFromFile(), getWorkspaceTitle());
    }

    // https://www.baeldung.com/java-method-reflection
    private View getNewViewFromFile() {
        File f = fileSelector.getFile();
        View v = new View();
        try (InputStream input = new FileInputStream(f)) {
            Properties prop = new Properties();

            prop.load(input);
            prop.keySet().forEach(key -> {
                try {
                    Class<?> clazz = Class.forName((String) key);
                    WorkspaceSetting workspaceSetting = objectMap.get(clazz);
                    workspaceSetting.setSettingValue((String) prop.get(key));
                } catch (Exception e) {

                }
            });

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, LOADING_ERROR_MESSAGE).showAndWait();
        }

        return v;
    }

    // https://www.mkyong.com/java/java-properties-file-examples/
    private void saveProperties() {
        try (OutputStream output = new FileOutputStream(System.getProperty("user.dir") + PATH_TO_SAVE_PROPERTIES + getWorkspaceTitle() + FILE_EXTENSION)) {
            Properties prop = new Properties();
            prop.setProperty("Title", getWorkspaceTitle());

            languagePicker.saveProperties(prop);
            palletteMaker.saveProperties(prop);
            backgroundPicker.saveProperties(prop);
            defaultValueSelector.saveProperties(prop);

            prop.store(output, null);
        } catch (Exception E) {
            new Alert(Alert.AlertType.ERROR, SAVE_ERROR_MESSAGE).showAndWait();
        }
    }

    private String getWorkspaceTitle() {
        return (title.getText().equals("") ? DEFAULT_WORKSPACE_TITLE : title.getText());
    }

    private void addToMapAndBox(WorkspaceSetting workspaceSetting) {
        box.getChildren().add(workspaceSetting.getNode());
        objectMap.put(workspaceSetting.getClass(), workspaceSetting);
    }
}
