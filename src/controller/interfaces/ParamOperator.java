package controller.interfaces;

import java.util.List;
import java.util.Stack;

/**
 * This is the interface used for the operator. This is seperate because some operations (i.e. special operations) need
 * access to this method to get their parameters based on the bracketed information. This way only this method can be
 * accessed by the operations.
 */
public interface ParamOperator {

    List<String> getParams(int paramSize, Stack<String> commandStack, String turtleIndex);
}
