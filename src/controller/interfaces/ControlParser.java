package controller.interfaces;

/**
 * This is the controlParser interface. This is in charge of parsing a given string command. This is in it's own interface
 * because some operations need access to parse commands so this interface can be passed to many different operations and
 * it will only have access to this specific method.
 */
public interface ControlParser {

    String runOperations(String commands, String turtleIndex);
}
