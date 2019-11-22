package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * calculates the sine of a given param
 */
public class Sine implements Operation {
    public Sine(Model myModel) {
    }

    @Override
    public String execute(List<String> params, String index) {
        return Math.sin(Math.toRadians(Double.parseDouble(params.get(0)))) + "";
    }
}
