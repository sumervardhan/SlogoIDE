package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.HideTurtleCommand;

import java.util.List;

/**
 * hides the turtle from the user
 */
public class HideTurtle implements Operation {
    private Model myModel;

    public HideTurtle(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //hide turtle
        myModel.showTurtle(0, index);
        myModel.addToCommandList(new HideTurtleCommand(Integer.parseInt(index)));
        return "0";
    }
}
