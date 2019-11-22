package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.SetBackgroundColorCommand;

import java.util.List;

/**
 * sets the background to a new color
 */
public class SetBackground implements Operation {
    private Model myModel;

    public SetBackground(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        //change the background color
        myModel.setMyBackground(Integer.parseInt(params.get(0)));
        myModel.addToCommandList(new SetBackgroundColorCommand(Integer.parseInt(params.get(0))));
        return params.get(0);
    }
}
