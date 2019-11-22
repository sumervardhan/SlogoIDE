package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.PenStatusCommand;

import java.util.List;

/**
 * pulls the pen up
 */
public class PenUp implements Operation {
    public static final int PEN_UP = 1;
    private Model myModel;

    public PenUp(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        // pull the pen up
        myModel.penUp(PEN_UP, index);
        myModel.addToCommandList(new PenStatusCommand(Integer.parseInt(index), true));
        return PEN_UP + "";
    }
}
