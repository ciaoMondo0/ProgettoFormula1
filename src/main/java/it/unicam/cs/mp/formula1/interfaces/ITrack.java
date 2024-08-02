package it.unicam.cs.mp.formula1.interfaces;

import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;

public interface ITrack {


    Coordinate getStartingPosition();

    Coordinate getEndingPosition();

    List<Coordinate> getPossibleMoves(ICar car);

    List<Coordinate> filterValidMoves(ICar car, List<Coordinate> moves);
}
