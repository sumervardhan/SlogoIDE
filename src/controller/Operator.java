package controller;

import controller.interfaces.CommandOperator;
import controller.interfaces.ControlParser;
import controller.interfaces.ParamOperator;
import model.Model;
import utilities.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Operator implements CommandOperator, ParamOperator {
    public static final String CONSTANT = "Constant";
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSED_BRACKET = "]";
    public static final String GROUP_START = "GroupStart";
    public static final String OPEN_GROUP = "(";
    public static final String CLOSED_GROUP = ")";
    public static final String VARIABLE = "Variable";
    public static final String COMMAND = "Command";
    public static final String COMMAND_TYPE = "CommandType";
    public static final String COMMAND_INPUT = "MakeUserInstruction";
    public static final String MAKE_VARIABLE = "MakeVariable";
    public static final String ID = "ID";
    public static final String OPERATION = "Operation";
    public static final String REPCOUNT = ":repcount";


    private OperationFinder operationFinder;
    private ProgramParser languageParser;
    private ArgumentType argument;
    private ParamOperator sendParams;
    private Boolean currentCommand;
    private Model myModel;
    private CustomCommands customCommands;

    /**
     * in charge of running individual commands
     *
     * @param language
     * @param parser
     */
    public Operator(String language, ControlParser parser) {
        argument = new ArgumentType(COMMAND_TYPE);
        myModel = new Model();
        languageParser = new ProgramParser(language);
        sendParams = this;
        customCommands = new CustomCommands(argument, parser);
        operationFinder = new OperationFinder(argument, myModel, sendParams, parser, customCommands);
        currentCommand = null;
    }

    /**
     * is the method called on by the controller to run a given command, the result of the command will be added to the stack
     *
     * @param command
     * @param commandStack
     * @param turtleIndex
     */
    @Override
    public void runCommand(String command, Stack<String> commandStack, String turtleIndex) {
        argument.addVar(REPCOUNT, (Integer.parseInt(argument.getVar(REPCOUNT)) + 1) + "");
        command = languageParser.getSymbol(command).equals(COMMAND) ? command : languageParser.getSymbol(command);
        currentCommand = command.equals(COMMAND_INPUT) || command.equals(MAKE_VARIABLE);
        if (command.equals(MAKE_VARIABLE) && argument.getVar(commandStack.peek()).equals(commandStack.peek())) {
            currentCommand = true;
            argument.addVar(commandStack.peek(), "0");
        }
        if (command.equals(CONSTANT)) {
            return;
        }
        loopOverCommand(command, commandStack, turtleIndex);
    }

    private void loopOverCommand(String command, Stack<String> commandStack, String turtleIndex) {
        if (!argument.getSymbol(command).equals(OPERATION)) {
            findParamsAndComplete(command, commandStack, turtleIndex);
        } else {
            for (int i = 0; i < myModel.getActiveTurtles().size(); i++) {
                findParamsAndComplete(command, i + 1 == myModel.getActiveTurtles().size() ? commandStack : (Stack<String>) commandStack.clone(), myModel.getActiveTurtles().get(i));
            }
        }
    }

    private Stack<String> findParamsAndComplete(String command, Stack<String> commandStack, String turtleIndex) {
        int paramSize = argument.getNumArguments(command);
        List<String> params;
        if (command.equals(GROUP_START)) {
            params = new ArrayList<>();
            if (commandStack.peek().equals(CLOSED_BRACKET)) return commandStack;
            params.add(languageParser.getSymbol(commandStack.peek()).equals(COMMAND) ? commandStack.pop() : languageParser.getSymbol(commandStack.pop()));
            params.add(handleMultiInput(commandStack, OPEN_GROUP, CLOSED_GROUP));
        } else {
            params = getParams(paramSize, commandStack, turtleIndex);
        }
        commandStack.push(operationFinder.completeOperation(command, params, turtleIndex));
        return commandStack;
    }

    /**
     * returns all the possible parameters of a given operation. made public because special operations use it to get the parameters
     * of some of their inputted values.
     *
     * @param paramSize
     * @param commandStack
     * @param turtleIndex
     * @return
     */
    @Override
    public List<String> getParams(int paramSize, Stack<String> commandStack, String turtleIndex) {
        List<String> params = new ArrayList<>();
        for (int i = 0; i < paramSize; i++) {
            if (commandStack.isEmpty()) return params;
            String current = commandStack.pop();
            if (current.equals(OPEN_BRACKET)) {
                current = handleMultiInput(commandStack, OPEN_BRACKET, CLOSED_BRACKET);
            } else if (argument.getSymbol(current).equals(CONSTANT) || checkCurrentCommand()) {
                //do nothing
            } else if (argument.getSymbol(current).equals(VARIABLE)) {
                if (!argument.getVar(current).equals(current)) {
                    current = argument.getVar(current);
                }
            } else if (languageParser.getSymbol(current).equals(ID)) {
                current = turtleIndex;
            } else {
                runCommand(current, commandStack, turtleIndex);
                i--;
                continue;
            }
            params.add(current);
        }
        return params;
    }

    private boolean checkCurrentCommand() {
        if (currentCommand) {
            currentCommand = false;
            return true;
        }
        return false;
    }

    private String handleMultiInput(Stack<String> commandStack, String startCommand, String endCommand) {
        int j = 1;
        String current = " ";
        if (!commandStack.peek().equals(endCommand)) {
            current = "";
            while (j >= 1) {
                current += " " + commandStack.pop();
                if (commandStack.peek().equals(endCommand)) {
                    j--;
                } else if (commandStack.peek().equals(startCommand)) {
                    j++;
                }
            }
            current = current.substring(1);
        }
        commandStack.pop();
        return current;
    }

    /**
     * returns all the variables to the controller
     *
     * @return
     */
    @Override
    public Map<String, String> getVars() {
        return argument.getVars();
    }

    /**
     * returns all custom commands to the controller
     *
     * @return
     */
    @Override
    public List<String> getCustomCommands() {
        return customCommands.getCustomCommands();
    }

    /**
     * changes the language of the parser
     *
     * @param language
     */
    @Override
    public void changeLanguage(String language) {
        languageParser = new ProgramParser(language);
    }

    /**
     * returns the list of commands from the model to the controller
     *
     * @return
     */
    @Override
    public List<Command> getListCommands() {
        List<Command> commands = new ArrayList<>(myModel.getCommandList());
        myModel.clearCommandList();
        return commands;
    }
}
