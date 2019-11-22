package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the negated value of a given parameter
 */
public class Minus implements Operation {
    public Minus(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return (-1 * Double.parseDouble(params.get(0))) + "";
    }
}
