package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.AddLineCommand;
import utilities.Commands.MoveTurtleCommand;
import utilities.Coordinate;

import java.util.List;

/**
 * brings the turtle back home to 0,0
 */
public class Home implements Operation {

    private Model myModel;

    public Home(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //send turtle home
        //move Coordinate(0,0);
        myModel.addToCommandList(myModel.getPenUp(index).equals("1") ?
                new MoveTurtleCommand(Integer.parseInt(index), Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index)), 0, 0) :
                new AddLineCommand(Integer.parseInt(index), Double.parseDouble(myModel.getX(index)), Double.parseDouble(myModel.getY(index)), 0, 0));
        myModel.move(new Coordinate(0.0, 0.0), index);
        return "0.0";
    }
}
