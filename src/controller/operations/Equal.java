package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns true if the two values are equal
 */
public class Equal implements Operation {
    public Equal(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return ((Double.parseDouble(params.get(0)) == Double.parseDouble(params.get(1))) ? 1 : 0) + "";
    }
}
