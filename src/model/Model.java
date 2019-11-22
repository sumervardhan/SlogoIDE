package model;

import utilities.Command;
import utilities.Coordinate;

import java.util.*;

public class Model implements ListModel, MovementModel, StaticModel {

    public static final int DEFAULT_BACKGROUND = 0;
    private Map<String, Turtle> myTurtles;
    private List<String> activeTurtles;
    private List<Command> myCommandList;
    private int myBackground;

    public Model() {
        myTurtles = new HashMap<>();
        activeTurtles = new ArrayList<>();
        myTurtles.put("0", new Turtle());
        activeTurtles.add("0");
        myCommandList = new ArrayList<>();
        myBackground = DEFAULT_BACKGROUND;
    }

    @Override
    public void addToCommandList(Command command) {
        myCommandList.add(command);
    }

    @Override
    public List<Command> getCommandList() {
        return new ArrayList<>(myCommandList);
    }

    @Override
    public void clearCommandList() {
        myCommandList.clear();
    }

    public List<String> getActiveTurtles() {
        return new ArrayList<>(activeTurtles);
    }

    @Override
    public void move(double distance, String index) {
        double x = Math.sin(Double.parseDouble(getHeading(index)) * Math.PI / 180) * distance + myTurtles.get(index).getCoordinates().getX();
        double y = Math.cos(Double.parseDouble(getHeading(index)) * Math.PI / 180) * distance + myTurtles.get(index).getCoordinates().getY();
        myTurtles.get(index).setCoordinates(new Coordinate(x, y));
    }

    @Override
    public void move(Coordinate coordinate, String index) {
        myTurtles.get(index).setCoordinates(coordinate);
    }

    @Override
    public void rotate(double degrees, String index) {
        myTurtles.get(index).setHeading(Double.parseDouble(getHeading(index)) + degrees);
    }


    @Override
    public void penUp(int pen, String index) {
        myTurtles.get(index).setPenUp(pen);
    }

    @Override
    public void showTurtle(int turtle, String index) {
        myTurtles.get(index).setIsShowing(turtle);
    }

    @Override
    public void clearScreen(String index) {
        myTurtles.get(index).setCoordinates(new Coordinate(0.0, 0.0));
    }

    @Override
    public String getX(String index) {
        return String.valueOf(myTurtles.get(index).getCoordinates().getX());
    }

    @Override
    public String getY(String index) {
        return String.valueOf(myTurtles.get(index).getCoordinates().getY());
    }

    @Override
    public String getHeading(String index) {
        return String.valueOf(myTurtles.get(index).getHeading());
    }

    public void setHeading(double heading, String index) {
        myTurtles.get(index).setHeading(heading);
    }

    @Override
    public String getPenUp(String index) {
        return String.valueOf(myTurtles.get(index).isPenUp());
    }

    @Override
    public String getTurtleShow(String index) {
        return String.valueOf(myTurtles.get(index).getIsShowing());
    }

    @Override
    public String getPenColor(String index) {
        return String.valueOf(myTurtles.get(index).getPenColor());
    }

    public void setPenColor(int color, String index) {
        myTurtles.get(index).setPenColor(color);
    }

    public void setShape(String turtleIndex, int shape) {
        myTurtles.get(turtleIndex).setShape(shape);
    }

    public String getShape(String turtleIndex) {
        return myTurtles.get(turtleIndex).getShape() + "";
    }

    public void setMyBackground(int background) {
        myBackground = background;
    }

    public List<String> setActiveTurtles(List<String> newTurtles) {
        List<String> initializedTurtles = new ArrayList<>();
        for (String s : newTurtles) {
            if (!myTurtles.containsKey(s)) {
                initializedTurtles.add(s);
                myTurtles.put(s, new Turtle());
            }
        }
        activeTurtles = newTurtles;
        return initializedTurtles;
    }

    public String getNumTurtles() {
        return myTurtles.keySet().size() + "";
    }

    public Set<String> getAllTurtles() {
        return myTurtles.keySet();
    }

    public void setPenSize(int size, String index) {
        myTurtles.get(index).setPenSize(size);
    }

    public void setShape(int shape, String index) {
        myTurtles.get(index).setShape(shape);
    }
}
