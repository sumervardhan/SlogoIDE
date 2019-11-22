package view.selector.features.variabletextfeature;

import view.FeatureReceiver;

import java.util.List;

/**
 * Implements VariableTextFeature, represents a pane showing the custom user commands that have been created.
 *
 * @author Goh Khian Wei
 * @author Ben Lawrence
 */
public class UserCommands extends VariableTextFeature {

    /**
     * Constructor, sets the FeatureReceiver
     *
     * @param view FeatureReceiver containing data to be stored in the pane.
     */
    public UserCommands(FeatureReceiver view) {
        super(view);
    }

    /**
     * Retrieves the custom user commands from the view
     *
     * @param view Class which user commands can be retrieved from
     * @return List of strings representing a user command each
     */
    @Override
    protected List<String> updateHistory(FeatureReceiver view) {
        return view.getUserCommands();
    }

    /**
     * Returns the default string if no user commands are found
     *
     * @return Default string if no user commands are found
     */
    @Override
    protected String getDefaultString() {
        return VariableTextFeatureConstants.USER_COMMANDS_DEFAULT_STRING;
    }
}
