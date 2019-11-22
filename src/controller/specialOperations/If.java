package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.List;

/**
 * standard if statement
 */
public class If extends SpecialOperation implements Operation {
    private ControlParser parser;

    public If(Model model, ControlParser parser, ParamOperator convertParams) {
        this.parser = parser;
    }

    @Override
    public String execute(List<String> params, String index) {
        if (!params.get(0).equals("0")) {
            return runIf(parser, params.get(1), index);
        } else {
            return runElse(parser, params.size() > 2 ? params.get(2) : "0", index);
        }
    }

    protected String runElse(ControlParser parser, String elseCommands, String index) {
        return parser.runOperations(elseCommands, index);
    }

    protected String runIf(ControlParser parser, String ifCommands, String index) {
        return parser.runOperations(ifCommands, index);
    }
}
