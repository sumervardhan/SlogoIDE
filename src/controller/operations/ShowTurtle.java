package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * shows the turtle
 */
public class ShowTurtle implements Operation {
    private Model myModel;

    public ShowTurtle(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //Show turtle
        myModel.showTurtle(1, index);
        myModel.addToCommandList(new utilities.Commands.ShowTurtle(Integer.parseInt(index)));
        return "1";
    }
}
