package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.PenStatusCommand;

import java.util.List;

/**
 * puts the pen down
 */
public class PenDown implements Operation {
    public static final int PEN_DOWN = 0;
    private Model myModel;

    public PenDown(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //call the model to put down the pen
        myModel.penUp(PEN_DOWN, index);
        myModel.addToCommandList(new PenStatusCommand(Integer.parseInt(index), false));
        return "0";
    }
}
