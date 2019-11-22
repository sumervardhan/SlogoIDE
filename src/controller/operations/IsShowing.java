package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns 1 if the turtle is showing, 0 else
 */
public class IsShowing implements Operation {
    private Model myModel;

    public IsShowing(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //return if the turtle is showing (0:1)
        return myModel.getTurtleShow(index);
    }
}
