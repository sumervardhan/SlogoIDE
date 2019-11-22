package controller;

import controller.exceptions.InvalidSyntaxException;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ArgumentType extends ProgramParser {
    public static final String RESOURCES_PACKAGE = "/resources/languages/";
    public static final String NUM_ARGUMENTS = "numArguments";
    public static final String REPCOUNT = ":repcount";

    private Map<String, Integer> args;
    private Map<String, String> vars;

    /**
     * is a class which extends standard program parser but also has some additional features useful for obtaining arguments
     * this includes finding the number of arguments for a given set of operations, as well as storing all of the different
     * variables.
     *
     * @param language of the argument type
     */
    public ArgumentType(String language) {
        super(language);
        args = new HashMap<>();
        vars = new HashMap<>();
        vars.put(REPCOUNT, "0");
    }

    /**
     * returns the number of arguments a given operation needs
     *
     * @param text which is the operation
     * @return an integer
     */
    public int getNumArguments(String text) {
        ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + NUM_ARGUMENTS);
        if (resources.containsKey(text)) {
            return Integer.parseInt(resources.getString(text));
        } else if (args.containsKey(text)) {
            return args.get(text);
        } else {
            throw new InvalidSyntaxException("This command type doesn't exist...");
        }
    }

    /**
     * returns the value of a given variable, or just the variable itself, if the map does not contain this key
     *
     * @param str the variable name
     * @return
     */
    public String getVar(String str) {
        if (vars.containsKey(str)) {
            return vars.get(str);
        } else {
            return str;
        }
    }

    /**
     * adds variables to the dictionary, given the var name and value
     *
     * @param var
     * @param val
     */
    public void addVar(String var, String val) {
        vars.put(var, val);
    }

    /**
     * adds new arguments to the argument map based on user defined commands
     *
     * @param name
     * @param numArgs
     */
    public void addArgs(String name, int numArgs) {
        args.put(name, numArgs);
    }

    /**
     * returns all the variables, used for the front end to display the variable types
     *
     * @return
     */
    public Map<String, String> getVars() {
        return new HashMap<>(vars);
    }
}
