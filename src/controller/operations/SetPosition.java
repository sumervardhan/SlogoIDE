package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.AddLineCommand;
import utilities.Commands.MoveTurtleCommand;
import utilities.Coordinate;

import java.util.List;

/**
 * sets the position to a given coordinate of parameters
 */
public class SetPosition implements Operation {
    private Model myModel;

    public SetPosition(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        myModel.addToCommandList(myModel.getPenUp(index).equals("1") ?
                new MoveTurtleCommand(Integer.parseInt(index), Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index)),
                        Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1))) :
                new AddLineCommand(Integer.parseInt(index), Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index)),
                        Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1))));
        myModel.move(new Coordinate(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1))), index);
        return "0";
    }
}
