package model;

import utilities.Command;

import java.util.List;

public interface ListModel {

    List<Command> getCommandList();

    void clearCommandList();

    void addToCommandList(Command command);

}
