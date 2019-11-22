package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns 1 if the pen is down
 */
public class IsPenDown implements Operation {

    private Model myModel;

    public IsPenDown(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        // should return 0 if pen is up and 1 if down
        return Integer.parseInt(myModel.getPenUp(index)) == 0 ? "1" : "0";
    }
}
