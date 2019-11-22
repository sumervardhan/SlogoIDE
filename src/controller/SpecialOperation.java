package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * class with some helpful methods for all special operations
 */
public class SpecialOperation {
    public static final String WHITESPACE = "\\s+";

    /**
     * converts a given list of double strings to string integers
     *
     * @param arr
     * @return
     */
    protected List<String> convertToIntegers(List<String> arr) {
        List<String> str = new ArrayList<>();
        for (String s : arr) {
            int temp = (int) Double.parseDouble(s);
            str.add(temp + "");
        }
        return str;
    }

    /**
     * converts a string to a stack
     *
     * @param params
     * @return
     */
    protected Stack<String> convertToStack(String params) {
        Stack<String> stack = new Stack<>();
        String[] arr = params.split(WHITESPACE);
        for (int i = arr.length - 1; i >= 0; i--) {
            stack.push(arr[i]);
        }
        return stack;
    }

    /**
     * takes a string of commands and splits it by the whitespace
     *
     * @param commands
     * @return
     */
    protected List<String> parseValues(String commands) {
        return new ArrayList<>(Arrays.asList(commands.split(WHITESPACE)));
    }

    /**
     * loops over a given item based on a start value, end, step, the command list, and a possible variable types
     *
     * @param startI
     * @param endI
     * @param step
     * @param commandList
     * @param var
     * @return
     */
    protected String loop(int startI, int endI, int step, List<String> commandList, String var) {
        String newCommand = "";
        for (int i = startI; i < endI; i += step) {
            for (String s : commandList) {
                if (s.equals(var)) {
                    newCommand += i + " ";
                } else {
                    newCommand += s + " ";
                }
            }
        }
        return newCommand.substring(0, newCommand.length() - 1);
    }
}
