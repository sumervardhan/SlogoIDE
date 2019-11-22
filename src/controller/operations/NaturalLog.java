package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * takes the natural log of a value
 */
public class NaturalLog implements Operation {
    public NaturalLog(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return (Math.log(Double.parseDouble(params.get(0)))) + "";
    }
}
