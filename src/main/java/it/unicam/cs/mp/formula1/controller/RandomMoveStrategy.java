package it.unicam.cs.mp.formula1.controller;


import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.IMoveStrategy;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;
import java.util.Random;


/**
 * Responsabile di definire la strategia di movimento delle auto
 *
 *
 */
public class RandomMoveStrategy implements IMoveStrategy {
    private Random random;

    public RandomMoveStrategy() {
        this.random = new Random();
    }


    /**
     * Calcola il prossimo movimento randomicamente tra le posizioni filtrate disponibili
     * @param car
     * @param track
     * @return Coordinate
     */
    @Override
    public Coordinate getNextMove(ICar car, ITrack track) {
        List<Coordinate> possibleMoves = track.getPossibleMoves(car);
        List<Coordinate> filteredMoves = track.filterValidMoves(car, possibleMoves);

        if (!filteredMoves.isEmpty()) {
            return filteredMoves.get(random.nextInt(filteredMoves.size()));
        } else {
            return null; // Indica che non ci sono mosse valide
        }
    }
}
