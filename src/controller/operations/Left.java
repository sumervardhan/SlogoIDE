package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * rotates the turtle to the left
 */
public class Left extends Right implements Operation {
    public Left(Model myModel) {
        super(myModel);
    }

    @Override
    public String execute(List<String> params, String index) {
        params.set(0, (Double.parseDouble(params.get(0)) * -1) + "");
        return super.execute(params, index);
    }
}
