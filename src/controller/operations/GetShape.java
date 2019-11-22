package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the shape index
 */
public class GetShape implements Operation {
    private Model myModel;

    public GetShape(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //Get the shape of the turtle
        return myModel.getShape(index);
    }
}
