package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.SetPenColorCommand;

import java.util.List;

/**
 * sets the pen color to a given index value
 */
public class SetPenColor implements Operation {
    private Model myModel;

    public SetPenColor(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //set pen color
        myModel.setPenColor(Integer.parseInt(params.get(0)), index);
        myModel.addToCommandList(new SetPenColorCommand(Integer.parseInt(index), Integer.parseInt(params.get(0))));
        return params.get(0);
    }
}
