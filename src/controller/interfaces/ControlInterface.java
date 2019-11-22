package controller.interfaces;

import utilities.Command;

import java.util.List;
import java.util.Map;

/**
 * This is the controller Interface, which is in charge of getting the current variables and the history of the commands
 * The rest of the Controller commands are in the ControlParser
 */
public interface ControlInterface {

    Map<String, String> getCurrentVariables();

    List<String> getHistory();

    List<Command> parseCommands(String commands);
}
