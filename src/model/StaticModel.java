package model;

public interface StaticModel {

    void penUp(int pen, String index);

    void showTurtle(int turtle, String index);

    void clearScreen(String index);

    String getX(String index);

    String getY(String index);

    String getHeading(String index);

    String getPenUp(String index);

    String getTurtleShow(String index);

    String getPenColor(String index);

}
