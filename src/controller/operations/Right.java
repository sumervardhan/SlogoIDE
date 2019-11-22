package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.RotateTurtleCommand;

import java.util.List;

/**
 * rotates the turtle to the right a given amount of degrees
 */
public class Right implements Operation {
    private Model myModel;

    public Right(Model myModel) {
        this.myModel = myModel;
    }

    public String execute(List<String> params, String index) {
        //model.left(params)
        myModel.rotate(Double.parseDouble(params.get(0)), index);
        myModel.addToCommandList(new RotateTurtleCommand(Integer.parseInt(index), Double.parseDouble(myModel.getHeading(index))));
        return params.get(0);
    }
}
