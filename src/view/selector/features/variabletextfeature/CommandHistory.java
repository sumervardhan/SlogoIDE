package view.selector.features.variabletextfeature;

import view.FeatureReceiver;

import java.util.List;

/**
 * * Implements VariableTextFeature, represents a pane showing the commands that have already been entered.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 */
public class CommandHistory extends VariableTextFeature {

    /**
     * Constructor, sets the FeatureReceiver
     *
     * @param view FeatureReceiver containing data to be stored in the pane.
     */
    public CommandHistory(FeatureReceiver view) {
        super(view);
    }

    /**
     * Retrieves the command history from the view
     *
     * @param view Class which the command history can be retrieved from
     * @return List of strings representing a command each
     */
    @Override
    protected List<String> updateHistory(FeatureReceiver view) {
        return view.getHistory();
    }

    /**
     * Returns the default string if no command history is found
     *
     * @return Default string if no command history is found
     */
    @Override
    protected String getDefaultString() {
        return VariableTextFeatureConstants.COMMAND_HISTORY_DEFAULT_STRING;
    }
}
