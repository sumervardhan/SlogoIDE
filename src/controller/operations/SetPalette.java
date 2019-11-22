package controller.operations;

import controller.interfaces.Operation;
import model.Model;
import utilities.Commands.SetPaletteCommand;

import java.util.List;

/**
 * sets the pallatte to a specific RGB value
 */
public class SetPalette implements Operation {
    private Model myModel;

    public SetPalette(Model myModel) {
        this.myModel = myModel;
    }

    @Override
    public String execute(List<String> params, String index) {
        // set the pallette
        myModel.addToCommandList(new SetPaletteCommand(Integer.parseInt(params.get(0)), Integer.parseInt(params.get(1)), Integer.parseInt(params.get(2)), Integer.parseInt(params.get(3))));
        //Currently returning whitespace separated string of red " " green " " blue values
        return params.get(0);
    }
}
