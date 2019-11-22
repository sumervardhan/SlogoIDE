package controller.specialOperations;

import controller.SpecialOperation;
import controller.interfaces.ControlParser;
import controller.interfaces.Operation;
import controller.interfaces.ParamOperator;
import model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AskWith extends SpecialOperation implements Operation {
    /**
     * any turtle which passes a given boolean will do a specified set of commands
     */
    private Model myModel;
    private ControlParser parser;
    private ParamOperator convertParams;

    public AskWith(Model myModel, ControlParser parser, ParamOperator convertParams) {
        this.myModel = myModel;
        this.parser = parser;
        this.convertParams = convertParams;
    }

    @Override
    public String execute(List<String> params, String turtleIndex) {
        List<String> activeTurtles = myModel.getActiveTurtles();
        List<String> conditional = new ArrayList<>();
        for (String s : myModel.getAllTurtles()) {
            myModel.setActiveTurtles(Arrays.asList(s));
            if (parser.runOperations(params.get(0), s).equals("1")) {
                conditional.add(s);
            }
        }
        myModel.setActiveTurtles(conditional);
        String result = parser.runOperations(params.get(1), turtleIndex);
        myModel.setActiveTurtles(activeTurtles);
        return result;
    }
}
