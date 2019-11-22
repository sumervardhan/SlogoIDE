package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the opposite of what is given 1, or 0
 */
public class Not implements Operation {
    public Not(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return ((!(Double.parseDouble(params.get(0)) == 1)) ? 1 : 0) + "";
    }
}
