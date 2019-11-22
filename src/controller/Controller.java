package controller;

import controller.exceptions.InvalidSyntaxException;
import controller.interfaces.CommandOperator;
import controller.interfaces.ControlInterface;
import controller.interfaces.ControlParser;
import utilities.Command;
import utilities.Commands.DisplayErrorCommand;
import utilities.Commands.UpdateCommandHistoryCommand;
import utilities.Commands.UpdateCustomVariablesCommand;
import utilities.Commands.UpdateUserCommandsCommand;

import java.util.*;

public class Controller implements ControlInterface, ControlParser {

    //Set command string delimiter - whitespace is default
    public static final String WHITESPACE = "\\s+";
    public static final String CONSTANT = "Constant";
    private CommandOperator operator;
    private List<String> commandHistory;
    private ControlParser parser;

    /**
     * This is the controller which is in charge of communicating with the front end and parsing through a given string command
     *
     * @param language
     */
    public Controller(String language) {
        parser = this;
        operator = new Operator(language, parser);
        commandHistory = new ArrayList<>();
    }

    private Stack<String> parseUserCommandsIntoStack(String commands) {
        String[] commandArray = commands.split(WHITESPACE);
        Stack<String> stack = new Stack<>();
        for (int i = commandArray.length - 1; i >= 0; i--) {
            stack.push(commandArray[i]);
        }
        return stack;
    }

    private String removeComments(String commands) {
        String[] lines = commands.split("\n");
        String newCommands = "";
        for (int x = 0; x < lines.length; x++) {
            if (lines[x].matches("\\s+?#.*") || lines[x].matches("^#.*"))
                lines[x] = "";
            newCommands += (x != lines.length - 1 && !lines[x].equals("")) ? lines[x] + " " : lines[x];
        }
        return newCommands;
    }

    /**
     * does the actual parsing of the commands and returns a list of commands that the front end uses to update
     *
     * @param commands
     * @return
     */
    //Returns List<Command>, after meaningfully parsing the commands as a string, to front end for execution
    @Override
    public List<Command> parseCommands(String commands) {
        commandHistory.add(commands);
        String newCommands = removeComments(commands);
        try {
            runOperations(newCommands, "-1");
        } catch (Exception e) {
            List<Command> list = getListCommands();
            list.add(new DisplayErrorCommand(e.getMessage()));
            return list;
        }
        return getListCommands();

    }

    private List<Command> getListCommands() {
        List<Command> listCommands = operator.getListCommands();
        listCommands.add(new UpdateCommandHistoryCommand(getHistory()));
        listCommands.add(new UpdateCustomVariablesCommand(getCurrentVariables()));
        listCommands.add(new UpdateUserCommandsCommand(getUserCommands()));
        return listCommands;
    }

    /**
     * is in charge of running the individual command operations, used in the back end to find parameters and return small operation types
     * also used by the main part of controller to run through all of the commands.
     *
     * @param commands
     * @param turtleIndex
     * @return
     */
    @Override
    public String runOperations(String commands, String turtleIndex) {
        Stack<String> commandStack = parseUserCommandsIntoStack(commands);
        String command = "";
        while (!commandStack.isEmpty()) {
            command = commandStack.pop();
            try {
                operator.runCommand(command, commandStack, turtleIndex);
            } catch (Exception e) {
                throw new InvalidSyntaxException(e.getMessage());
            }
        }
        return command;
    }

    /**
     * returns the current variables
     *
     * @return
     */
    @Override
    public Map<String, String> getCurrentVariables() {
        return operator.getVars();
    }

    /**
     * returns the history of commands run
     *
     * @return
     */
    @Override
    public List<String> getHistory() {
        List<String> commands = new ArrayList<>(commandHistory);
        Collections.reverse(commands);
        return commands;
    }

    /**
     * returns all of the user commands
     *
     * @return
     */
    public List<String> getUserCommands() {
        return operator.getCustomCommands();
    }

    /**
     * changes the language to something new
     *
     * @param language
     */
    public void changeLanguage(String language) {
        operator.changeLanguage(language);
    }
}
