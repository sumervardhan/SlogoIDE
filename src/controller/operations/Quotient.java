package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * divides param 0 by param 1
 */
public class Quotient implements Operation {
    public Quotient(Model myModel) {
    }

    @Override
    public String execute(List<String> params, String index) {
        return (Double.parseDouble(params.get(0)) / Double.parseDouble(params.get(1))) + "";
    }
}
