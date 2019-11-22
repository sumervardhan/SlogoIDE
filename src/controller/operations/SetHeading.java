package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.RotateTurtleCommand;

import java.util.List;

/**
 * sets the heading to an absolute direction
 */
public class SetHeading implements Operation {

    private Model myModel;

    public SetHeading(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        /*
        Need to somehow obtain the angle from the given coordinates. Need api method for this
         */
        String currentHeading = myModel.getHeading(index);
        myModel.setHeading(Double.parseDouble(params.get(0)), index);
        myModel.addToCommandList(new RotateTurtleCommand(Integer.parseInt(index), (Double.parseDouble(myModel.getHeading(index)))));
        return myModel.getHeading(index);
    }
}
