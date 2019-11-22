package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the heading of the turtle
 */
public class Heading implements Operation {
    private Model myModel;

    public Heading(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        // should return the turtles heading
        return myModel.getHeading(index);
    }
}
