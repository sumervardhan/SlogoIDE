package controller.interfaces;

import java.util.List;

/**
 * The standard operation interface. This is the interface that every single operation implements to create a generic operation
 * command that can then be applied using reflection.
 */
public interface Operation {

    String execute(List<String> params, String turtleIndex);
}
