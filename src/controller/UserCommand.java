package controller;

import java.util.*;

/**
 * The base class for a given user command which stores the necessary parameters and returns the command which will input
 * the custom parameters in
 */
public class UserCommand {
    public static final String WHITESPACE = " ";

    private Map<String, String> paramMap;
    private List<String> paramNames;
    private List<String> command;

    public UserCommand(List<String> params) {
        createParamMap(params);
        command = createCommand(params);

    }

    private void createParamMap(List<String> params) {
        paramMap = new HashMap<>();
        paramNames = new ArrayList<>(Arrays.asList(params.get(1).split(WHITESPACE)));
        for (String s : paramNames) {
            paramMap.put(s, "");
        }
    }

    private List<String> createCommand(List<String> params) {
        return new ArrayList<>(Arrays.asList(params.get(2).split(WHITESPACE)));
    }

    /**
     * returns the command list
     *
     * @return
     */
    public List<String> getCommand() {
        return new ArrayList<>(command);
    }

    private void setParamMap(List<String> vars) {
        for (int i = 0; i < vars.size(); i++) {
            paramMap.put(paramNames.get(i), vars.get(i));
        }
    }

    /**
     * returns the amount of parameters needed for this command
     *
     * @return
     */
    public int getParamSize() {
        return paramNames.size();
    }

    /**
     * returns the custom operation given the list of parameters
     *
     * @param vars
     * @return
     */
    public String returnCustomOperation(List<String> vars) {
        setParamMap(vars);
        String operation = "";
        for (String s : command) {
            if (paramMap.containsKey(s)) {
                operation += paramMap.get(s) + " ";
            } else {
                operation += s + " ";
            }
        }
        return operation;
    }

    /**
     * toString for this usercommand
     *
     * @return
     */
    @Override
    public String toString() {
        String str = "";
        for (String s : paramNames) {
            str += s + " ";
        }
        for (String s : command) {
            str += s + " ";
        }
        return str.substring(0, str.length() - 1);
    }
}
