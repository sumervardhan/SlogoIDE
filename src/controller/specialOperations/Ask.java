package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.List;
import java.util.Stack;


public class Ask extends SpecialOperation implements Operation {
    /**
     * only does the specific command on the numbers given, and then returns to the normal active turtles
     */

    private Model myModel;
    private ControlParser parser;
    private ParamOperator convertParams;

    public Ask(Model myModel, ControlParser parser, ParamOperator convertParams) {
        this.myModel = myModel;
        this.parser = parser;
        this.convertParams = convertParams;
    }

    @Override
    public String execute(List<String> params, String turtleIndex) {
        List<String> activeTurtles = myModel.getActiveTurtles();
        Stack<String> stack = convertToStack(params.get(0));
        List<String> inputs = convertParams.getParams(Integer.MAX_VALUE, stack, turtleIndex);
        inputs = convertToIntegers(inputs);
        myModel.setActiveTurtles(inputs);
        String result = parser.runOperations(params.get(1), turtleIndex);
        myModel.setActiveTurtles(activeTurtles);
        return result;
    }
}
