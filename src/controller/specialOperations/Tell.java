package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;
import utilities.Commands.AddTurtleCommand;

import java.util.List;
import java.util.Stack;

public class Tell extends SpecialOperation implements Operation {
    /**
     * sets the active turtles to this list of params
     */

    private Model myModel;
    private ParamOperator convertParams;

    public Tell(Model myModel, ControlParser parser, ParamOperator convertParams) {
        this.myModel = myModel;
        this.convertParams = convertParams;
    }

    @Override
    public String execute(List<String> params, String turtleIndex) {
        Stack<String> stack = convertToStack(params.get(0));
        List<String> inputs = convertParams.getParams(Integer.MAX_VALUE, stack, turtleIndex);
        inputs = convertToIntegers(inputs);
        List<String> newTurtles = myModel.setActiveTurtles(inputs);
        for (String s : newTurtles) {
            myModel.addToCommandList(new AddTurtleCommand(Integer.parseInt(s)));
        }
        return inputs.size() > 0 ? inputs.get(inputs.size() - 1) : "0";
    }
}
