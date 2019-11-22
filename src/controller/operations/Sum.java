package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the sum of two parameters
 */
public class Sum implements Operation {
    public Sum(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return (Double.parseDouble(params.get(0)) + Double.parseDouble(params.get(1))) + "";
    }
}