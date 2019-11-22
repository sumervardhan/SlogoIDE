package controller.operations;

import model.Model;

import java.util.List;

/**
 * makes the turtle go backwards whatever the given param value is
 */
public class Backward extends Forward {
    public Backward(Model myModel) {
        super(myModel);
    }

    @Override
    public String execute(List<String> params, String index) {
        params.set(0, (Double.parseDouble(params.get(0)) * -1) + "");
        return super.execute(params, index);
    }
}
