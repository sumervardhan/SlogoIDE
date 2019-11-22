package utilities.Commands;

import utilities.Command;
import view.BasicView;

import java.util.Map;

/**
 * Command object that tells the front end to update the user defined variables that have been entered by the user.
 *
 * @author Ben lawrence
 */
public class UpdateCustomVariablesCommand implements Command {
    private Map<String, String> variables;

    /**
     * Constructor for UpdateCustomVariablesCommand.
     *
     * @param variables - A map of string variable names to string variable values
     */
    public UpdateCustomVariablesCommand(Map<String, String> variables) {
        this.variables = variables;
    }

    /**
     * Execute method for this command. Tells the front end to update its list of variables based on this more current list.
     *
     * @param view - BasicView being executed on
     */
    @Override
    public void execute(BasicView view) {
        view.updateCustomVariables(variables);
    }
}
