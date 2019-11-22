package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns 1 if either of the two params are 1, 0 else
 */
public class Or implements Operation {
    public Or(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return ((Double.parseDouble(params.get(0)) == 1 || Double.parseDouble(params.get(1)) == 1) ? 1 : 0) + "";
    }
}
