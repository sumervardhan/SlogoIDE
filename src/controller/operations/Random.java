package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns a random digit from 1 to given param
 */
public class Random implements Operation {
    public Random(Model myModel) {

    }

    @Override
    public String execute(List<String> params, String index) {
        java.util.Random rand = new java.util.Random();
        return (rand.nextInt((int) Double.parseDouble(params.get(0)))) + "";
    }
}
