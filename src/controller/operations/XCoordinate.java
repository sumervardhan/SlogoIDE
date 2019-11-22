package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the x coordinate of a given turtle
 */
public class XCoordinate implements Operation {
    private Model myModel;

    public XCoordinate(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //returns the coordinate of the turtle
        return myModel.getX(index);
    }

}
