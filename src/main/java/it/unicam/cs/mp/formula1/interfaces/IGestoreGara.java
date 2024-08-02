package it.unicam.cs.mp.formula1.interfaces;

import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;

public interface IGestoreGara {

     void gestisciGara();
    boolean isGameOver();
    ICar getWinner();

    List<ICar> getBots();

    ICar getCurrentCar();
    List<Coordinate> getPossibleMoves();
}
