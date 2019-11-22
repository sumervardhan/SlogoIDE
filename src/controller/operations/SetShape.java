package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.SetShapeCommand;

import java.util.List;

/**
 * Sets the shape to a given index
 */
public class SetShape implements Operation {
    private Model myModel;

    public SetShape(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        // set the shape
        myModel.setShape(Integer.parseInt(params.get(0)), index);
        myModel.addToCommandList(new SetShapeCommand(Integer.parseInt(index), Integer.parseInt(params.get(0))));
        return "0";
    }
}
