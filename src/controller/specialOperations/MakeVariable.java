package controller.specialOperations;

import controller.ArgumentType;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;

import java.util.List;

/**
 * instantiates a variable
 */
public class MakeVariable implements Operation {
    private ArgumentType argumentType;

    public MakeVariable(ArgumentType argumentType, ControlParser parser) {
        this.argumentType = argumentType;
    }

    @Override
    public String execute(List<String> params, String index) {
        argumentType.addVar(params.get(0), params.get(1));
        return "1";
    }
}
