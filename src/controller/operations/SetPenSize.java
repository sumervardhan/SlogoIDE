package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.SetPenSizeCommand;

import java.util.List;

/**
 * sets the pen size to a given size
 */
public class SetPenSize implements Operation {
    private Model myModel;

    public SetPenSize(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        // set pen size
        myModel.setPenSize(Integer.parseInt(params.get(0)), index);
        myModel.addToCommandList(new SetPenSizeCommand(Integer.parseInt(index), Double.parseDouble(params.get(0))));
        return params.get(0);
    }
}
