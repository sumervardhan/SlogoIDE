package controller.interfaces;

import utilities.Command;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This is the commandOperator which is all the commands that the operator class uses to run to get variables and run the given command
 */
public interface CommandOperator {

    void runCommand(String command, Stack<String> commandStack, String turtleIndex);

    Map<String, String> getVars();

    List<String> getCustomCommands();

    void changeLanguage(String language);

    List<Command> getListCommands();
}
