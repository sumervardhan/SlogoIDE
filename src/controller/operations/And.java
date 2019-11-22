package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * takes in two params and returns true if both are 1
 */
public class And implements Operation {
    public And(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return ((Double.parseDouble(params.get(0)) == 1 && Double.parseDouble(params.get(1)) == 1) ? 1 : 0) + "";
    }
}
