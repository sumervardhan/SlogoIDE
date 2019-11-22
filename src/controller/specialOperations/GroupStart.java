package controller.specialOperations;

import controller.ArgumentType;
import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;

import java.util.List;
import java.util.Stack;

/**
 * applies a given command to a grouping of constants
 */
public class GroupStart extends SpecialOperation implements Operation {
    public static final String OPERATOR = "Operator";

    private ArgumentType argumentType;
    private ControlParser parser;
    private ParamOperator convertParams;

    public GroupStart(ArgumentType argumentType, ControlParser parser, ParamOperator convertParams) {
        this.argumentType = argumentType;
        this.parser = parser;
        this.convertParams = convertParams;
    }

    @Override
    public String execute(List<String> params, String index) {
        Stack<String> inputs = convertToStack(params.get(1));
        if (inputs.isEmpty()) return "0";
        String command = params.get(0);
        int numArgs = argumentType.getNumArguments(command);
        while (!inputs.isEmpty()) {
            List<String> inputParams = convertParams.getParams(numArgs, inputs, index);
            String newCommand = command + " " + loop(0, 1, 1, inputParams, null);
            String result = parser.runOperations(newCommand, index);
            if (argumentType.getSymbol(command).equals(OPERATOR)) {
                if (inputs.isEmpty()) {
                    return result;
                }
                inputs.push(result);
            }
        }
        return "1";
    }
}
