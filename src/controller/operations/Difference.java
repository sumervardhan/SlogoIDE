package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * takes the difference of two param values
 */
public class Difference implements Operation {
    public Difference(Model myModel) {
    }

    @Override
    public String execute(List<String> params, String index) {
        return (Double.parseDouble(params.get(0)) - Double.parseDouble(params.get(1))) + "";
    }
}
