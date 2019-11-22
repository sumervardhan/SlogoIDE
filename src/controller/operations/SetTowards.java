package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.RotateTurtleCommand;
import utilities.Coordinate;

import java.util.List;


/**
 * sets the command to go towards a given value
 */
public class SetTowards implements Operation {
    private Model myModel;

    public SetTowards(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        Coordinate current = new Coordinate(Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index)));
        Coordinate towards = new Coordinate(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)));
        double angle = 90 - Math.atan((towards.getY() - current.getY()) / (towards.getX() - current.getX()));
        myModel.addToCommandList(new RotateTurtleCommand(0, angle - Double.parseDouble(myModel.getHeading(index))));
        myModel.rotate(angle - Double.parseDouble(myModel.getHeading(index)), index);
        return angle - Double.parseDouble(myModel.getHeading(index)) + "";
    }
}
