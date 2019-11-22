package model;

import utilities.Coordinate;

public interface MovementModel {

    void move(double distance, String index);

    void move(Coordinate cord, String index);

    void rotate(double degrees, String index);

}
