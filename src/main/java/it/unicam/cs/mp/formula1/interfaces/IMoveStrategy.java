package it.unicam.cs.mp.formula1.interfaces;

import it.unicam.cs.mp.formula1.modelli.Coordinate;

public interface IMoveStrategy {

    Coordinate getNextMove(ICar car, ITrack track);
}
