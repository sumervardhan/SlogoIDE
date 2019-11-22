package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.List;
import java.util.Stack;

/**
 * will do a set number of times
 */
public class DoTimes extends SpecialOperation implements Operation {

    private ParamOperator convertParams;
    private ControlParser parser;

    public DoTimes(Model model, ControlParser parser, ParamOperator convertParams) {
        this.convertParams = convertParams;
        this.parser = parser;
    }

    @Override
    public String execute(List<String> params, String index) {
        Stack<String> inputs = convertToStack(params.get(0));
        String var = inputs.pop();
        int repititions = (int) (Double.parseDouble(convertParams.getParams(1, inputs, index).get(0)));
        List<String> commandList = parseValues(params.get(1));
        String newCommand = loop(1, repititions + 1, 1, commandList, var);
        return parser.runOperations(newCommand, index);
    }
}
