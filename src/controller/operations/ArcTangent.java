package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the arc tan of a given value
 */
public class ArcTangent implements Operation {
    public ArcTangent(Model myModel) {
    }

    @Override
    public String execute(List<String> params, String index) {
        return Math.toDegrees(Math.atan(Double.parseDouble(params.get(0)))) + "";
    }
}
