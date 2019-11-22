package controller.operations;

import controller.interfaces.Operation;
import model.Model;

import java.util.List;

/**
 * returns the pen color
 */
public class GetPenColor implements Operation {

    private Model myModel;

    public GetPenColor(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //returns the pen color
        return myModel.getPenColor(index);
    }
}
