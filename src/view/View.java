package view;

import controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import utilities.Command;
import utilities.Coordinate;
import view.commandgenerator.CommandGenerator;
import view.mainpane.MainPane;
import view.mainpane.MainVisualiser;
import view.selector.Feature;
import view.selector.features.ColorPicker;
import view.selector.features.ImagePicker;
import view.selector.features.variabletextfeature.CommandHistory;
import view.selector.features.variabletextfeature.EnvironmentVariables;
import view.selector.features.variabletextfeature.UserCommands;
import view.statepane.BasicStatePane;
import view.statepane.StatePane;
import view.turtlearena.BasicTurtleArena;
import view.turtlearena.TurtleArena;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * The view contains the controller as well as all frontend objects.
 * The view acts as the bridge between the backend controller and the frontend objects,
 * sending commands to the backend for parsing and executing the backend's instructions on frontend objects.
 * The view also acts as a middle man between separate frontend objects, sending information between various frontend objects
 * instead of allowing frontend objects to directly communicate with each other. This is to increase encapsulation.
 *
 * @author Goh Khian Wei
 * @author Ben lawrence
 * @author Joshua Medway
 */
public class View implements BasicView, CmdObserver, StateObserver, FeatureReceiver {
    private static final String RESOURCE_BUNDLE_LOCATION = "view/resources/";
    private static final String DEFAULT_RESOURCE_FILE = "english";
    private static final String DEFAULT_FEATURE_RESOURCE_FILE = "features";
    private static final String RESOURCE_DEFAULT_LANGUAGE = "defaultlanguage";
    private static final String STATE_PANE_NAME = "statePaneName";
    private static final String ERROR_UNABLE_TO_INITIALISE_FEATURE = "errorfeatureinit";
    private static final String ERROR_IMAGE_LOADING = "errorimageloading";
    private static final int DEFAULT_IMAGE_INDEX = 0;
    private static final int INITIAL_TURTLE_INDEX = 0;
    private static final int NOT_OBSERVER = 3;

    private TurtleArena turtleArena;
    private Controller control;
    private CommandGenerator commandGenerator;
    private ResourceBundle myResources;
    private MainPane mainVisualiser;
    private Map<Class, Feature> features;
    private StatePane statePane;
    private ColorPicker cPicker;
    private ImagePicker iPicker;
    private String language;
    private Image defaultImage;
    private Color defaultPenColor;
    private double defaultPenWidth;
    private Set<LanguageChangeObserver> languageChangeObservers;

    public View() {
        initUtils();
        initVisualiserObjects();
    }

    /**
     * Method to change the background colour of the turtle arena
     *
     * @param colorIndex Index corresponding to a colour in the palette
     */
    @Override
    public void changeBackgroundColor(int colorIndex) {
        turtleArena.changeBackgroundColor(cPicker.getColorFromIndex(colorIndex));
    }

    /**
     * Method to change the pen colour of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param colorIndex  Index corresponding to a colour in the palette
     */
    @Override
    public void changePenColor(int turtleIndex, int colorIndex) {
        turtleArena.changePenColor(turtleIndex, cPicker.getColorFromIndex(colorIndex));
        statePane.updatePenColor(turtleIndex, cPicker.getColorFromIndex(colorIndex));
    }

    /**
     * Method to change the image of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param shapeIndex  Index corresponding to a image in the image repository
     */
    @Override
    public void changeTurtleImage(int turtleIndex, int shapeIndex) {
        turtleArena.changeTurtleImage(turtleIndex, iPicker.getImageFromIndex(shapeIndex));
    }

    /**
     * Method to update the pen status of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param penState    New pen status
     */
    @Override
    public void updatePenUp(int turtleIndex, boolean penState) {
        statePane.updatePenUp(turtleIndex, penState);
    }

    /**
     * Method to set the pen size of a specific turtle
     *
     * @param turtleIndex Index of turtle
     * @param pixels      Width of the pen
     */
    @Override
    public void setPenSize(int turtleIndex, double pixels) {
        turtleArena.changePenThickness(turtleIndex, pixels);
        statePane.updatePenThickness(turtleIndex, pixels);
    }

    /**
     * Method to set the colour corresponding to a specific index of the palette
     *
     * @param index Index
     * @param red   0-255 integer representation of red component
     * @param green 0-255 integer representation of green component
     * @param blue  0-255 integer representation of blue component
     */
    @Override
    public void setPaletteIndex(int index, int red, int green, int blue) {
        cPicker.addNewColorIndex(index, red, green, blue);
    }

    /**
     * Method to clear and reset the turtle arena
     */
    @Override
    public void clearScreen() {
        turtleArena.clearScreen();
    }

    /**
     * Displays an error message on the screen for the user to see
     *
     * @param message - String message to be displayed to the user
     */
    @Override
    public void displayErrorMessage(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }

    /**
     * Method to hide a specific turtle from view
     *
     * @param turtleIndex Index of turtle
     */
    @Override
    public void hideTurtle(int turtleIndex) {
        turtleArena.hideTurtle(turtleIndex);
    }

    /**
     * Method to show a specific turtle
     *
     * @param turtleIndex Index of turtle
     */
    @Override
    public void showTurtle(int turtleIndex) {
        turtleArena.showTurtle(turtleIndex);
    }

    /**
     * Method to add a turtle with specified index to the front-end view
     *
     * @param turtleIndex Index of turtle
     */
    @Override
    public void addTurtle(int turtleIndex) {
        turtleArena.addTurtle(turtleIndex);
        statePane.addTurtle(turtleIndex);
    }

    /**
     * This is called to to tell the view to add a line to the turtle arena starting at start coordinate and ending at end coordinate. The line belongs to the turtle with
     * the given index.
     *
     * @param index - Index of the turtle whose movement caused the line to be drawn
     * @param start - Start coordinate in SLogo coordinates of line
     * @param end - end coordinate in SLogo coordinates of line
     */
    @Override
    public void addLine(int index, Coordinate start, Coordinate end) {
        turtleArena.addLine(index, end.getX(), -end.getY());
        statePane.updateTurtlePosition(index, new double[]{end.getX(), end.getY()});
    }

    /**
     * Move the turtle with the given index from the start coordinate to the end coordinate.
     *
     * @param turtleIndex - Index of the turtle to be moved
     * @param start - Start coordinate of the turtle in SLogo coordinate system
     * @param end - End coordinate of the turtle in SLogo coordinate system
     */
    @Override
    public void moveTurtle(int turtleIndex, Coordinate start, Coordinate end) {
        turtleArena.moveTurtle(turtleIndex, end.getX(), flipY(end.getY()));
        statePane.updateTurtlePosition(turtleIndex, new double[]{end.getX(), end.getY()});
    }

    /**
     * Rotates the turtle clockwise by a certain number of degrees
     *
     * @param turtleIndex - Index of the turtle to be rotated
     * @param degrees - Double number of degrees the turtle rotates clockwise
     */
    @Override
    public void rotateTurtle(int turtleIndex, double degrees) {
        turtleArena.rotateTurtle(turtleIndex, degrees);
        statePane.updateTurtleHeading(turtleIndex, degrees);
    }

    /**
     * Updates the command history with the given list of user commands entered into the display
     *
     * @param history - List of string values containing the commands entered into the command prompt
     */
    @Override
    public void updateCommandHistory(List<String> history) {
        features.get(CommandHistory.class).update(history);
    }

    /**
     * Updates the custom variable history with the given list of user custom variables entered into the display
     *
     * @param variables - Map of strings containing variable names and values
     */
    @Override
    public void updateCustomVariables(Map<String, String> variables) {
        features.get(EnvironmentVariables.class).update(variables);
    }

    /**
     * Updates the user defined command history with the given list of user created commands entered into the display
     *
     * @param userCommands - List of string values containing the user defined commands entered into the command prompt
     */
    @Override
    public void updateUserCommands(List<String> userCommands) {
        features.get(UserCommands.class).update(userCommands);
    }

    /**
     * Update command used for the observer based design pattern. The terminal prompt text box sends commands to the backend asynchronously, and are sent via this method.
     *
     * @param cmd Verbatim command that the user has input
     */
    @Override
    public void updateCmd(String cmd) {
        update(cmd);
    }

    /**
     * Changes the language used to interpret commands and that is displayed on the front end.
     *
     * @param language - String language representing new language being used to parse commands
     */
    @Override
    public void changeLanguage(String language) {
        String oldLanguage = this.language;
        this.language = language;
        control.changeLanguage(this.language);
        languageChangeObservers.forEach(observer -> observer.updateLanguage(language, oldLanguage));
    }

    /**
     * Asynchronously asks the backend for the most recent list of user variables
     *
     * @return - map of strings representing the variable name and values
     */
    @Override
    public Map<String, String> getCurrentVariables() {
        return control.getCurrentVariables();
    }

    /**
     * Called to asynchronously ask the backend for the most up to date command history
     *
     * @return - Returns a list of strings representing the newest list of recent commands
     */
    @Override
    public List<String> getHistory() {
        return control.getHistory();
    }

    /**
     * Asynchronously asks the backend for the most recent user commands
     *
     * @return - Returns a list of strings representing the most recent list of user defined commands
     */
    @Override
    public List<String> getUserCommands() {
        return control.getUserCommands();
    }

    /**
     * Sets the position of a turtle in the turtle arena to the new position specified by generating a command for parsing by the back-end
     *
     * @param turtleIndex Index of turtle
     * @param xPos        X coordinate of new position
     * @param yPos        Y coordinate of new position
     */
    @Override
    public void updatePosition(int turtleIndex, int xPos, int yPos) {
        String commands = commandGenerator.generateSetXY(language, turtleIndex, xPos, yPos);
        update(commands);
    }

    /**
     * Sets the heading of a turtle in the turtle arena to a specified heading by generating a command string that is parsed and interpreted by the back-end
     *
     * @param turtleIndex Index of turtle
     * @param angle       Angle of new heading
     */
    @Override
    public void updateHeading(int turtleIndex, double angle) {
        String commands = commandGenerator.generateSetHeading(language, turtleIndex, angle);
        update(commands);
    }

    /**
     * Sets the pen status of a pen turtle to the specified pen status through generation of a command string that is sent to the backend for parsing
     *
     * @param turtleIndex Index of turtle
     * @param penStatus   New pen status
     */
    @Override
    public void updatePenStatus(int turtleIndex, boolean penStatus) {
        String commands = commandGenerator.generatePenStatus(language, turtleIndex, penStatus);
        update(commands);
    }

    /**
     * Sets the pen colour of a specific turtle and updates the corresponding value in the state pane.
     *
     * @param turtleIndex Index of turtle
     * @param color       New pen colour
     */
    @Override
    public void updatePenColour(int turtleIndex, Color color) {
        turtleArena.changePenColor(turtleIndex, color);
        statePane.updatePenColor(turtleIndex, color);
    }

    /**
     * Sets the pen thickness of a specific turtle and updates the corresponding value in the state pane
     *
     * @param turtleIndex Index of turtle
     * @param thickness   New pen thickness
     */
    @Override
    public void updatePenThickness(int turtleIndex, double thickness) {
        turtleArena.changePenThickness(turtleIndex, thickness);
        statePane.updatePenThickness(turtleIndex, thickness);
    }

    /**
     * Displays an alert and waits for user to acknowledge alert, with specific error message displayed in the alert.
     *
     * @param e Caught exception
     */
    @Override
    public void displayError(InvocationTargetException e) {
        new Alert(Alert.AlertType.ERROR, e.getCause().getMessage()).showAndWait();
    }

    /**
     * Returns the root node of all the front end display
     *
     * @return - Node object pointing to root of JavaFX tree
     */
    public Node getNode() {
        return mainVisualiser.getNode();
    }

    /**
     * Sets the default image used when making new turtles
     *
     * @param index - Index of the image in the the turtle image palette
     */
    public void setDefaultTurtleImage(int index) {
        try {
            turtleArena.setDefaultImage(iPicker.getImageFromIndex(index));
        } catch (Exception e) {
            defaultImage = null;
            new Alert(Alert.AlertType.ERROR, myResources.getString(ERROR_IMAGE_LOADING)).showAndWait();
        }
    }

    /**
     * Sets the default pen color when creating a new turtle
     *
     * @param index - Index of the color in the color palette
     */
    public void setDefaultPenColor(int index) {
        turtleArena.setDefaultPenColor(cPicker.getColorFromIndex(index));
        statePane.setDefaultPenColor(cPicker.getColorFromIndex(index));
    }

    /**
     * Sets the default pen width when creating a new turtle
     *
     * @param width - Double width in pixels of line
     */
    public void setDefaultPenWidth(double width) {
        turtleArena.setDefaultPenWidth(width);
        statePane.setDefaultPenWidth(width);
    }

    private void initFeatures(ResourceBundle featureResources) {
        features = new HashMap<>();
        languageChangeObservers = new HashSet<>();
        featureResources.getKeys().asIterator().forEachRemaining(key -> {
            initFeature(featureResources.getString(key).split(","));
        });
    }

    private void initFeature(String[] featureInfo) {
        try {
            Class<?> clazz = Class.forName(featureInfo[2]);
            Object obj = clazz.getDeclaredConstructor(Class.forName(featureInfo[1])).newInstance(this);
            Feature f = (Feature) obj;
            features.put(clazz, f);
            mainVisualiser.addPane(f.getNode(), featureInfo[0]);
            if (featureInfo.length > NOT_OBSERVER && Class.forName(featureInfo[NOT_OBSERVER]).equals(LanguageChangeObserver.class)) {
                languageChangeObservers.add((LanguageChangeObserver) obj);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, myResources.getString(ERROR_UNABLE_TO_INITIALISE_FEATURE)).showAndWait();
        }
    }

    private void update(String cmd) {
        List<Command> commands = control.parseCommands(cmd);
        BasicView bView = this;
        commands.forEach(command -> command.execute(bView));
    }

    private double flipY(double yVal) {
        return -yVal;
    }

    private void initDefaultValues() {
        iPicker = new ImagePicker();
        defaultImage = iPicker.getImageFromIndex(DEFAULT_IMAGE_INDEX);
        defaultPenColor = Color.BLUE;
        defaultPenWidth = 1;
    }

    private void initTurtleArena() {
        addTurtle(INITIAL_TURTLE_INDEX);
        turtleArena.clearScreen();
    }

    private void initMainVisualiserTurtleArena() {
        turtleArena = new BasicTurtleArena(defaultImage, defaultPenColor, defaultPenWidth);
        mainVisualiser = new MainVisualiser(turtleArena.getTurtleArena());
    }

    private void initStatePane() {
        statePane = new BasicStatePane(this, defaultPenColor, defaultPenWidth);
        mainVisualiser.addPane(statePane.getNode(), myResources.getString(STATE_PANE_NAME));
    }

    private void initUtils() {
        myResources = ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION + DEFAULT_RESOURCE_FILE);
        language = myResources.getString(RESOURCE_DEFAULT_LANGUAGE);
        control = new Controller(language);
        cPicker = new ColorPicker();
        commandGenerator = CommandGenerator.getInstance();
    }

    private void initVisualiserObjects() {
        initDefaultValues();
        initMainVisualiserTurtleArena();
        initFeatures(ResourceBundle.getBundle(RESOURCE_BUNDLE_LOCATION + DEFAULT_FEATURE_RESOURCE_FILE));
        initStatePane();
        initTurtleArena();
    }
}
