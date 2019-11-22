package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the remainder between two given parameters
 */
public class Remainder implements Operation {

    public Remainder(Model myModel) {
    }

    @Override
    public String execute(List<String> params, String index) {
        return (Double.parseDouble(params.get(0)) % Double.parseDouble(params.get(1))) + "";
    }
}
