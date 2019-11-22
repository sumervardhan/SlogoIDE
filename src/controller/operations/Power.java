package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the param at 0 to the power of param at 1
 */
public class Power implements Operation {
    public Power(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return (Math.pow(Double.parseDouble(params.get(0)), Double.parseDouble(params.get(1)))) + "";
    }
}
