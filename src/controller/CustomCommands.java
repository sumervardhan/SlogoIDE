package controller;

import controller.exceptions.InvalidSyntaxException;
import controller.interfaces.ControlParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCommands {

    public static final String NEW_COMMAND = "MakeUserInstruction";
    public static final String COMMAND = "Command";


    private Map<String, UserCommand> customCommands;
    private ArgumentType operatorParser;
    private ControlParser parser;

    /**
     * is in charge of running and storing custom commands
     */
    public CustomCommands(ArgumentType argument, ControlParser parser) {
        customCommands = new HashMap<>();
        this.operatorParser = argument;
        this.parser = parser;
    }

    /**
     * takes in the command and the parameters and either runs the custom command or creates a new command if it is a new command
     *
     * @param command the command type
     * @param params  the parameters
     * @return
     */
    public String handleCustomCommands(String command, List<String> params) {
        if (command.equals(NEW_COMMAND)) {
            return handleNewCommmand(params);
        } else if (operatorParser.getSymbol(command).equals(COMMAND)) {
            return handleCommand(command, params);
        } else {
            throw new InvalidSyntaxException("The input stream is invalid...");
        }
    }

    private String handleNewCommmand(List<String> params) {
        UserCommand command = new UserCommand(params);
        operatorParser.addArgs(params.get(0), command.getParamSize());
        customCommands.put(params.get(0), command);
        return "1";
    }

    private String handleCommand(String command, List<String> params) {
        if (customCommands.containsKey(command)) {
            UserCommand currentCommand = customCommands.get(command);
            return parser.runOperations(currentCommand.returnCustomOperation(params), "-1");
        } else {
            throw new InvalidSyntaxException("This is not a defined command...");
        }
    }

    /**
     * returns all the custom commands. used to display on the front end side
     *
     * @return
     */
    public List<String> getCustomCommands() {
        List<String> userCommands = new ArrayList<>();
        for (String s : customCommands.keySet()) {
            userCommands.add(s + " " + customCommands.get(s).toString());
        }
        return userCommands;
    }
}
