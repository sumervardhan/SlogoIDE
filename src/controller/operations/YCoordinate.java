package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the y coordinate of a given turtle
 */
public class YCoordinate implements Operation {
    private Model myModel;

    public YCoordinate(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //should return the y coordinate
        return myModel.getY(index);
    }
}
