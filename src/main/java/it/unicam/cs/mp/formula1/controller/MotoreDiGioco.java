package it.unicam.cs.mp.formula1.controller;

import it.unicam.cs.mp.formula1.interfaces.ICar;
import it.unicam.cs.mp.formula1.interfaces.IMotoreDiGioco;
import it.unicam.cs.mp.formula1.interfaces.IMoveStrategy;
import it.unicam.cs.mp.formula1.interfaces.ITrack;
import it.unicam.cs.mp.formula1.modelli.Coordinate;

import java.util.List;
import java.util.Random;

/**
 E’ responsabile della gestione della logica di movimento della gara ovvero far muovere i bot sul tracciato
 seguendo le regole del gioco e la matematica prevista dal gioco di Formula 1

 */
public class MotoreDiGioco implements IMotoreDiGioco {


    private List<ICar> cars;
    private ITrack track;
    private IMoveStrategy moveStrategy;





    public MotoreDiGioco(List<ICar> cars, ITrack track) {
        this.cars = cars;
        this.track = track;

        moveStrategy = new RandomMoveStrategy();


    }



    @Override
    public void moveCar(ICar car) {
        Coordinate newMove = moveStrategy.getNextMove(car, track);
        if (newMove != null) {
            updateCarPosition(car, newMove);
        } else {
            outOfTrackMove(car);
        }
    }


    /**
     * Aggiorna la posizione dell'auto e calcola la posizione corrente per poter poi determinare il punto principale
     * su cui basare la prossima posizione
     * @param car
     * @param newMove
     */

    private void updateCarPosition(ICar car, Coordinate newMove) {
        Coordinate currentPosition = car.getCurrentPosition();
        Coordinate lastMove = new Coordinate(
                newMove.getX() - currentPosition.getX(),
                newMove.getY() - currentPosition.getY());
        car.setCurrentPosition(newMove);
        car.setLastMove(lastMove);
    }

    /**
     * Nel caso la macchina vada fuori pista, imposta la posizione alla posizione precedente.
     */
    private void outOfTrackMove(ICar car) {
        car.setCurrentPosition(car.getPreviousPosition());
        car.setLastMove(new Coordinate(0, 0));
    }


}
