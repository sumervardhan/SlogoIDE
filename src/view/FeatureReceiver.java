package view;

import controller.interfaces.Language;

import java.util.List;
import java.util.Map;

/**
 * This interface follows the observer design pattern.
 * This is given to classes which implement the Feature interface so that there can be
 * two way communication between the View and the features
 *
 * @author Ben Lawrence
 * @author Khian Wei Goh
 */
public interface FeatureReceiver extends StaticView, Language {

    /**
     *Asks the observers for a map of current variables
     *
     * @return - Map of strings where key is variable name and value is value of variable
     */
    Map<String, String> getCurrentVariables();

    /**
     * Asks observer for most recent history
     *
     * @return - list of strings containing history of commands
     */
    List<String> getHistory();

    /**
     * Asks observer for most recent set of user defined commands
     *
     * @return - List of strings holding most recent user defined commands
     */
    List<String> getUserCommands();
}
