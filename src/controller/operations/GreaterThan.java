package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns 1 if the first param is greater than the second, 0 else
 */
public class GreaterThan implements Operation {
    public GreaterThan(Model myModel) {
    }

    @Override
    public String execute(List<String> params, String index) {
        return ((Double.parseDouble(params.get(0)) > Double.parseDouble(params.get(1))) ? 1 : 0) + "";
    }
}
