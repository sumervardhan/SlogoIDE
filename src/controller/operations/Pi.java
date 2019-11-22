package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the Pi value
 */
public class Pi implements Operation {
    public Pi(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return Math.PI + "";
    }
}
