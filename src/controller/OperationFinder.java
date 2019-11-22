package controller;

import controller.exceptions.InvalidSyntaxException;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.List;

public class OperationFinder {
    public static final String STANDARD_ROOT = "controller.operations.";
    public static final String SPECIAL_ROOT = "controller.specialOperations.";
    public static final String SPECIAL_OPERATION = "SpecialOperation";
    public static final String OPERATION = "Operation";
    public static final String OPERATOR = "Operator";
    public static final String GROUP_START = "GroupStart";
    public static final String MAKE_VARIABLE = "MakeVariable";


    private Model myModel;
    private CustomCommands customCommands;
    private ArgumentType argument;
    private ParamOperator convertParams;
    private ControlParser parser;

    /**
     * is the "factory" class used to call the correct operation
     *
     * @param arg
     * @param myModel
     */
    public OperationFinder(ArgumentType arg, Model myModel, ParamOperator convertParams, ControlParser parser, CustomCommands customCommands) {
        this.myModel = myModel;
        this.customCommands = customCommands;
        argument = arg;
        this.parser = parser;
        this.convertParams = convertParams;
    }

    /**
     * completes the given command and takes in its parameters, as well as a control parser and param
     *
     * @param command     the given command
     * @param params      the parameters of the command
     * @param turtleIndex the turtle its being applied to
     * @return the result of the operation
     */
    public String completeOperation(String command, List<String> params, String turtleIndex) {
        Operation operation;
        if (argument.getSymbol(command).equals(SPECIAL_OPERATION)) {
            operation = specialOperation(command);
        } else if (argument.getSymbol(command).equals(OPERATION) || argument.getSymbol(command).equals(OPERATOR)) {
            operation = standardOperation(command);
        } else if (command.equals(MAKE_VARIABLE)) {
            operation = argOperation(command);
        } else {
            return customCommands.handleCustomCommands(command, params);
        }
        return operation.execute(params, turtleIndex);
    }

    private Operation argOperation(String command) {
        try {
            return (Operation) Class.forName(SPECIAL_ROOT + command).getDeclaredConstructor(ArgumentType.class, ControlParser.class).newInstance(argument, parser);
        } catch (Exception e) {
            throw new InvalidSyntaxException("Incorrect Method name");
        }
    }

    private Operation standardOperation(String command) {
        try {
            return (Operation) Class.forName(STANDARD_ROOT + command).getDeclaredConstructor(Model.class).newInstance(myModel);
        } catch (Exception e) {
            throw new InvalidSyntaxException("Incorrect Method name");
        }
    }

    private Operation specialOperation(String command) {
        try {
            if (command.equals(GROUP_START)) {
                return (Operation) Class.forName(SPECIAL_ROOT + command).getDeclaredConstructor(ArgumentType.class, ControlParser.class, ParamOperator.class).newInstance(argument, parser, convertParams);
            } else {
                return (Operation) Class.forName(SPECIAL_ROOT + command).getDeclaredConstructor(Model.class, ControlParser.class, ParamOperator.class).newInstance(myModel, parser, convertParams);
            }
        } catch (Exception e) {
            throw new InvalidSyntaxException("Incorrect Method name");
        }
    }
}
