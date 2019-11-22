package view;

import java.util.List;
import java.util.Map;

/**
 * This interface defines the configuration functionality of a view class.
 * Its methods are mainly concerned with the few things that a view stores.
 *
 * @author Ben Lawrence
 * @author Khian Wei Goh
 * @author Joshua Medway
 * @author Sumer Vardhan
 */
public interface ConfigurationView {

    /**
     * Tells the view to display an error message to the user
     *
     * @param message - String message that should be shown to the user
     */
    void displayErrorMessage(String message);

    /**
     * Tells the view to update its list of user variables
     *
     *  @param variables - Map of string variable names to string variable values
     */
    void updateCustomVariables(Map<String, String> variables);

    /**
     * Tells the view to update its command history
     *
     * @param history - List of strings containing command history of user input
     */
    void updateCommandHistory(List<String> history);

    /**
     * Tells the view to update its list of user defined commands
     *
     * @param userCommands - List of strings representing user defined commands
     */
    void updateUserCommands(List<String> userCommands);
}
