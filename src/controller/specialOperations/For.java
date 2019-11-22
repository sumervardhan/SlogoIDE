package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.List;
import java.util.Stack;

/**
 * a standard for loop which goes over certain operations
 */
public class For extends SpecialOperation implements Operation {

    private ParamOperator convertParams;
    private ControlParser parser;

    public For(Model model, ControlParser parser, ParamOperator convertParams) {
        this.convertParams = convertParams;
        this.parser = parser;
    }

    @Override
    public String execute(List<String> params, String index) {
        Stack<String> inputs = convertToStack(params.get(0));
        String var = inputs.pop();
        List<String> inputParams = convertParams.getParams(3, inputs, index);
        int startI = (int) (Double.parseDouble(inputParams.get(0)));
        int endI = (int) (Double.parseDouble(inputParams.get(1)) + 1);
        int step = (int) Double.parseDouble(inputParams.get(2));
        List<String> commandList = parseValues(params.get(1));
        String newCommand = loop(startI, endI, step, commandList, var);
        return parser.runOperations(newCommand, index);
    }
}
