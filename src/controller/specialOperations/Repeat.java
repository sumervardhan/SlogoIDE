package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.List;

/**
 * repeats a set of commands a given number of times
 */
public class Repeat extends SpecialOperation implements Operation {
    private ControlParser parser;

    public Repeat(Model model, ControlParser parser, ParamOperator convertParams) {
        this.parser = parser;
    }

    @Override
    public String execute(List<String> params, String index) {
        int iterations = (int) (Double.parseDouble(params.get(0)));
        List<String> commandList = parseValues(params.get(1));
        String newCommands = loop(0, iterations, 1, commandList, null);
        return parser.runOperations(newCommands, index);
    }
}
