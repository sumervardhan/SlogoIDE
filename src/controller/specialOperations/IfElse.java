package controller.specialOperations;

import controller.interfaces.ControlParser;
import controller.interfaces.ParamOperator;
import model.Model;

/**
 * an if else statement
 */
public class IfElse extends If {
    public IfElse(Model model, ControlParser parser, ParamOperator convertParams) {
        super(model, parser, convertParams);
    }
}
