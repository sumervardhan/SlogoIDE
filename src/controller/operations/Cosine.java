package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * takes the cosine of a given param value
 */
public class Cosine implements Operation {
    public Cosine(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        return (Math.cos(Math.toRadians(Double.parseDouble(params.get(0))))) + "";
    }
}
