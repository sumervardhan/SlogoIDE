package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the tangent value of a given param
 */
public class Tangent implements Operation {
    public Tangent(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return (Math.tan(Math.toRadians(Double.parseDouble(params.get(0))))) + "";
    }
}
