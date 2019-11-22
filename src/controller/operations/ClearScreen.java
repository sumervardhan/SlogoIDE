package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.ClearScreenCommand;
import utilities.Commands.RotateTurtleCommand;

import java.util.List;

/**
 * Clears the screen and resets all the turtles
 */
public class ClearScreen implements Operation {
    private Model myModel;

    public ClearScreen(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //Clear the entire screen
        myModel.clearScreen(index);
        myModel.setHeading(0, index);
        myModel.addToCommandList(new RotateTurtleCommand(Integer.parseInt(index), 0));
        myModel.addToCommandList(new ClearScreenCommand());
        return index;
    }
}
