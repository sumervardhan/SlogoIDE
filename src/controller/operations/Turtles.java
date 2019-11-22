package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the number of turtles in a window
 */
public class Turtles implements Operation {
    private Model myModel;

    public Turtles(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String turtleIndex) {
        System.out.println(myModel.getNumTurtles());
        return myModel.getNumTurtles();
    }
}
