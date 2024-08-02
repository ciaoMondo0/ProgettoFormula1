package it.unicam.cs.mp.formula1.interfaces;

import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;

public interface ICar {
    Coordinate getCurrentPosition();

    Coordinate getLastMove();



    void setCurrentPosition(Coordinate move);

    void setLastMove(Coordinate move);

    Coordinate getPreviousPosition();


    void setFinished(boolean finished);

    boolean getFinished();

    String getName();

    Coordinate getPrincipalPoint();

    List<Coordinate> getPositionHistory();
}
