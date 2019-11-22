package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns 1 if the two values are not equal 0 else
 */
public class NotEqual implements Operation {
    public NotEqual(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return ((Double.parseDouble(params.get(0)) != Double.parseDouble(params.get(1))) ? 1 : 0) + "";
    }
}
