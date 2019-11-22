package view.commandgenerator;

import java.util.ResourceBundle;

/**
 * The CommandGenerator class generates specific command strings in the appropriate language based on the method invoked.
 * It is used to ensure that the user does not have to care about the current program language to instruct the program graphically.
 * This is a internal utility that assumes that all inputs are correct, as such no error checking is included.
 * <p>
 * This class follows the singleton design pattern as one object is sufficient to handle all tasks the class is responsible for.
 *
 * @author Goh Khian Wei
 */
public class CommandGenerator {
    private static final String COMMAND_LANGUAGE_RESOURCES_PATH = "resources/languages/";
    private static final String SET_POSITION = "SetPosition";
    private static final String SET_HEADING = "SetHeading";
    private static final String PEN_UP = "PenUp";
    private static final String PEN_DOWN = "PenDown";
    private static final String ASK = "Ask";
    private static final String SPLIT_REGEX = "\\|+?";

    private static CommandGenerator commandGenerator;
    private ResourceBundle commandTranslatorResource;

    private CommandGenerator() {

    }

    /**
     * Ensures that only one instance of the CommandGenerator class is ever created.
     *
     * @return instance of CommandGenerator
     */
    public static CommandGenerator getInstance() {
        if (commandGenerator == null) {
            commandGenerator = new CommandGenerator();
        }
        return commandGenerator;
    }

    /**
     * Returns a command string instructing a particular turtle to change position.
     *
     * @param language    Current program language
     * @param turtleIndex Index of turtle to be affected
     * @param xPos        x-coordinate of new position
     * @param yPos        y-coordinate of new position
     * @return Command string that can be sent to the controller for parsing
     */
    public String generateSetXY(String language, int turtleIndex, int xPos, int yPos) {
        commandTranslatorResource = ResourceBundle.getBundle(COMMAND_LANGUAGE_RESOURCES_PATH + language);
        String askTurtle = generateAskTurtleString(turtleIndex);
        String actualCommand = String.format("[ %s %d %d ]", commandTranslatorResource.getString(SET_POSITION).split(SPLIT_REGEX)[0], xPos, yPos);
        return String.join("\n", askTurtle, actualCommand);
    }

    /**
     * Returns a command string instructing a particular turtle to rotate to a particular heading
     *
     * @param language    Current program language
     * @param turtleIndex Index of turtle to be affected
     * @param angle       New heading angle
     * @return Command string that can be sent to the controller for parsing
     */
    public String generateSetHeading(String language, int turtleIndex, double angle) {
        commandTranslatorResource = ResourceBundle.getBundle(COMMAND_LANGUAGE_RESOURCES_PATH + language);
        String askTurtle = generateAskTurtleString(turtleIndex);
        String actualCommand = String.format("[ %s %.0f ]", commandTranslatorResource.getString(SET_HEADING).split(SPLIT_REGEX)[0], angle);
        return String.join("\n", askTurtle, actualCommand);
    }

    /**
     * Returns a command string instructing a particular turtle to change its pen status (penup/pendown)
     *
     * @param language    Current program language
     * @param turtleIndex Index of turtle to be affected
     * @param penStatus   New pen status
     * @return Command string that can be sent to controller for parsing
     */
    public String generatePenStatus(String language, int turtleIndex, boolean penStatus) {
        commandTranslatorResource = ResourceBundle.getBundle(COMMAND_LANGUAGE_RESOURCES_PATH + language);
        String askTurtle = generateAskTurtleString(turtleIndex);
        String actualCommand;
        if (penStatus) {
            actualCommand = String.format("[ %s ]", commandTranslatorResource.getString(PEN_UP).split(SPLIT_REGEX)[0]);
        } else {
            actualCommand = String.format("[ %s ]", commandTranslatorResource.getString(PEN_DOWN).split(SPLIT_REGEX)[0]);
        }
        return String.join("\n", askTurtle, actualCommand);
    }

    private String generateAskTurtleString(int turtleIndex) {
        return String.format("%s [ %d ]", commandTranslatorResource.getString(ASK).split(SPLIT_REGEX)[0], turtleIndex);
    }

}
