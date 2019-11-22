package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.AddLineCommand;
import utilities.Commands.MoveTurtleCommand;
import utilities.Coordinate;

import java.util.List;

/**
 * moves a turtle forward a given amount
 */
public class Forward implements Operation {
    private Model myModel;

    public Forward(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //move the model forward
        Coordinate old = new Coordinate(Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index)));
        myModel.move(Double.parseDouble(params.get(0)), index);
        //This has to be fixed in the model
        myModel.addToCommandList(myModel.getPenUp(index).equals("1") ? new MoveTurtleCommand(Integer.parseInt(index), old.getX(), old
                .getY(), Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index))) : new AddLineCommand(Integer.parseInt(index), old.getX(), old
                .getY(), Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index))));
        return params.get(0);
    }
}